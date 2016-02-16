/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taliter.fiscal.device.icl;

import com.taliter.fiscal.device.FiscalDeviceEventHandler;
import com.taliter.fiscal.device.FiscalPacket;
import com.taliter.fiscal.device.InvalidFiscalResponseException;
import com.taliter.fiscal.port.FiscalPort;
import java.io.IOException;

/**
 *
 * @author nikolabintev@edabg.com
 */
public class ICLFiscalDevice extends ICLBasicFiscalDevice {
    
    /**
     * Creates an instance of DaisyFiscalDevice.
     * @param port The port in which the fiscal device is connected.
     * @param timeout The time to execute the request. In milliseconds.
     * @param maxTries The number of tries to execute the request.
     * @param encoding Fiscal device's encoding.
     */
    private int CMD_PRINTER_STATUS = 0x4a;
            
    public ICLFiscalDevice(FiscalPort port, int timeout, int maxTries, String encoding) {
        super(port, timeout, maxTries, encoding);
    }
    
    /**
     * Get the command for the printer status.
     * @return The printer status command.
     */
    public int getCMDPrinterStatus() {
        return this.CMD_PRINTER_STATUS;
    }
    
    public void setCMDPrinterStatus(int printerStatusCmd) {
        this.CMD_PRINTER_STATUS = printerStatusCmd;
    }
    
    /**
     * Opens a connection with the daisy fiscal device.
     * @throws Exception Throws Exception
     */
    @Override
    public void open() throws Exception {
        super.open();
        boolean success = false;
        try {
            synchronize();
            success = true;
        } finally {
            if (!success) {
                close();
            }
        }
    }
    /**
     * Tests and synchronizes the communication with the fiscal device. Done automatically when the connection is opened.
     * @throws IOException Throws Exception
     */
    @Override
    public void synchronize() throws IOException {
        FiscalDeviceEventHandler h = eventHandler;
        eventHandler = null;	// Suppress event generation.
        try {
            
            FiscalPacket request = createFiscalPacket();
            request.setCommandCode(CMD_PRINTER_STATUS);
            FiscalPacket response = createFiscalPacket();
            basicExecute(request, response, request);	// The first try may hit the last used serial number
            basicExecute(request, response, request);	// and get its corresponding response.

            if (getCMDPrinterStatus() != response.getCommandCode()) {
                throw new InvalidFiscalResponseException(request, response);	// Will not tolerate STATPRN here.
           }

        } finally {
            eventHandler = h;
        }
    }
}

