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
    private JScrollPane help;

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
                new ImageIcon(getClass().getResource("/Resources/elite_comix_reader.png")));
        aboutPane = new JEditorPane();
        aboutPane.setContentType("text/html");
        aboutPane.setEditable(false);
        aboutPane.setText("<Html><Font color = '#515151' size = '4'>"
                + "<Font size = '5' color = '#333333'><B><i>Elite Comix Reader</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>***********************************</B></Font><br>"
                + "<Font size = '5' color = '#990000'><B>- by Abhishek Banerjee</B></Font><br>"
                + "email - <Font size = '5' color = '#990000'><B>abhishekbanerjee1992@ymail.com</B></Font><br>"
                + "website - <A href = 'http://sourceforge.net/projects/elitecomixread/'>sourceforge.net/projects/elitecomixread/</A><br>"
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
                + "<Font size = '5' color = '#333333'><B><i>Quick start guide</i></B></Font><br>"
                + "<Font size = '5' color = '#101010'><B>****************************</B></Font><br>"
                + "<Font size = '4' color = '#333333'><B><i>Features :</i></B></Font><br>"
                + "Elite Comix Reader is a simple java based comic book reader.<br>"
                + "<ul><li>Multiplatform</li>"
                + "<li>cbr, cbz, rar, zip and folders support</li>"
                + "<li>jpeg, gif, png, wbmp and bmp image support</li>"
                + "<li>comic reading using keyboard and mouse</li>"
                + "<li>fast open and comic navigation</li>"
                + "<li>fullscreen mode</li>"
                + "<li>fit width and fit height modes.</li>"
                + "<li>Zoom In, Zoom Out and Original Image Size modes.</li>"
                 + "<li>Rotate Image Left and Right modes.</li>"
                + "<li>Comic bookmarks</li></ul>"
                + "<Font size = '4' color = '#333333'><B><i>Settings :</i></B></Font><br>"
                + "Comics Path : set your favourite comic path. Open comic dialog will open that path by default.<br>"
                + "Background color change : set your favourite background color.<br>"
                + "Look and Feel : set your favourite look and feel. "
                + "You will need to restart the reader for changes to be saved.<br>"
                + "<Font color = '#515151' size = '4'>"
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
        help = new JScrollPane(helpPane);
        
        helpPanel.add(help, BorderLayout.CENTER);

        helpPanel.add(jPanel2, BorderLayout.SOUTH);

        tab.addTab("Help", helpPanel);

        setMinimumSize(new Dimension(500, 600));
        Point p = mainFrame.getLocation();
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y +
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        getContentPane().add(tab);
        tab.setFocusable(false);
        tab.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                help.getVerticalScrollBar().setValue(help.getVerticalScrollBar().getMinimum());
            }
        });
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
