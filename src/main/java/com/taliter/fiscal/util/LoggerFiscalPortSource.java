package com.taliter.fiscal.util;

import java.io.*;

import com.taliter.fiscal.port.*;

/** A LoggerFiscalPort factory. */
public class LoggerFiscalPortSource implements FiscalPortSource, Cloneable
{
	private FiscalPortSource portSource;
	private transient PrintWriter printWriter;
	private transient PrintStream printStream;

	public LoggerFiscalPortSource() {}

	public LoggerFiscalPortSource(FiscalPortSource portSource)
	{
		this.portSource = portSource;
	}

	public LoggerFiscalPortSource(FiscalPortSource portSource, PrintWriter printWriter)
	{
		this.portSource = portSource;
		this.printWriter = printWriter;
	}

	public LoggerFiscalPortSource(FiscalPortSource portSource, PrintStream printStream)
	{
		this.portSource = portSource;
		this.printStream = printStream;
	}

	public Object clone()
	{
		try { return super.clone(); }
		catch (CloneNotSupportedException e) { throw new Error(e.toString()); }
	}

	/**
         * Set the underlying FiscalPortSource. 
         * @param portSource A fiscal port source.
         */
	public void setPortSource(FiscalPortSource portSource) { this.portSource = portSource; }
	
        /** 
         * Get the underlying FiscalPortSource. 
         * @return A fiscal port source.
         */
	public FiscalPortSource getPortSource() { return portSource; }

	/** 
         * Set the logging PrintWriter. 
         * @param printWriter A logging print writer.
         */
	public void setPrintWriter(PrintWriter printWriter) { this.printWriter = printWriter; printStream = null; }
	/** 
         * Get the logging PrintWriter. 
         * @return The PrintWriter object.
         */
	public PrintWriter getPrintWriter() { return printWriter; }

	/** 
         * Set the logging PrintStream. 
         * @param printStream The PrintStream object
         */
	public void setPrintStream(PrintStream printStream) { this.printStream = printStream; printWriter = null; }
	
        /** 
         * Get the logging PrintStream. 
         * @return The logging print stream.
         */
	public PrintStream getPrintStream() { return printStream; }

	/** Create a LoggerFiscalPort object. */
	public FiscalPort getFiscalPort() throws Exception { return getLoggerFiscalPort(); }

	/** 
         * Create a LoggerFiscalPort object. 
         * @return A LoggerFiscalPort object.
         * @throws Exception Throws Exception.
         */
	public LoggerFiscalPort getLoggerFiscalPort() throws Exception
	{
		if (printWriter != null) return new LoggerFiscalPort(portSource.getFiscalPort(), printWriter);
		else if (printStream != null) return new LoggerFiscalPort(portSource.getFiscalPort(), printStream);
		else throw new NullPointerException("Undefined log printer");
	}
}
