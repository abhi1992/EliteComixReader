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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Abhishek Banerjee
 */
public class ArchiveManager extends ExtractorModel{
   private static File f;
   FileExtractor fExt;
   RARExtractor cbrExt;
    public int extract(File file) throws IOException {
        f = file;
        if(f.getName().endsWith(".cbr") || f.getName().endsWith(".rar")) {
            
            cbrExt = new RARExtractor();
            
                return cbrExt.writeToOutputStream(file, new ByteArrayOutputStream());
            
            
        }
        else if(file.toString().endsWith(".cbz") || f.getName().endsWith(".zip")) {
           CbzExtractor cbzExt;
           cbzExt = new CbzExtractor();
           //System.out.print("sdkhdhsl");
           return cbzExt.writeToOutputStream(file, new ByteArrayOutputStream());
       }
        else if(file.isDirectory()) {
            fExt = new FileExtractor(file.listFiles());
                return 0;
        }
        return -1;
    }
    
    public BufferedImage getImage(int index) throws IOException {
            return getImageFromFile(index);
 //       return null;
    }
    
    static int getSize(){    
        return FileExtractor.getFileSize();
    }
}
