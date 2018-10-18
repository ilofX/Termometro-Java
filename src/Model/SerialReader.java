/*
 * Copyright 2018 Stella Filippo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Model;

import View.MainFrame;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stella Filippo
 * @version 1.0
 */
public class SerialReader implements SerialPortEventListener {

    private static final Integer TIMEOUT = 905000;
    private InputStream input;
    private SerialPort port;
    private CommPortIdentifier portID;
    private Integer dataRate;
    private final MainFrame mf;
    private final FileManager fm;
    
    public SerialReader(MainFrame mf, FileManager fm){
        this.mf = mf;       
        this.fm = fm;
    }
    
    public void initializeConnection (CommPortIdentifier portID, Integer dataRate){
        this.dataRate = dataRate;
        this.portID = portID;
    }

    public void init() {
        try {
            this.port = (SerialPort) this.portID.open("ArduinoSerialPORT", SerialReader.TIMEOUT);
        } catch (PortInUseException ex) {
            Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.input = this.port.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.port.addEventListener(this);
        } catch (TooManyListenersException ex) {
            Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port.notifyOnDataAvailable(true);
        
        try {
            this.port.setSerialPortParams(this.dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void close(){
        try {
            this.port.removeEventListener();
            this.input.close();
            this.port.close();
        } catch (IOException ex) {
            Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        switch(spe.getEventType()){
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            byte[] readBuffer = new byte[20];
            try {
                while(this.input.available() > 0){
                    int bytesRead = this.input.read(readBuffer);
                }
                this.mf.getjTextArea1().setText(this.mf.getjTextArea1().getText()+new String(readBuffer).trim()+" °C\n");
                this.mf.setScrollToMaximum();
                this.fm.Write(new String(readBuffer));
            } catch (IOException ex) {
                Logger.getLogger(SerialReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
    }
}
