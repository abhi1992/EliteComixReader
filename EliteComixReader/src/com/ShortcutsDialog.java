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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The dialog to be displayed when Key Board Shortcuts button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class ShortcutsDialog extends JDialog{
    
    private JLabel jLabel;
    private JEditorPane jEditorPane;
    private JPanel jPanel;
    private JButton jButton;
    
    /**
     * Constructor
     * @param mainFrame the frame on which the dialog is called
     * @param i an integer denoting cycle focus up
     */
    ShortcutsDialog(final MainFrame mainFrame) {
        
        setTitle("Key Board Shortcuts");
        setLayout(new BorderLayout());
        jLabel = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/keyboard.png")));
        jEditorPane = new JEditorPane();
        jEditorPane.setContentType("text/html");
        jEditorPane.setEditable(false);
        jEditorPane.setText("<Html><Font color = '#515151' size = '4'>"
                + "<Font size = '5' color = '#333333'><B><i>  Key Board Events</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>****************************</B></Font><br>"
                + "<Font size = '5' color = '#990000'><B>O</B></Font> - Open Comic / Folder<br>"
                + "<Font size = '5' color = '#990000'><B>S</B></Font> - Save Page<br>"
                + "<Font size = '5' color = '#990000'><B>Left Arrow</B></Font> - Previous Page<br>"
                + "<Font size = '5' color = '#990000'><B>Right Arrow</B></Font> - Next Page<br>"
                + "<Font size = '5' color = '#990000'><B>W</B></Font> - Fit Width / Height<br>"
                + "<Font size = '5' color = '#990000'><B>Escape</B></Font> - Toggle Fullscreen Mode<br>"
                + "<Font size = '5' color = '#990000'><B>T</B></Font> - Toggle Always On Top<br>"
                + "<Font size = '5' color = '#990000'><B>J</B></Font> - Jump To Page<br>"
                + "<Font size = '5' color = '#990000'><B>B</B></Font> - Add / Remove Bookmark<br>"
                + "<Font size = '5' color = '#990000'><B>A</B></Font> - Show Bookmarks<br>"
                + "<Font size = '5' color = '#990000'><B>K</B></Font> - Show Key Board Shortcuts<br>"
                + "<Font size = '5' color = '#990000'><B>H</B></Font> - Help<br>"
                + "<Font size = '5' color = '#990000'><B>Q</B></Font> - Quit<br><br>"
                + "<Font size = '5' color = '#333333'><B><i>  Mouse Events</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>****************************</b></Font><br>"
                + "<Font size = '5' color = '#990000'><B>Double Click</B></Font> - Toggle Fullscreen Mode<br>"
                + "<Font size = '5' color = '#990000'><B>Right Click</B></Font> - Popup Menu</html>");
        
        jButton = new JButton("Close");
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                dispose();
            }
        });
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        
        jPanel.add(jButton);
        getContentPane().add(jLabel, BorderLayout.WEST);
        getContentPane().add(jEditorPane, BorderLayout.CENTER);
        getContentPane().add(jPanel, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(500, 600));
        Point p = mainFrame.getLocation();
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y + 
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        setVisible(true);
        
        pack();
    }
    
    /**
     * main method for testing.
     * @param args to pass command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager s = new ArchiveManager();
                MainFrame ff = new MainFrame(s);
    }
    
}
