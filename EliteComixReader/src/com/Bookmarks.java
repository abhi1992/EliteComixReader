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

import java.io.*;
import java.util.*;

/**
 * Keeps track of bookmarks in the comic 
 * @author Abhishek
 * @since v0.0.1
 * @version v0.0.1
 */
public class Bookmarks {
    private static int bookmarksIndex[] = {-1, -1, -1, -1};
    
    /**
     * Constructor
     * @since v0.0.1
     */
    public Bookmarks() {
        bookmarksIndex = new int[4];
        Arrays.fill( bookmarksIndex, 0, 4, -1);
    }

    /**
     * loads the bookmarkIndex of file f
     * @param f comic book file
     * @throws FileNotFoundException if file is not present
     * @throws IOException if file read write error occurs
     * @return integer 0 denoting success -1 denoting failure
     * @since v0.0.1
     */
    static int load(File f) throws FileNotFoundException, IOException {
        String file = f.getName();
        try (FileInputStream fin = new FileInputStream(ExtractorModel.appDir + 
                "/" + "bookmarks.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String s = br.readLine();
            while(s != null) {
                StringTokenizer test = new StringTokenizer(s);
                if(Integer.parseInt(test.nextToken()) == hash(file)) {
                    for(int i = 0; i < 4 && test.hasMoreTokens(); i++)
                        bookmarksIndex[i] = Integer.parseInt(test.nextToken());
                    return 0;
                }
                s = br.readLine();
            }
        }
        return -1;
    }
    
    /**
     * 
     * @param str the string to be hashed
     * @return integer hash value of input string
     * @since v0.0.1
     */
    private static int hash(String str) {
        int hash = 7;
        for (int i=0; i < str.length(); i++) {
            hash = hash * 31 + str.charAt(i);
        }
        return hash;
    } 
    
    /**
     * adds index to bookmarksIndex of file f
     * @param f file name of current comic
     * @param index index to add to bookmarks
     * @throws FileNotFoundException if file is not present
     * @throws IOException if file read write error occurs
     * @return null 
     * @since v0.0.1
     */
    static void add(File f, int index) throws FileNotFoundException, IOException {
        File in = new File (ExtractorModel.appDir + "/" + "bookmarks.txt");
        File out = new File(ExtractorModel.appDir + "/" + "tmp.txt");
        out.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(out); 
                PrintWriter pw = new PrintWriter(fout);
                FileInputStream fin = new FileInputStream(in)) {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String s = br.readLine();
            String sink;
            boolean flag = false;
            int hash = hash(f.getName());
            
            while(s != null) {
                StringTokenizer test = new StringTokenizer(s);
                sink = s;
                if(Integer.parseInt(test.nextToken()) == hash) {
                    
                    for(int i = 0; i < 4; i++) {
                        bookmarksIndex[i] = Integer.parseInt(test.nextToken());
                    }
                    
                    sink = "" + hash;
                    if(bookmarksIndex[0] == -1) {
                        bookmarksIndex[0] = index;
                    }
                    else if(bookmarksIndex[1] == -1) {
                        bookmarksIndex[1] = index;
                    }
                    else if(bookmarksIndex[2] == -1) {
                        bookmarksIndex[2] = index;
                    }
                    else {
                        bookmarksIndex[2] = index;
                    }
                    sink += " " + bookmarksIndex[0] + " " + bookmarksIndex[1] +
                            " " + bookmarksIndex[2] + " " + bookmarksIndex[3];
                    flag = true;
                    }
                pw.append(sink + "\n");
                s = br.readLine();
                
            }
            if(!flag) {
                pw.append(hash + " "+ index + " -1 -1 -1\n");
            }
        } 
        in.delete();
        out.renameTo(in);
    }

    /**
     * removes index to bookmarksIndex of file f
     * @param f file name of current comic
     * @param index index to remove to bookmarks
     * @throws FileNotFoundException if file is not present
     * @throws IOException if file read write error occurs
     * @return null 
     * @since v0.0.1
     */
    static void remove(File f, int index) throws FileNotFoundException, IOException {
        File in = new File (ExtractorModel.appDir + "/" + "bookmarks.txt");
        File out = new File(ExtractorModel.appDir + "/" + "tmp.txt");
        out.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(out); 
                PrintWriter pw = new PrintWriter(fout);
                FileInputStream fin = new FileInputStream(in)) {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String s = br.readLine();
            String sink;
            int hash = hash(f.getName());
            
            while(s != null) {
                StringTokenizer test = new StringTokenizer(s);
                sink = s;
                if(Integer.parseInt(test.nextToken()) == hash) {
                    
                    for(int i = 0; i < 4; i++) {
                        bookmarksIndex[i] = Integer.parseInt(test.nextToken());
                    }
                    
                    sink = "" + hash;
                    if(bookmarksIndex[0] == index) {
                        bookmarksIndex[0] = -1;
                    }
                    else if(bookmarksIndex[1] == index) {
                        bookmarksIndex[1] = -1;
                    }
                    else if(bookmarksIndex[2] == index) {
                        bookmarksIndex[2] = -1;
                    }
                    sink += " " + bookmarksIndex[0] + " " + bookmarksIndex[1] +
                            " " + bookmarksIndex[2] + " " + bookmarksIndex[3];                    
                    }
                pw.append(sink + "\n");
                s = br.readLine();
                
            }
            
        } 
        in.delete();
        out.renameTo(in);
    }
    
    /**
     * adds last page index to bookmarksIndex of file f
     * @param f file name of current comic
     * @param index index to add to bookmarks
     * @throws FileNotFoundException if file is not present
     * @throws IOException if file read write error occurs
     * @return null 
     * @since v0.0.1
     */
    static void setLastPageAsBookmark(File f, int index) throws FileNotFoundException, IOException {
        File in = new File (ExtractorModel.appDir + "/" + "bookmarks.txt");
        File out = new File(ExtractorModel.appDir + "/" + "tmp.txt");
        out.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(out); 
                PrintWriter pw = new PrintWriter(fout);
                FileInputStream fin = new FileInputStream(in)) {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String s = br.readLine();
            String sink;
            boolean flag = false;
            int hash = hash(f.getName());
            
            while(s != null) {
                StringTokenizer test = new StringTokenizer(s);
                sink = s;
                if(Integer.parseInt(test.nextToken()) == hash) {
                    
                    for(int i = 0; i < 4; i++) {
                        bookmarksIndex[i] = Integer.parseInt(test.nextToken());
                    }
                    
                    sink = "" + hash;
                    bookmarksIndex[3] = index;
                    
                    sink += " " + bookmarksIndex[0] + " " + bookmarksIndex[1] +
                            " " + bookmarksIndex[2] + " " + bookmarksIndex[3];
                    flag = true;
                    }
                pw.append(sink + "\n");
                s = br.readLine();
            }
            if(!flag) {
                pw.append(hash + " -1 -1 -1 " + index + "\n");
            }
        }
        
        in.delete();
        out.renameTo(in);
    }
    
    /**
     * @return integer array of bookmark indexes
     * @since v0.0.1 
     */
    public static int[] getBookmarksIndex() {
        return bookmarksIndex;
    }
    
    /**
     * @return true - index is among bookmarks index 
     * false - index is not among bookmarks index
     * @param index to compare with bookmarksIndex
     * @since v0.0.1 
     */
    public static boolean isIndexBookmarked(int index) {
        return index == bookmarksIndex[0] || index == bookmarksIndex[1] ||
               index == bookmarksIndex[2] || index == bookmarksIndex[3];
    }
    
    public static void main(String args[]) {
        new MainFrame(new ArchiveManager());
    }
}
