package com.taliter.fiscal.port;

import java.io.*;

/** A FiscalPort factory. */
public interface FiscalPortSource extends Serializable
{
	/** 
         * Clones the current object.
         *@return A deep copy of this port source. 
         */
	public Object clone();

	/** 
         * Create a FiscalPort object. 
         * @return A FiscalPort object.
         * @throws Exception Throws Exception.
         */
	public FiscalPort getFiscalPort() throws Exception;
}
