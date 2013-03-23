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

import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
/**
 *
 * @author Abhishek Banerjee
 */
public class PopupMenu extends JPopupMenu{

    private JMenuItem open, left, right, fitWidth, settings, origSize, zoomOut,
            rotateLeft, rotateRight, 
            zoomIn, fullscreen, alwaysOnTop, goTo, shortcuts, nextComic, 
            prevComic, about, exit, save, addBookmark, bookmarksManager;
    private JMenu zoom, rotate;
    private static ImagePanel imagePanel;
    private static MainFrame mainFrame;
    private static ArchiveManager archiveManager;



    PopupMenu(ImagePanel i, MainFrame f, ArchiveManager am) {

        PopupMenu.imagePanel = i;
        PopupMenu.mainFrame = f;
        PopupMenu.archiveManager = am;

        open = new JMenuItem("Open Comic / Folder");
        open.setIcon(new ImageIcon(getClass().getResource("/Resources/open1.png")));
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton0ActionPerformed(evt);
            }
        });
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0));
        add(open);

        save = new JMenuItem("Save Image");
        save.setIcon(new ImageIcon(getClass().getResource("/Resources/document_save1.png")));
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
        add(save);

        addSeparator();

        left = new JMenuItem("Previous Page");
        left.setIcon(new ImageIcon(getClass().getResource("/Resources/go_previous_black1.png")));
        left.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        left.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        add(left);

        right = new JMenuItem("Next Page");
        right.setIcon(new ImageIcon(getClass().getResource("/Resources/go_next_black1.png")));
        right.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        right.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        add(right);

        goTo = new JMenuItem("Jump To Page");
        goTo.setIcon(new ImageIcon(getClass().getResource("/Resources/view_refresh1.png")));
        goTo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0));
        add(goTo);

        addSeparator();

        prevComic = new JMenuItem("Previous Comic");
        prevComic.setIcon(new ImageIcon(getClass().getResource("/Resources/arrow_left_double.png")));
        prevComic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        prevComic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0));
        add(prevComic);

        nextComic = new JMenuItem("Next Comic");
        nextComic.setIcon(new ImageIcon(getClass().getResource("/Resources/arrow_right_double1.png")));
        nextComic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        nextComic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0));
        add(nextComic);

        addSeparator();

        fitWidth = new JMenuItem("Fit Width / Height");
        fitWidth.setIcon(new ImageIcon(getClass().getResource("/Resources/arrows1.png")));
        fitWidth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        fitWidth.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0));
        add(fitWidth);
        
        rotateLeft = new JMenuItem("Rotate Image Left");
        rotateLeft.setIcon(new ImageIcon(getClass().getResource("/Resources/rotate_ccw.png")));
        rotateLeft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        rotateLeft.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0));
        
        rotateRight = new JMenuItem("Rotate Image Right");
        rotateRight.setIcon(new ImageIcon(getClass().getResource("/Resources/rotate_cw.png")));
        rotateRight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        rotateRight.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0));
        
        rotate = new JMenu("Rotate");
        rotate.add(rotateLeft);
        rotate.add(rotateRight);
        add(rotate);
        
        fullscreen = new JMenuItem("Toggle Fullscreen Mode");
        fullscreen.setIcon(new ImageIcon(getClass().getResource("/Resources/full screen1.png")));
        fullscreen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        fullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        add(fullscreen);
        
        zoomOut = new JMenuItem("Zoom Out");
        zoomOut.setIcon(new ImageIcon(getClass().getResource("/Resources/new_zoom_out.png")));
        zoomOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0));
        
        origSize = new JMenuItem("Original Image Size");
        origSize.setIcon(new ImageIcon(getClass().getResource("/Resources/zoom_original.png")));
        origSize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        origSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0));
        
        zoomIn = new JMenuItem("Zoom In");
        zoomIn.setIcon(new ImageIcon(getClass().getResource("/Resources/new_zoom_in.png")));
        zoomIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0));
        
        zoom = new JMenu("Zoom");
        zoom.add(zoomOut);
        zoom.add(origSize);
        zoom.add(zoomIn);
        add(zoom);
        
        alwaysOnTop = new JMenuItem("Always On Top");
        alwaysOnTop.setIcon(new ImageIcon(getClass().getResource("/Resources/alwaysontop.png")));
        alwaysOnTop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        alwaysOnTop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0));
        add(alwaysOnTop);

        addSeparator();

        addBookmark = new JMenuItem("Add / Remove Bookmark");
        addBookmark.setIcon(new ImageIcon(getClass().getResource("/Resources/star_none1.png")));
        addBookmark.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        addBookmark.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0));
        add(addBookmark);

        bookmarksManager = new JMenuItem("Show Bookmarks");
        bookmarksManager.setIcon(new ImageIcon(getClass().getResource("/Resources/user_bookmarks1.png")));
        bookmarksManager.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        bookmarksManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0));
        add(bookmarksManager);

        addSeparator();

        shortcuts = new JMenuItem("Key Board Shortcuts");
        shortcuts.setIcon(new ImageIcon(getClass().getResource("/Resources/keyboard1.png")));
        shortcuts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        shortcuts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0));
        add(shortcuts);

        settings = new JMenuItem("Settings");
        settings.setIcon(new ImageIcon(getClass().getResource("/Resources/configure_shortcuts1.png")));
        settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new SettingsDialog(mainFrame);
            }
        });
        settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0));
        add(settings);

        about = new JMenuItem("Help");
        about.setIcon(new ImageIcon(getClass().getResource("/Resources/help2.png")));
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0));
        add(about);

        exit = new JMenuItem("Exit");
        exit.setIcon(new ImageIcon(getClass().getResource("/Resources/dialog_close1.png")));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
        add(exit);


        //setVisible(true);
    }

    private void jButton0ActionPerformed(ActionEvent evt) {
         MainFrame.open(archiveManager);
         open.transferFocusUpCycle();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
         mainFrame.prevPage(archiveManager);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        mainFrame.nextPage(archiveManager);
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        ToolBar.setFitToggle();
        mainFrame.fitImage(ToolBar.isFitWidthSelected());

        //fitWidth.transferFocusUpCycle();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        mainFrame.fullscreen();
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        mainFrame.alwaysOnTop();
        ToolBar.setAlwaysOnTopToggle();
    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        mainFrame.jumpToPage();
    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new HelpDialog(mainFrame);
    }

    private void jButton8ActionPerformed(ActionEvent evt) {
        mainFrame.close();
    }

    private void jButton9ActionPerformed(ActionEvent evt) {
        shortcuts.transferFocusUpCycle();
        ShortcutsDialog shortcutsDialog = new ShortcutsDialog(mainFrame);

    }

    private void jButton10ActionPerformed(ActionEvent evt) {
        mainFrame.save();

    }

    /**
     * add bookmark button action
     * @param evt action event
     * @return null
     * @since v0.0.1
     */
    private void jButton11ActionPerformed(ActionEvent evt) {

        File ff = MainFrame.getFile();
        if(ff != null) {
            BookmarksManager.add(ff, imagePanel.getIndex());
            ToolBar.setAddBookmarkToggle();
        }
    }

    /**
     * bookmarks manager button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton12ActionPerformed(ActionEvent evt) {
        new BookmarksDialog(mainFrame);
    }

    /**
     * bookmarks manager button action
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton13ActionPerformed(ActionEvent evt) {
        File h = Settings.getPrevComicFile();
        if(h != null)
            MainFrame.displayComic(archiveManager, h);
//        else
//            prevComic.setEnabled(false);
    }

    /**
     * bookmarks manager button action
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton14ActionPerformed(ActionEvent evt) {
        File h = Settings.getNextComicFile();
        if(h != null)
            MainFrame.displayComic(archiveManager, h);

    }
    
    /**
     * OrigSize button action 
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton15ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex())) {
            ToolBar.setOrigSizeToggle();
            mainFrame.origSize(ToolBar.isOrigSizeSelected());
        }
    }
    
    /**
     * zoomOut button action 
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton16ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.zoomOut();
    }
    
    /**
     * zoomIn button action 
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton17ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.zoomIn();
    }

    /**
     * Rotate Left
     * @param evt ActonEvent
     * @return null
     * @since v0.0.4
     */
    private void jButton18ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.rotateLeft();
    }
    
    /**
     * Rotate Right
     * @param evt ActonEvent
     * @return null
     * @since v0.0.4
     */
    private void jButton19ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.rotateRight();
    }
    
  void showPopup(MouseEvent e) {

        show(e.getComponent(), e.getX(), e.getY());
        //setVisible(true);
}

    //<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String args[])
    {

        ArchiveManager ee = new ArchiveManager();
        MainFrame f = new MainFrame(ee);
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

}
