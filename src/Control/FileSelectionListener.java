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
package Control;

import View.MainFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Stella Filippo
 * @version 1.0
 */
public class FileSelectionListener implements MouseListener{
    
    private final MainFrame mf;

    public FileSelectionListener(MainFrame mf) {
        this.mf = mf;
        this.mf.addFileSelectionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mf.getjFileChooser1().setCurrentDirectory(new File("."));
        this.mf.getjFileChooser1().setDialogTitle("Scegli il percorso di salvataggio del file");
        this.mf.getjFileChooser1().setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.mf.getjFileChooser1().setAcceptAllFileFilterUsed(false);
        if(this.mf.getjFileChooser1().showOpenDialog(this.mf) == JFileChooser.APPROVE_OPTION){
            this.mf.getjTextField1().setText(this.mf.getjFileChooser1().getSelectedFile().getAbsolutePath()+"\\temperatura.txt");
        }
        else{
            
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
