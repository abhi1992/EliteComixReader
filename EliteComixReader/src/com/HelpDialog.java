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
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The dialog to be displayed when help button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class HelpDialog extends JDialog{
    
    private JLabel jLabel1, jLabel2;
    private JTabbedPane tab;
    private JEditorPane aboutPane, helpPane;
    private JPanel jPanel1, jPanel2, aboutPanel, helpPanel;
    private JButton jButton1, jButton2;
    
    /**
     * Constructor
     * @param mainFrame the frame on which the dialog is called
     * @param i an integer denoting cycle focus up
     */
    HelpDialog(final MainFrame mainFrame) {
        
        tab = new JTabbedPane();
        
        setTitle("Key Board Shortcuts");
        //setAutoRequestFocus(true);
        setLayout(new BorderLayout());
        jLabel1 = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/about2.png")));
        aboutPane = new JEditorPane();
        aboutPane.setContentType("text/html");
        aboutPane.setEditable(false);
        aboutPane.setText("<Html><Font color = '#515151' size = '4'>"
                + "<Font size = '5' color = '#333333'><B><i>  CBR Reader</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>***********************************</B></Font><br>"
                + "<Font size = '5' color = '#990000'><B>- by Abhishek Banerjee</B></Font><br>"
                + "email - <Font size = '5' color = '#990000'><B>abhishekbanerjee1992@ymail.com</B></Font><br>"
                + "website - <Font size = '5' color = '#990000'><B></B></Font><br>"
                + "Liscence - <A href = 'http://www.gnu.org/licenses/'>GPL3</A><br>"
                + "<Font size = '5' color = '#101010'><B>***********************************</b></Font><br>"
                + ".cbr and .rar files are Loaded using <A href = "
                + "'https://github.com/edmund-wagner/junrar/'>junrar</A><br>"
                + "Drag n Drop feature is enabled by <A href = "
                + "'http://iharder.sourceforge.net/current/java/filedrop/'>File Drop</A><br>"
                + "Icons downloaded from <A href = 'http://findicons.com'>FindIcons</A></html>");
        
        aboutPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if(Desktop.isDesktopSupported()) {
                        try {        
                            try {
                                Desktop.getDesktop().browse(e.getURL().toURI());
                            } catch (IOException ex) {
                            }
                        } catch (URISyntaxException ex) {
                        }
                    }
                }
            }
        });
        
        jButton1 = new JButton("Close");
        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //mainFrame.setEnabled(true);
                dispose();
            }
        });
        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        
        jPanel1.add(jButton1);
        aboutPanel = new JPanel(new BorderLayout());
        aboutPanel.add(jLabel1, BorderLayout.WEST);
        
        
        aboutPanel.add(aboutPane, BorderLayout.CENTER);
        aboutPanel.add(jPanel1, BorderLayout.SOUTH);
        
        tab.addTab("About", aboutPanel);
        tab.transferFocusDownCycle();
        helpPane = new JEditorPane();
        helpPane.setContentType("text/html");
        helpPane.setEditable(false);
        
        helpPane.setText("<Html><Font color = '#515151' size = '4'>"
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
        
        jLabel2 = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/help1.png")));
        
        helpPanel = new JPanel(new BorderLayout());
        helpPanel.add(jLabel2, BorderLayout.WEST);
        
        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        
        jButton2 = new JButton("Close");
        jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //mainFrame.setEnabled(true);
                dispose();
            }
        });
        
        jPanel2.add(jButton2);
        
        helpPanel.add(helpPane, BorderLayout.CENTER);
        helpPanel.add(jPanel2, BorderLayout.SOUTH);
        
        tab.addTab("Help", helpPanel);
        
        setMinimumSize(new Dimension(500, 600));
        Point p = mainFrame.getLocation();
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y + 
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        getContentPane().add(tab);
        setVisible(true);
        tab.transferFocusUpCycle();
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
