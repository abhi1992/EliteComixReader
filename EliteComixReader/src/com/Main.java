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
 * Main Class
 * @author Abhishek
 * @since v0.0.1
 */
public class Main {
  
    /**
     * Main method
     * @param args command line arguments
     */
     public static void main(String args[]) {
        ArchiveManager e = new ArchiveManager();
        MainFrame mainFrame = new MainFrame(e);
        mainFrame = null;
    }
    
}
