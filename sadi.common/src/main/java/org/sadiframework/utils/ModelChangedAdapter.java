package org.sadiframework.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.sadiframework.utils.ModelChangedAdapter;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelChangedListener;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public abstract class ModelChangedAdapter implements ModelChangedListener
{
	private static final Logger log = Logger.getLogger(ModelChangedAdapter.class);
	
	public abstract void addedStatement(Statement s);

	public abstract void removedStatement(Statement s);

	public void addedStatements(Statement[] statements)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( String.format("adding %d statements from array ", statements.length) );
		
		for (Statement s: statements)
			addedStatement(s);
	}

	public void addedStatements(List<Statement> statements)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( String.format("adding %d statements from list ", statements.size()) );
		
		for (Statement s: statements)
			addedStatement(s);
	}

	public void addedStatements(StmtIterator statements)
	{
		if (log.isTraceEnabled())
			log.trace( "adding statements from iterator" );
		
		while (statements.hasNext())
			addedStatement(statements.nextStatement());
	}

	public void addedStatements(Model m)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( "adding statements from model" );
		
		addedStatements(m.listStatements());
	}

	public void notifyEvent(Model m, Object event)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( String.format("notified of event %s", event) );
	}

	public void removedStatements(Statement[] statements)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( String.format("removing %d statements from array ", statements.length) );
		
		for (Statement s: statements)
			removedStatement(s);
	}

	public void removedStatements(List<Statement> statements)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( String.format("removing %d statements from list ", statements.size()) );
		
		for (Statement s: statements)
			removedStatement(s);
	}

	public void removedStatements(StmtIterator statements)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( "removing statements from iterator" );
		
		while (statements.hasNext())
			removedStatement(statements.nextStatement());
	}

	public void removedStatements(Model m)
	{
		if (getLog().isTraceEnabled())
			getLog().trace( "removing statements from model" );
		
		removedStatements(m.listStatements());
	}
	
	public Logger getLog()
	{
		return log;
	}
}
