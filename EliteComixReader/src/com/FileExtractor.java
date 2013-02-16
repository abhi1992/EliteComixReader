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

/**
 * Extracts directories
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class FileExtractor extends ExtractorModel {
    
    /**
     * 
     * @param f an array of image files 
     * @since v0.0.1
     */
    FileExtractor(File[] f) {
        clear();
        addAll(f);
    }
    
    /**
     * 
     * @return size or no of files in the comic
     * @since v0.0.1
     */
    static int getSize() {
        return getFileSize();
    }
    
    
}
