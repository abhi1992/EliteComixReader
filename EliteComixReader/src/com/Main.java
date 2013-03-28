/**
*    Copyright (c) 2013 Abhishek Banerjee.
*    This file is part of Elite Comix Reader.
*    
*    Elite Comix Reader is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*    
*    Elite Comix Reader is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*    
*    You should have received a copy of the GNU General Public License
*    along with Elite Comix Reader.  If not, see <http://www.gnu.org/licenses/>.
* 
*/
package com;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 * Main Class
 * @author Abhishek
 * @since v0.0.1
 */
public class Main {
  
    /**
     * Main method
     * @param args command line arguments
     */
     public static void main(final String args[]) {
         
        final ArchiveManager arch = new ArchiveManager();
        
        //MainFrame mainFrame = null;
        try {
            load();
        } catch(Exception e) {
            File f = new File(ExtractorModel.getAppDir() + "/Properties.xml");
            f.delete();
            load();
        }
        try {
            if(Settings.getLaf().equals("System")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            else {
                UIManager.setLookAndFeel(Settings.getLaf());
            }
            
        } catch (ClassNotFoundException | InstantiationException 
                    | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    System.exit(1);
            }
        
        SwingUtilities.invokeLater(new Runnable() {

             @Override
                public void run() {
                    if(args.length == 0)
                        new MainFrame(arch);
                    else
                        new MainFrame(args[0], arch);
                }
         });
        //mainFrame = null;
    }
     
    static void load() {
        
        try {
            Settings.load();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            
        }
    }
    }
