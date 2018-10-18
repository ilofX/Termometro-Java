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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stella Filippo
 * @version 1.0
 */
public class FileManager {
    
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private File file;
    private FileOutputStream outStream;
    private PrintWriter out;
    private boolean isOpen=false;

    public void SetFile(File f){
        if(this.isOpen){this.close();}
        this.file = f;
    }
    
    public void Write(String s){
        this.open();
        Date date = new Date();
        this.out.println(this.dateFormat.format(date)+"\t"+s.trim()+" °C");
        this.out.flush();
        this.close();
    }
    
    public void open() {
        try {
            this.file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.outStream = new FileOutputStream(this.file,true);
            this.out = new PrintWriter(this.outStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isOpen=true;
    }
    
    public void close(){
        try {
            this.out.close();
            this.outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isOpen=false;
    }
    
}
