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
/**
 * An interface which every file format extractor must implement
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public interface Extractor {
    
    /**
     * 
     * @param file the name of the input file 
     * @param os the output stream to write to
     * @return integer denoting success or failure
     * @since v0.0.1
     */
    public int writeToOutputStream(File file, ByteArrayOutputStream os);
    
    
}
