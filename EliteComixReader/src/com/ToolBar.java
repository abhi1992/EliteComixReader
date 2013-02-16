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
import java.io.File;
import javax.swing.*;

/**
 * ToolBar for Elite Comix Reader
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class ToolBar extends JToolBar {
    
    private static JButton open, left, right, about, exit,  fullscreen,  goTo,
            keyShorcuts, save, bookmarksManager;
    private static JToggleButton fitWidth, alwaysOnTop, addBookmark;
    private static ImagePanel imagePanel;
    private static MainFrame mainFrame;
    private static ArchiveManager archiveManager;
    
    /**
     * ToolBar Constructor
     * 
     * @since v0.0.1
     */
    ToolBar(ImagePanel i, MainFrame f, ArchiveManager am) {
        
        ToolBar.imagePanel = i;
        ToolBar.mainFrame = f;
        ToolBar.archiveManager = am;
        setFloatable(false);
        //setBorderPainted(false);
        setEnabled(false);
        setFocusable(false);
        setFocusCycleRoot(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        open = createButton(open, "/Resources/open1.png", KeyEvent.VK_O, "Open Comic");
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton0ActionPerformed(evt);
                
            }
        });
        add(open);
        
        save = createButton(save, "/Resources/document_save1.png", KeyEvent.VK_S
                , "Save Image");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton10ActionPerformed(evt);
                
            }
        });
        add(save);
        
        addSeparator(new Dimension(10, 30));
        
        left = createButton(left, "/Resources/go_previous_black1.png", KeyEvent.VK_LEFT
                , "Previous Page");
        left.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(left);

        right = createButton(right, "/Resources/go_next_black1.png", KeyEvent.VK_RIGHT
                , "Next Page");
        right.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(right);
        goTo = createButton(goTo, "/Resources/view_refresh1.png", KeyEvent.VK_J
                , "Jump To Page");
        goTo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(goTo);
        
        addSeparator(new Dimension(10, 30));
        
        fitWidth = new JToggleButton(new ImageIcon(getClass().getResource("/Resources/arrows1.png")));
        fitWidth.setMnemonic(KeyEvent.VK_W);
        fitWidth.setToolTipText("Fit Width");
        fitWidth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(fitWidth);
        fullscreen = createButton(fullscreen, "/Resources/full screen1.png", 
                KeyEvent.VK_ESCAPE, "Fullscreen");
        fullscreen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(fullscreen);
        
        alwaysOnTop = new JToggleButton(new ImageIcon(getClass().getResource("/Resources/alwaysontop.png")));
        alwaysOnTop.setMnemonic(KeyEvent.VK_T);
        alwaysOnTop.setToolTipText("Always On Top");
        alwaysOnTop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(alwaysOnTop);
        
        addSeparator(new Dimension(10, 30));
        
        addBookmark = new JToggleButton(new ImageIcon(getClass()
                .getResource("/Resources/star_none1.png")));
        addBookmark.setMnemonic(KeyEvent.VK_B);
        addBookmark.setToolTipText("Add Bookmark");
        addBookmark.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        add(addBookmark);
        
        bookmarksManager = createButton(bookmarksManager, "/Resources/user_bookmarks1.png", 
                KeyEvent.VK_A, "Show Bookmarks");
        bookmarksManager.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        add(bookmarksManager);
        
        addSeparator(new Dimension(10, 30));
        
        keyShorcuts = createButton(keyShorcuts, "/Resources/keyboard1.png", 
                KeyEvent.VK_K, "Key Board Shortcuts");
        keyShorcuts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        add(keyShorcuts);
        
        about = createButton(about, "/Resources/help2.png", 
                KeyEvent.VK_H, "Help");
        about.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(about);
        
        exit = createButton(exit, "/Resources/dialog_close1.png", 
                KeyEvent.VK_Q, "Exit");
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(exit);
        transferFocusUpCycle();
        setAddBookmark();
    }

    /**
     * adds a new button to the tool bar
     * @param j
     * @param url
     * @param key
     * @param toolTip
     * @return JButton with url icon, key mnemonic, toolTip as tool tip text
     * @since v0.0.1
     */
    final JButton createButton(JButton j, String url, int key, String toolTip) {
        j = new JButton(new ImageIcon(getClass().getResource(url)));
        j.setMnemonic(key);
        j.setToolTipText(toolTip);
        return j;
    }
    
    /**
     * sets the visibility of the Tool bar
     * @param 
     * @return null
     * @since v0.0.1 
     */
    void hideToolBar(boolean b) {
        setVisible(b);
    }
    
    /**
     * sets the state of the toggle button addBookmark
     * @param null
     * @return null
     * @since v0.0.1 
     */
    static void setAddBookmark() {
        addBookmark.setSelected(Bookmarks.isIndexBookmarked(
                imagePanel.getIndex()));
        addBookmark.transferFocusUpCycle();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String args[])
    {
        JFrame f = new JFrame();
        //f.add(new ToolBar(),BorderLayout.NORTH);
        f.add(new JPanel());
        //Popup p;
        try {
            
                UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(f);
            } catch (ClassNotFoundException |
                    InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                System.err.println("Could not load LookAndFeel " +e.getMessage());
            }
        f.setMinimumSize(new Dimension(200, 200));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    //</editor-fold>
    
    /**
     * open button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton0ActionPerformed(ActionEvent evt) {
     MainFrame.open(archiveManager);
     open.transferFocusUpCycle();
    }
    
    /**
     * left button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
     imagePanel.prevPage(archiveManager);
     left.transferFocusUpCycle();
     setAddBookmark();
    }
    
    /**
     * right button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton2ActionPerformed(ActionEvent evt) {
    imagePanel.nextPage(archiveManager);
    right.transferFocusUpCycle();
    setAddBookmark();
    }
    
    /**
     * fit Width button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton3ActionPerformed(ActionEvent evt) {
    mainFrame.fitImage(isFitWidthSelected());
    fitWidth.transferFocusUpCycle();
    }
    
    /**
     * full screen button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton4ActionPerformed(ActionEvent evt) {
    mainFrame.fullscreen();
    fullscreen.transferFocusUpCycle();
    }
    
    /**
     * always on top button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton5ActionPerformed(ActionEvent evt) {
    mainFrame.alwaysOnTop();
    alwaysOnTop.transferFocusUpCycle();
    }
    
    /**
     * go to button action
     * @param evt ActionEvent 
     * @return null
     * @since v0.0.1 
     */
    private void jButton6ActionPerformed(ActionEvent evt) {
        try{
            String res = JOptionPane.showInputDialog(mainFrame, "Enter page no: "
            +"( 0 - " + ArchiveManager.getSize() +" )");
            //System.out.print(res);
            if(res != null) {
                int page = Integer.parseInt(res);
                if(page > -1)
                    imagePanel.goToPage(page);
            }
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(mainFrame, "Enter Integers only!!");
        }
        goTo.transferFocusUpCycle();
        setAddBookmark();
    }
    
    /**
     * about button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton7ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new HelpDialog(mainFrame, 2);
        //about.transferFocusUpCycle();
    }
    
    /**
     * exit button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton8ActionPerformed(ActionEvent evt) {
        if(MainFrame.getFile() != null)
            BookmarksManager.setLastPageAsBookmark(MainFrame.getFile(), imagePanel.getIndex());
        System.exit(0);
    }
    
    /**
     * key shortcuts button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton9ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new ShortcutsDialog(mainFrame, 1);
        //about.transferFocusUpCycle();
    }
    
    /**
     * save button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton10ActionPerformed(ActionEvent evt) {
        mainFrame.save();
        save.transferFocusUpCycle();
    }
    
    /**
     * add bookmark button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton11ActionPerformed(ActionEvent evt) {
        
        File file = MainFrame.getFile();
        if(file != null)
        
            if(addBookmark.isSelected()) {
                BookmarksManager.add(file, imagePanel.getIndex());
                addBookmark.transferFocusUpCycle();
            
            } else {
                BookmarksManager.remove(file, imagePanel.getIndex());
                addBookmark.transferFocusUpCycle();    
            }
          else
                addBookmark.setSelected(false);   
    }
    
    /**
     * bookmarks manager button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1 
     */
    private void jButton12ActionPerformed(ActionEvent evt) {
        new BookmarksDialog(mainFrame, 3);
    }

    /**
     * sets fit width toggle button state
     * @return null
     * @since v0.0.1 
     */
    static void setFitToggle() {
        boolean b = true;
        if(imagePanel.getMode() == 0)
            b = false;
        else if(imagePanel.getMode() == 1)
            b = true;
        fitWidth.setSelected(b);
    }
    
    /**
     * sets addBookmark toggle button state
     * @return null
     * @since v0.0.1 
     */
    static void setAddBookmarkToggle() {
        boolean b = true;
        if(addBookmark.isSelected())
            b = false;
        else
            b = true;
        addBookmark.setSelected(b);
    }
    
    /**
     * sets always on top toggle button state
     * @return null
     * @since v0.0.1 
     */
    static void setAlwaysOnTopToggle() {
        alwaysOnTop.setSelected(mainFrame.isAlwaysOnTop());
    }
    
    /**
     * sets focus away from keyShortcuts or about button
     * @return null
     * @since v0.0.1 
     */
    static void cycleFocus(int i) {
        if(i == 1) {
            keyShorcuts.transferFocusUpCycle();
        } else if(i == 2) {
            about.transferFocusUpCycle();
        } else if(i == 3) {
            bookmarksManager.transferFocusUpCycle();
        }
    }
    
    /**
     * 
     * @return boolean state of toggle button fitWidth
     * @since v0.0.1 
     */
    static boolean isFitWidthSelected() {
        return fitWidth.isSelected();
    }
    
    /**
     * 
     * @return boolean state of toggle button fitWidth
     * @since v0.0.1 
     */
    static boolean isAddBookmarkSelected() {
        return addBookmark.isSelected();
    }
    
}
