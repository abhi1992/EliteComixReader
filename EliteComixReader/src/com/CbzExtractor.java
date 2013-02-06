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

/**
 *
 * @author Abhishek Banerjee
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CbzExtractor extends ExtractorModel implements Extractor
{	
    
    public CbzExtractor(){
        
        //setOutputStream(new ArrayList<PipedOutputStream>());
        setError(new String());
        clear();
    }
    
    
    private static int extract(final File file) 
    {
        if(file==null || !file.exists()){
            setError("error file " +file + " does not exist");
            return -1;
        }
        String s = file.toString();
        //s = s.substring(s.length()-3);
        
        if(s.toLowerCase().endsWith("cbz") 
                || s.toLowerCase().equalsIgnoreCase("zip")) {
            s = null;
            
            
            try( ByteArrayOutputStream os = new ByteArrayOutputStream();
                    FileInputStream fin = new FileInputStream(file);
                    ) {
                
                
                try (ZipInputStream zin = new ZipInputStream(fin);
                        ) {
                    ZipEntry entry = zin.getNextEntry();
                    
                    
                    while(entry != null) {
                        File f = new File(appDir);
                        //os = new ByteArrayOutputStream();
                        //System.out.println("entryname " + entry.getName());
                        os.reset();
                        File ff = f;
                        if (entry.isDirectory()) {
                            ff = new File(f.getAbsolutePath()+"/"+entry.getName());
                            ff.mkdirs();
                            ff.deleteOnExit();
                            entry = zin.getNextEntry();
                            continue;
                        }
                        
                        ff.deleteOnExit();
                        String path = ff.getAbsolutePath()+"/"+entry.getName();
                        //ff.mkdirs();
                        ff = new File(path);
                        
                        ff.deleteOnExit();
                        //ff.mkdirs();
                        byte[] buffer = new byte[1024];
			int length;
                        try (FileOutputStream fout = new FileOutputStream(ff)) {
                            while( (length = zin.read(buffer)) > 0)
                            {
                                os.write(buffer, 0, length);
                            }
                            
                            fout.write(os.toByteArray());
                            if(ff!=null)
                                addFile(ff);
                        }
                        entry = zin.getNextEntry();
                                }
                }
                
            }
            catch(IOException e) {
                setError(e.getMessage());
                return -1;
            }finally{
            
            }
        
    }
        return 0;
    }
    

    private static void recurseDirectory(File file)
    {
        if(file==null||!file.exists()){
            return;
        }
        
        if(file.isDirectory()){
            File[] files = file.listFiles();
        
            if(files == null){
                return;
            }
            
            for(File f: files){
                recurseDirectory(f);
                f = null;
            }
        
        }else{
        
            extract(file);
            file=null;
        
        }

    }

    @Override
    public int writeToOutputStream(File file, ByteArrayOutputStream os) {
        return extract(file);
    }
    
    public static void main(String[] args)
    {
    JFileChooser f = new JFileChooser();
    JFrame fa = new JFrame();
    fa.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    fa.setVisible(true);                
    fa.setMinimumSize(new Dimension(200, 200));
    f.showOpenDialog(fa);

    File file = f.getSelectedFile();

    if(file.exists()){
            
        //if(file.isDirectory()){
            //recurseDirectory(file);
        //}else{
        clear();
            System.out.print(extract(file));
            System.out.print(getError());
        //}
        }
    
    }

    


}
