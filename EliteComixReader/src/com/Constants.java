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

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Constants
 * @author Abhishek Banerjee
 * @version v0.0.8
 * @since v0.0.8
 */
public class Constants {
    
    public static final String defaultValues[] = {"", "", "System", "javax.swing.plaf.nimbus.NimbusLookAndFeel"
        , "com.sun.java.swing.plaf.motif.MotifLookAndFeel", "javax.swing.plaf.metal.MetalLookAndFeel"
        , "", "500", "500", "", "50", "50", "1", "false", "false", "false", "" 
        , "0", "0", "0" , "user.home", "10", "", "79", "83", "37", "39", "74"
        , "114", "115", "87", "57", "48", "27", "91", "49", "93", "84", "65", "66"
        , "75", "88", "72", "81", "112"}, 
            
            defaultTags[] = {"Properties", "Laf"
                , "Default", "Nimbus", "Motif", "Metal", "Size", "Height", "Width"
                , "Location", "X", "Y", "fitWidth", "fullscreen", "alwaysOnTop", "Maximized"
                , "Color", "Red", "Green", "Blue", "ComicsPath", "Scrollbar", "Keys", "Open"
                , "Save", "Left", "Right", "GoTO", "PrevComic", "NextComic", "FitWidth"
                , "RotateLeft", "RotateRight", "Fullscreen", "ZoomOut", "OrigImage", "ZoomIn"
                , "AlwaysOnTop", "AddBookmark", "BookmarksManager", "KeyboardShortcuts"
                , "Settings", "Help", "Exit", "ToolBar"};    
    public static ArrayList<Integer> availableKeys, assignedKeys;
    
    public Constants() {
        availableKeys = new ArrayList<>();
        assignedKeys = new ArrayList<>();
        
    }
    
    void setAssignedKeys() {
        
    }
    
    void setAvailableKeys() {
        for(int i = 0; i < 200; i++) {
            if(!KeyEvent.getKeyText(i).contains("Unknown")) {
                availableKeys.add(i);
            }
        }
    }
    
    public ArrayList<String> getAssignedKeyNames() {
        ArrayList<String> arr = new ArrayList();
        
        for(int i : availableKeys) {
            arr.add(KeyEvent.getKeyText(i));
        }
        return arr;
    }
    
    public static void main(String args[]) {
        for(int r = 0; r < 300; r++)
        System.out.println(r + " : " + KeyEvent.getKeyText(r));
    }
}
