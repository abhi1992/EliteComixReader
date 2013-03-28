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
    private JScrollPane scroll;
    
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
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.OPEN - Constants.START_VAL))
                +"</B></Font> - Open Comic / Folder<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.SAVE - Constants.START_VAL))
                + "</B></Font> - Save Page<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.LEFT - Constants.START_VAL))
                + "</B></Font> - Previous Page<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.RIGHT - Constants.START_VAL))
                + "</B></Font> - Next Page<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.FIT_WIDTH - Constants.START_VAL))
                + "</B></Font> - Fit Width / Height<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.ZOOM_OUT - Constants.START_VAL))
                + "</B></Font> - Zoom Out<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.ORIGIMAGE - Constants.START_VAL))
                + "</B></Font> - Original Image Size<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.ZOOM_IN - Constants.START_VAL))
                + "</B></Font> - Zoom In<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.FULLSCREEN - Constants.START_VAL))
                + "</B></Font> - Toggle Fullscreen Mode<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.ALWAYS_ON_TOP - Constants.START_VAL))
                + "</B></Font> - Toggle Always On Top<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.GOTO - Constants.START_VAL))
                + "</B></Font> - Jump To Page<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.ADD_BOOKMARK - Constants.START_VAL))
                + "</B></Font> - Add / Remove Bookmark<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.BOOKMARKS_MANAGER - Constants.START_VAL))
                + "</B></Font> - Show Bookmarks<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.KEYBOARDS_SHORTCUTS - Constants.START_VAL))
                + "</B></Font> - Show Key Board Shortcuts<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.TOOL_BAR - Constants.START_VAL))
                + "</B></Font> - Hide / Show Toolbar<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.SETTINGS - Constants.START_VAL))
                + "</B></Font> - Settings<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.PAGE_INFO - Constants.START_VAL))
                + "</B></Font> - Show Page no in panel<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.TIME - Constants.START_VAL))
                + "</B></Font> - Show time in panel<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.PAGE_NO - Constants.START_VAL))
                + "</B></Font> - Show Page no in Toolbar<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.HELP - Constants.START_VAL))
                + "</B></Font> - Help<br>"
                + "<Font size = '5' color = '#990000'><B>"
                + KeyEvent.getKeyText(Constants.getAssignedKeys().get(Constants.EXIT - Constants.START_VAL))
                + "</B></Font> - Quit<br><br>"
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
        setMinimumSize(new Dimension(500, 600));
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jEditorPane.setPreferredSize(new Dimension(300, 550));
        scroll = new JScrollPane(jEditorPane);
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMinimum());
        jPanel.add(jButton);
        getContentPane().add(jLabel, BorderLayout.WEST);
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(jPanel, BorderLayout.SOUTH);
        
        Point p = mainFrame.getLocation();
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y + 
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        setModalityType(
           Dialog.ModalityType.TOOLKIT_MODAL);
        
        setVisible(true);
        //pack();
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
