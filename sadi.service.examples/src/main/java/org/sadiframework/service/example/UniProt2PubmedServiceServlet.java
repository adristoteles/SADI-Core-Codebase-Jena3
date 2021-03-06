package org.sadiframework.service.example;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sadiframework.service.annotations.ContactEmail;
import org.sadiframework.service.annotations.TestCase;
import org.sadiframework.service.annotations.TestCases;
import org.sadiframework.utils.SIOUtils;
import org.sadiframework.vocab.SIO;

import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.kraken.interfaces.uniprot.citationsNew.Author;
import uk.ac.ebi.kraken.interfaces.uniprot.citationsNew.AuthoringGroup;
import uk.ac.ebi.kraken.interfaces.uniprot.citationsNew.Citation;
import uk.ac.ebi.kraken.interfaces.uniprot.citationsNew.JournalArticle;
import uk.ac.ebi.kraken.interfaces.uniprot.citationsNew.PubMedId;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDFS;

@ContactEmail("info@sadiframework.org")
@TestCases(
		@TestCase(
				input = "http://sadiframework.org/examples/t/uniprot2pubmed.input.1.rdf",
				output = "http://sadiframework.org/examples/t/uniprot2pubmed.output.1.rdf"
		)
)

public class UniProt2PubmedServiceServlet extends UniProtServiceServlet
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(UniProt2PubmedServiceServlet.class);

	private static final String PUBMED_PREFIX = "http://lsrn.org/PMID:";
	private final Resource PubMed_Type = ResourceFactory.createResource("http://purl.oclc.org/SADI/LSRN/PMID_Record");
	private final Resource PubMed_Identifier = ResourceFactory.createResource("http://purl.oclc.org/SADI/LSRN/PMID_Identifier");

	@Override
	public void processInput(UniProtEntry input, Resource output)
	{
		for (Citation cite: input.getCitationsNew()) {
			if (cite instanceof JournalArticle) {
				Resource pubmedNode = createPubMedNode(output.getModel(), (JournalArticle)cite);
				if (pubmedNode != null)
					output.addProperty(SIO.has_reference, pubmedNode);
			}
		}
	}

	private Resource createPubMedNode(Model model, JournalArticle article)
	{
		PubMedId pmId = article.getCitationXrefs().getPubmedId();
		if (pmId == null || StringUtils.isEmpty(pmId.getValue()))
			return null;

		Resource pubmedNode = model.createResource(getPubMedUri(pmId), PubMed_Type);

		// add identifier structure...
		SIOUtils.createAttribute(pubmedNode, PubMed_Identifier, pmId.getValue());

		// add label...
		pubmedNode.addProperty(RDFS.label, getLabel(article));

		return pubmedNode;
	}

	private static String getPubMedUri(PubMedId pmId)
	{
		String pdbId = pmId.getValue();
		return String.format("%s%s", PUBMED_PREFIX, pdbId);
	}

	private static String getLabel(JournalArticle article)
	{
		return String.format("%s, %s", formatAuthorList(article), article.getTitle());
	}

	private static String formatAuthorList(JournalArticle article)
	{
		List<Author> authors = article.getAuthors();
		List<AuthoringGroup> authorGroups = article.getAuthoringGroup();
		if (authors != null && !authors.isEmpty()) {
			Iterator<Author> i = authors.iterator();
			StringBuilder buf = new StringBuilder();
			Author firstAuthor = i.next();
			buf.append(firstAuthor.getValue());
			if (authors.size() > 3) {
				buf.append(" et al");
				return buf.toString();
			}
			while (i.hasNext()) {
				Author author = i.next();
				if (i.hasNext()) {
					buf.append(", ");
					buf.append(author.getValue());
				} else {
					buf.append(" & ");
					buf.append(author.getValue());
				}
			}
			return buf.toString();
		} else if (authorGroups != null  && authorGroups.isEmpty()) {
			Iterator<AuthoringGroup> i = authorGroups.iterator();
			StringBuilder buf = new StringBuilder();
			buf.append(i.next().getValue());
			while (i.hasNext()) {
				AuthoringGroup authoringGroup = i.next();
				if (i.hasNext()) {
					buf.append(", ");
					buf.append(authoringGroup.getValue());
				} else {
					buf.append(" & ");
					buf.append(authoringGroup.getValue());
				}
			}
			return buf.toString();
		} else {
			return "unknown author";
		}
	}
}
