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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Abhishek Banerjee
 */
public class HelpDialog extends JDialog{
    
    private JLabel jLabel1, jLabel2;
    private JTabbedPane tab;
    private JEditorPane aboutPane, helpPane;
    private JPanel jPanel1, jPanel2, aboutPanel, helpPanel;
    private JButton jButton1, jButton2;
    
    HelpDialog(final MainFrame mainFrame,final int i) {
        
        tab = new JTabbedPane();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ToolBar.cycleFocus(i);
            }
        });
        Point p = mainFrame.getLocation();
        setLocation(p.x + 50, p.y + 50);
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
                + "Liscence - <A href = ''>GPL3</A><br>"
                + "<Font size = '5' color = '#101010'><B>***********************************</b></Font><br>"
                + ".cbr and .rar files are Loaded using <A href = ''>junrar</A><br>"
                + "Drag n Drop feature is enabled by <A href = ''>File Drop</A><br>"
                + "Icons downloaded from <A href = 'http://findicons.com'>FindIcons</A></html>");
        
        jButton1 = new JButton("Close");
        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //mainFrame.setEnabled(true);
                dispose();
                
                ToolBar.cycleFocus(i);
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
                + "<Font size = '5' color = '#990000'><B>K</B></Font> - Show Key Board Shortcuts<br>"
                + "<Font size = '5' color = '#990000'><B>H</B></Font> - Help<br>"
                + "<Font size = '5' color = '#990000'><B>Q</B></Font> - Quit<br><br>"
                + "<Font size = '5' color = '#333333'><B><i>  Mouse Events</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>****************************</b></Font><br>"
                + "<Font size = '5' color = '#990000'><B>Double Click</B></Font> - Toggle Fullscreen Mode<br>"
                + "<Font size = '5' color = '#990000'><B>Right Click</B></Font> - Popup Menu</html>");
        /**/
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
                ToolBar.cycleFocus(i);
            }
        });
        
        jPanel2.add(jButton2);
        
        helpPanel.add(helpPane, BorderLayout.CENTER);
        helpPanel.add(jPanel2, BorderLayout.SOUTH);
        
        tab.addTab("Help", helpPanel);
        
        setSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(500, 600));
        
        getContentPane().add(tab);
        setVisible(true);
        pack();
    }
    
    public static void main(String args[]) {
        ArchiveManager s = new ArchiveManager();
                MainFrame ff = new MainFrame(s);
    }
    
}
