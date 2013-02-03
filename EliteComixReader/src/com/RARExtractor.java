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
import java.io.File;
import java.io.IOException;
import java.util.List;


import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.RarException.RarExceptionType;
import com.github.junrar.io.ReadOnlyAccessFile;
import com.github.junrar.rarfile.FileHeader;
import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class RARExtractor extends ExtractorModel implements Extractor
{	
    
    public RARExtractor(){
        
        //setOutputStream(new ArrayList<PipedOutputStream>());
        setError(new String());
        
    }
    
    
    @Override
    public int writeToOutputStream(File file, ByteArrayOutputStream os){
        if(file.exists()){
            
        if(file.isDirectory()){
            System.out.print("Entered!!\n");
            return recurseDirectory(file, os);
        }else{
            //removeAll();
            return extract(file, os);
        }
        }
        return 0;
    }
    
    static int extract(File file, ByteArrayOutputStream osi)
    {
        
        if(file==null || !file.exists()){
            setError("error file " +file + " does not exist");
            return -1;
        }
        String s = file.toString();
        s = s.substring(s.length()-3);
        
        if(s.equalsIgnoreCase("rar")||s.equalsIgnoreCase("cbr")){

            ReadOnlyAccessFile readFile = null;
            try {
                Archive arc = null;
                try {
                    
                    arc = new Archive(file);
                    
                } catch (RarException e) {
                    setError( "archive consturctor error"+e.getMessage());
                    return -1;
                }
                if(arc != null){
                    if(arc.isEncrypted()){
                        setError( "archive is encrypted cannot extract");
                        return -1;
                    }
                    List<FileHeader> files = arc.getFileHeaders();
                    int i = 0;
                    File f = new File("E:/cbr/");
                    f.mkdirs();
                    
                    
                    
                    for(FileHeader fh : files)
                    {
                        String path = f.getAbsolutePath()+"/"+fh.getFileNameString();
                        //System.out.println(fh.getFileNameString()
                          //          .subSequence(0, fh.getFileNameString().indexOf("\\")));
                        File ff = f;
                        if (fh.getFileNameString().contains("\\")) {
                            ff = new File(f.getAbsolutePath()+"/"+fh.getFileNameString()
                                    .subSequence(0, fh.getFileNameString().indexOf("\\")));
                            ff.mkdirs();
                            ff.deleteOnExit();
                            path = f.getAbsolutePath()+"/" + fh.getFileNameString();
                            //System.out.println(fh.getFileNameString()
                              //      .subSequence(0, fh.getFileNameString().indexOf("\\")));
                        }
                        
                        ff.deleteOnExit();
                        
                        osi = new ByteArrayOutputStream();
                        
                        ff = new File(path);
                        ff.deleteOnExit();
                        
                        //PrintWriter pw = new PrintWriter(os);
                            if(fh.isEncrypted()){
                                setError( "file is encrypted cannot extract: "+fh.getFileNameString());
                                return -1;
                            }
                            if(fh.isFileHeader() && fh.isUnicode()){
                                //logger.info("unicode name: "+fh.getFileNameW());
                            }
                            
                            try(FileOutputStream os = new FileOutputStream(ff);)
                            {
                                
                                arc.extractFile(fh, osi);
                                //add(os);
                                os.write(osi.toByteArray());
                                osi.close();
                                addFile(new File(path));
                            } catch (RarException e) {
                                
                                if(e.getType().equals(RarExceptionType.notImplementedYet)) {
                                    setError( "error extracting unsupported file: "
                                            +fh.getFileNameString()+e.getMessage());
                                    return -1;
                                    }
                                setError( "error extracting file: "
                                        +fh.getFileNameString()+e.getMessage());
                                return -1;
                            
                            } finally {
                                
                            osi.close();
                            }
                            i++;
                    }
                }
                
                setError( "successfully tested archive: "+file);
            
            } catch (Exception e) {
            
                setError( "file: "+file+ " extraction error - does the file exist?"
                        +e.getMessage());
            
            } finally {
                if(readFile != null) {
                    try {
                        readFile.close();
                    } catch (IOException e) {
                        setError(e.getMessage());
                    }
                }
            }
        }
        return 0;
    }
    
    

   static int recurseDirectory(File file, ByteArrayOutputStream os)
    {
        if(file == null || !file.exists()) {
            return -1;
        }
        
        if(file.isDirectory()) {
            File[] files = file.listFiles();
        
            if(files == null) {
                return -1;
            }
            
            for(File f: files) {
                recurseDirectory(f, os);
                f = null;
            }
        
        } else {
            
            file = null;
            return extract(file, os);
            
        }
        return 0;
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
            
        if(file.isDirectory()){
            //System.out.print(recurseDirectory(file));
        }else{
           System.out.print(extract(file, new ByteArrayOutputStream()));
        }
        }
    
    }
}
