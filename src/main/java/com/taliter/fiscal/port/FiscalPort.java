package com.taliter.fiscal.port;

import java.io.*;

/** A bidirectional stream channel. */
public interface FiscalPort
{
        /**
         * Open the port. 
         * @throws Exception Throws Exception
        */
	public void open() throws Exception;
        
        /**
         * Close the port. Does nothing if already closed.
         * @throws Exception Throws Exception
         */
	public void close() throws Exception;
        
        /**
         * Checks whether the port is opened.
         * @return Returns true if the port is open.
         */
	public boolean isOpen();

	/** 
         * Set the receive timeout. The port must be open. -1 means no timeout (default after open()). 
         * @param ms The timeout in milliseconds.
         * @throws Exception Throws Exception
         */
	public void setTimeout(int ms) throws Exception;
	
        /** 
         * Get the receive timeout. The port must be open. -1 means no timeout (default after open()). 
         * @return The timeout in milliseconds.
         * @throws Exception Throws Exception
         */
	public int getTimeout() throws Exception;

	/** 
         * Set the baud rate. The port may be open or closed.
         * @param baudRate The port baud rate.
         * @throws Exception Throws UnsupportedOperationException if baud rates are not applicable to this type of port. 
         */
	public void setBaudRate(int baudRate) throws Exception;
        
	/** 
         * Get the baud rate. The port may be open or closed.
         * @return The port baud rate.
         * @throws Exception Throws UnsupportedOperationException if baud rates are not applicable to this type of port. 
         */
	public int getBaudRate() throws Exception;
        
        
	/** 
         * Get the input stream. The port must be open. 
         * @return The input stream.
         * @throws IOException Throws IOException
         */
	public InputStream getInputStream() throws IOException;
        
	/** 
         * Get the output stream. The port must be open. 
         * @return The output stream.
         * @throws IOException IOException
         */
	public OutputStream getOutputStream() throws IOException;

	/** 
         * Flush the output stream and wait until output is done. 
         * @throws IOException Throws IOException
         */
	public void flushAndWait() throws IOException;
}
