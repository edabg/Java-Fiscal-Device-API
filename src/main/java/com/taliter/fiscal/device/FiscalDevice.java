package com.taliter.fiscal.device;

import java.io.*;

/** An interface used to communicate with fiscal devices. */
public interface FiscalDevice
{
	/** 
         * Open the device. 
         * @throws Exception Throws Exception
         */
	public void open() throws Exception;
        
	/** 
         * Close the device. Does nothing if already closed. 
         * @throws Exception Throws Exception
         */
	public void close() throws Exception;

	/** 
         * Checks whether the fiscal device is open.
         * @return Returns true if the device is open. 
         */
	public boolean isOpen();

	/** 
         * Test and synchronize communication with the device. Done automatically after open(). 
         * @throws IOException Throws IOException
         */
	public void synchronize() throws IOException;

	/** 
         * Set the event handler. 
         * @param eventHandler The fiscal device event handler.
         */
	public void setEventHandler(FiscalDeviceEventHandler eventHandler);
        
	/** 
         * Get the event handler. 
         * @return The fiscal device event handler
         */
	public FiscalDeviceEventHandler getEventHandler();

	/** 
         * Create an empty packet. 
         * @return Returns the created packet.
         */
	public FiscalPacket createFiscalPacket();

	/** 
         * Execute a fiscal request. 
         * @param request The request to execute.
         * @return Returns the response from the fiscal device.
         * @throws IOException Throws IOException
        */
	public FiscalPacket execute(FiscalPacket request) throws IOException;
        
	/** 
         * Execute a fiscal request.
         * @param request The request to execute.
         * @param response The response from the fiscal device.
         * @throws IOException Throws IllegalArgumentException if request == response. 
         */
	public void execute(FiscalPacket request, FiscalPacket response) throws IOException;
}
