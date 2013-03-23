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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The dialog to display the available bookmarks.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class BookmarksDialog extends JDialog{
    
    private BookmarkIcon image1, image2, image3, image4;
    private JLabel jLabel, title;
    private JPanel basePanel, rightPanel;
    private JButton jButton;
    
    /**
     * Constructor
     * @param mainFrame the frame on which the dialog is called
     * @param i an integer denoting cycle focus up
     */
    BookmarksDialog(final MainFrame mainFrame) {
        
        setResizable(false);
        setFocusable(false);
        ArrayList<BufferedImage> a = new ArrayList<>(4);
        a.addAll(BookmarksManager.getBookmarkImages());
        
        setTitle("Bookmarks");
        setLayout(new BorderLayout());
        jLabel = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/user_bookmarks.png")));
        title = new JLabel("Click on any bookmark to go to that page.");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("monospaced", 0, 20));
        
        image1 = new BookmarkIcon(a.get(0));
        image1.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    mainFrame.goToImage(Bookmarks.getBookmarksIndex()[0]);
                    dispose();
            }
            });
        image2 = new BookmarkIcon(a.get(1));
        image2.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    mainFrame.goToImage(Bookmarks.getBookmarksIndex()[1]);
                    dispose();
            }
            });
        image3 = new BookmarkIcon(a.get(2));
        image3.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    mainFrame.goToImage(Bookmarks.getBookmarksIndex()[2]);
                    dispose();
            }
            });
        image4 = new BookmarkIcon(a.get(3));
        image4.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    mainFrame.goToImage(Bookmarks.getBookmarksIndex()[3]);
                    dispose();
            }
            });
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 2));
        rightPanel.add(image1);
        rightPanel.add(image2);
        rightPanel.add(image3);
        rightPanel.add(image4);
        
        jButton = new JButton("Close");
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                dispose();
            }
        });
        
        basePanel = new JPanel();
        basePanel.setLayout(new FlowLayout());
        
        basePanel.add(jButton);
        
        getContentPane().add(title, BorderLayout.NORTH);
        getContentPane().add(jLabel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
        getContentPane().add(basePanel, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(600, 600));
        Point p = mainFrame.getLocation();
        setLocation(p.x + (Math.abs(mainFrame.getWidth() - getWidth())) / 2, p.y + 
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        setVisible(true);
        setModalityType(
           Dialog.ModalityType.TOOLKIT_MODAL);
        //jButton.transferFocusUpCycle();
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

