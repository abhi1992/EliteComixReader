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

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
/**
 *
 * @author Abhishek Banerjee
 */

class ExtractorModel {
    
    //private static ArrayList<ByteArrayOutputStream> cbb;
    private static String errorString;
    private static ArrayList<File> file;
    private static int fileSize;
    final static String appDir = System.getProperty("user.home")
                        + System.getProperty("file.separator") + ".ELiteComixReader";
    ExtractorModel() {
        //img = new ArrayList<>();
        errorString = new String();
        if(file != null) {
            clear();
        }
        file = new ArrayList<>();
        //size = 0;
        fileSize = 0;
        File f = new File(appDir);
        f.mkdirs();
        Constants c = new Constants();
    }
    
    static File getFile() {
        if(file.isEmpty())
            return null;
        return file.get(0);
    }

    public static String getAppDir() {
        return appDir;
    }
    
    static File getFile(int index) {
        return file.get(index);
    }
    
    static void addFile(File f) {
        if(isImageFile(f)) {
            file.add(f);
            fileSize++;
        }
    }
    
    static void addAll(File files[]) {
        for(File f : files) {
            if(isImageFile(f)) {
                file.add(f);
                fileSize ++;
            }
        }
    }
    
    static boolean isImageFile(File f) {
        return  f.getName().toLowerCase().endsWith(".jpg") ||
                f.getName().toLowerCase().endsWith(".png") ||
                f.getName().toLowerCase().endsWith(".gif") ||
                f.getName().toLowerCase().endsWith(".bmp") ||
                f.getName().toLowerCase().endsWith(".wbmp");     
    }
    
    
    static int getFileSize() {
        return fileSize;
    }
    
    static void clear() {
        for(File f : file) {
            f.delete();
        }
        
    }
    
    static String getError() {
        return errorString;
    }
    
    static void setError(String error) {
        errorString = error;
    }
    
    
    static BufferedImage getImageFromFile(int index) throws IOException {
        BufferedImage image; 
        image = ImageIO.read(file.get(index));
        return image;
    }
    
    static BufferedImage getImageFromFile() throws IOException {
        BufferedImage image; 
        image = ImageIO.read(file.get(0));
        file.remove(0);
        return image;
    }
    
    
    static ByteArrayInputStream getByteArrayInputStream(ByteArrayOutputStream baos) {
        return new ByteArrayInputStream(baos.toByteArray());
    }
    
   
    static ArrayList<BufferedImage> getAllImages(File[] f) throws IOException {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for(int i = 0; i < f.length; i++) {
            images.add(ImageIO.read(f[i]));
            //System.out.print("img "+i+"\n----------\n"+cbb.get(i).getInputStream());
        }
        //removeAll();
        return images;
    }
}
