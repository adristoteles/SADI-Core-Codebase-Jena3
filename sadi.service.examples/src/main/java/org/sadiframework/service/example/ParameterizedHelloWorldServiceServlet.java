package org.sadiframework.service.example;

import org.sadiframework.service.SynchronousServiceServlet;
import org.sadiframework.service.annotations.ContactEmail;
import org.sadiframework.service.annotations.Description;
import org.sadiframework.service.annotations.InputClass;
import org.sadiframework.service.annotations.Name;
import org.sadiframework.service.annotations.OutputClass;
import org.sadiframework.service.annotations.ParameterClass;
import org.sadiframework.service.annotations.ParameterDefaults;
import org.sadiframework.service.annotations.TestCase;
import org.sadiframework.service.annotations.TestCases;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;

/**
 * A slightly less simple "Hello, World" service that reads a name and
 * attaches a greeting. The language of the greeting is read from a
 * secondary parameter.
 *
 * This also demonstrates the use of annotations for service configuration.
 *
 * @author Luke McCarthy
 */
@Name("ParamaterizedHelloWorld")
@Description("A \"Hello, world!\" service where the output language is specified in a parameter")
@ContactEmail("info@sadiframework.org")
@InputClass("http://sadiframework.org/examples/hello.owl#NamedIndividual")
@OutputClass("http://sadiframework.org/examples/hello.owl#GreetedIndividual")
@ParameterClass("http://sadiframework.org/examples/hello.owl#SecondaryParameters")
@ParameterDefaults({"http://sadiframework.org/examples/hello.owl#lang, http://www.w3.org/2001/XMLSchema#string", "en"})
@TestCases({
		@TestCase(
				input = "http://sadiframework.org/examples/t/hello.input.1.rdf",
				output = "http://sadiframework.org/examples/t/hello.output.1.rdf"
		)
		, @TestCase(
				input = "/t/hello-param.input.1.rdf",
				output = "/t/hello-param.output.1.rdf"
		)
		, @TestCase(
				input = "/t/hello-param.input.2.rdf",
				output = "/t/hello-param.output.2.rdf"
		)
		, @TestCase(
				input = "/t/hello-param.input.3.rdf",
				output = "/t/hello-param.output.3.rdf"
		)
		, @TestCase(
				input =
					"<rdf:RDF\n" +
					"    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
					"    xmlns:foaf=\"http://xmlns.com/foaf/0.1/\"\n" +
					"    xmlns:hello=\"http://sadiframework.org/examples/hello.owl#\">\n" +
					"	<hello:NamedIndividual rdf:about=\"http://sadiframework.org/examples/hello-input.rdf#1\">\n" +
					"		<foaf:name>Guy Incognito</foaf:name>\n" +
					"	</hello:NamedIndividual>\n" +
					"	<hello:SecondaryParameters>\n" +
					"		<hello:lang rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">es</hello:lang>\n" +
					"	</hello:SecondaryParameters>\n" +
					"</rdf:RDF>",
				output =
					"<rdf:RDF\n" +
					"    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
					"    xmlns:hello=\"http://sadiframework.org/examples/hello.owl#\">\n" +
					"	<hello:GreetedIndividual rdf:about=\"http://sadiframework.org/examples/hello-input.rdf#1\">\n" +
					"		<hello:greeting>Hola, Guy Incognito!</hello:greeting>\n" +
					"	</hello:GreetedIndividual>\n" +
					"</rdf:RDF>"
		)
})
public class ParameterizedHelloWorldServiceServlet extends SynchronousServiceServlet
{
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.sadiframework.service.SynchronousServiceServlet#processInput(com.hp.hpl.jena.rdf.model.Resource, com.hp.hpl.jena.rdf.model.Resource, com.hp.hpl.jena.rdf.model.Resource)
	 */
	@Override
	public void processInput(Resource input, Resource output, Resource parameters)
	{
		String name = input.getProperty(FOAF.name).getString();
		String lang = parameters.getProperty(Vocab.lang).getString();
		String greeting = null;
		if (lang.equalsIgnoreCase("fr"))
			greeting = "Bonjour";
		else if (lang.equalsIgnoreCase("it"))
			greeting = "Ciao";
		else if (lang.equalsIgnoreCase("es"))
			greeting = "Hola";
		else if (lang.equalsIgnoreCase("en"))
			greeting = "Hello";
		output.addProperty(Vocab.greeting, String.format("%s, %s!", greeting, name), XSDDatatype.XSDstring);
	}

	private static class Vocab
	{
		private static String NS = "http://sadiframework.org/examples/hello.owl#";
		private static Model m_model = ModelFactory.createDefaultModel();
		public static Property greeting = m_model.createProperty(NS + "greeting");
		public static Property lang = m_model.createProperty(NS + "lang");
	}
}
