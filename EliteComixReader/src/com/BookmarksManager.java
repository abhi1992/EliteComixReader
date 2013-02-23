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
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Loads and adds bookmarks.
 * @author Abhishek
 * @version v0.0.1
 * @since v0.0.1
 */
public class BookmarksManager {
    
    /**
     * Loads bookmarks or creates the file bookmarks.txt if it doesn't exist.
     * @param f name of the comic
     * @return integer denoting success or failure
     * @throws IOException read write error
     */
    static int load(File f) throws IOException {
        try {
            return Bookmarks.load(f);
        } catch (FileNotFoundException ex) {
            try {
                File ff = new File(ExtractorModel.getAppDir());
                ff.mkdirs();
                ff = new File(ExtractorModel.getAppDir() + "/bookmarks.txt" );
                ff.createNewFile();
            } catch (IOException ex1) {
                
            }
        }
    return -1;
    }
    
    /**
     * Adds index to the bookmarks list.
     * @param f name of the comic
     * @param index page no of the comic book to add to bookmarks
     */
    static void add(File f, int index) {
        try {
            Bookmarks.add(f, index);
        } catch (FileNotFoundException ex) {
            try {
                File ff = new File(ExtractorModel.getAppDir());
                ff.mkdirs();
                ff = new File(ExtractorModel.getAppDir() + "/bookmarks.txt" );
                ff.createNewFile();
            } catch (IOException ex1) {
                
            }
            
        } catch (IOException ex) {
        }
    }
    
    /**
     * Removes index to the bookmarks list.
     * @param f name of the comic
     * @param index page no of the comic book to remove from bookmarks
     */
    static void remove(File f, int index) {
        try {
            Bookmarks.remove(f, index);
        } catch (FileNotFoundException ex) {
            try {
                File ff = new File(ExtractorModel.getAppDir());
                ff.mkdirs();
                ff = new File(ExtractorModel.getAppDir() + "/bookmarks.txt" );
                ff.createNewFile();
            } catch (IOException ex1) {
                
            }
            
        } catch (IOException ex) {
        }
    }
    
    /**
     * Adds index to the bookmarks list.
     * @param f name of the comic
     * @param index page no of the comic book to add to bookmarks
     */
    static void setLastPageAsBookmark(File f, int index) {
        if(f != null) {
        try {
            Bookmarks.setLastPageAsBookmark(f, index);
        } catch (FileNotFoundException ex) {
            try {
                File ff = new File(ExtractorModel.getAppDir());
                ff.mkdirs();
                ff = new File(ExtractorModel.getAppDir() + "/bookmarks.txt" );
                ff.createNewFile();
            } catch (IOException ex1) {
            }
            
        } catch (IOException ex) {
        }
        }
    }
    
    /**
     * Retrieve the images from BookmarksIndex
     * @return ArrayList of BufferedImages marked in bookmarks
     */
    static ArrayList<BufferedImage> getBookmarkImages() {
        ArrayList<BufferedImage> arr = new ArrayList<>(4);
        int index[] = Bookmarks.getBookmarksIndex();
        for(int ii = 0; ii < 4; ii++) {
            
                try {
                    if(index[ii] != -1)
                        arr.add(ImageIO.read(ExtractorModel.getFile(index[ii])));
                    else
                        arr.add(
                        ImageIO.read(BookmarksManager
                        .class.getResource("/Resources/user_bookmarks.png")
                        .openStream()));
                } catch (IOException ex) {    
                }
        }
        return arr;
    }
}
