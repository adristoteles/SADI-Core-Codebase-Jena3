
package com.example;

import org.apache.log4j.Logger;
import ca.wilkinsonlab.sadi.service.annotations.Name;
import ca.wilkinsonlab.sadi.service.annotations.ContactEmail;
import ca.wilkinsonlab.sadi.service.annotations.InputClass;
import ca.wilkinsonlab.sadi.service.annotations.OutputClass;
import ca.wilkinsonlab.sadi.service.simple.SimpleSynchronousServiceServlet;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
//import com.hp.hpl.jena.rdf.model.Statement;
//import com.hp.hpl.jena.rdf.model.StmtIterator;

@Name("hello")
@ContactEmail("adril9@hotmail.com")
@InputClass("http://sadiframework.org/examples/hello.owl#NamedIndividual")
@OutputClass("http://sadiframework.org/examples/hello.owl#GreetedIndividual")
public class HelloWorldService extends SimpleSynchronousServiceServlet
{
	private static final Logger log = Logger.getLogger(HelloWorldService.class);
	private static final long serialVersionUID = 1L;

	@Override
	public void processInput(Resource input, Resource output)
	{
		/* your code goes here
		 * (add properties to output node based on properties of input node...)
		 */

	     System.out.println("¡¡¡¡===INIT processInput SADI.skeleton===!!!");
		 String name = input.getProperty(Vocab.name).getString(); 
		    output.addProperty(Vocab.greeting, String.format("Hello, %s!", name)); 
	}

	@SuppressWarnings("unused")
	private static final class Vocab
	{
		private static Model m_model = ModelFactory.createDefaultModel();
		
		public static final Property greeting = m_model.createProperty("http://sadiframework.org/examples/hello.owl#greeting");
		public static final Property name = m_model.createProperty("http://xmlns.com/foaf/0.1/name");
		public static final Resource GreetedIndividual = m_model.createResource("http://sadiframework.org/examples/hello.owl#GreetedIndividual");
		public static final Resource NamedIndividual = m_model.createResource("http://sadiframework.org/examples/hello.owl#NamedIndividual");
		public static final Resource string = m_model.createResource("http://www.w3.org/2001/XMLSchema#string");
	}
}