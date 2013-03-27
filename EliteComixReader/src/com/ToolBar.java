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
import java.io.FilenameFilter;
import javax.swing.*;

/**
 * ToolBar for Elite Comix Reader
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class ToolBar extends JToolBar {

    private static JButton open, left, right, about, exit,  fullscreen,  goTo,
            zoomIn, zoomOut, rotateLeft, rotateRight, translator,  
            keyShorcuts, save, bookmarksManager, settings, nextComic, prevComic;
    private static JToggleButton fitWidth, alwaysOnTop, addBookmark, origSize;
    private static ImagePanel imagePanel;
    private static MainFrame mainFrame;
    private static ArchiveManager archiveManager;
    private static JTextField page;
    private static JLabel totalPages;
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
        //setEnabled(false);
        setFocusable(false);
        setFocusCycleRoot(true);
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

        prevComic = createButton(prevComic, "/Resources/arrow_left_double.png", KeyEvent.CTRL_DOWN_MASK+KeyEvent.VK_LEFT
                , "Previous Comic");
        prevComic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        add(prevComic);

        nextComic = createButton(nextComic, "/Resources/arrow_right_double1.png", KeyEvent.CTRL_DOWN_MASK+KeyEvent.VK_RIGHT
                , "Next Comic");
        nextComic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        add(nextComic);

        addSeparator(new Dimension(10, 30));
        
        fitWidth = new JToggleButton(new ImageIcon(getClass().getResource("/Resources/arrows1.png")));
        fitWidth.setMnemonic(KeyEvent.VK_W);
        fitWidth.setToolTipText("Fit Width");
        fitWidth.setFocusable(false);
        fitWidth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        fitWidth.setPreferredSize(new Dimension(32, 32));
        add(fitWidth);
        
        rotateLeft = createButton(rotateLeft, "/Resources/rotate_ccw.png", 
                KeyEvent.VK_9
                , "Rotate Left");
        rotateLeft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        add(rotateLeft);
        
        rotateRight = createButton(rotateRight, "/Resources/rotate_cw.png", 
                KeyEvent.VK_0
                , "Rotate Right");
        rotateRight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        add(rotateRight);
        
        fullscreen = createButton(fullscreen, "/Resources/full screen1.png",
                KeyEvent.VK_ESCAPE, "Fullscreen");
        fullscreen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(fullscreen);
        
        addSeparator(new Dimension(10, 30));
        
        zoomOut = createButton(zoomOut, "/Resources/new_zoom_out.png", 
                KeyEvent.VK_CLOSE_BRACKET
                , "Zoom Out");
        zoomOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        add(zoomOut);
        
        origSize = new JToggleButton(new ImageIcon(getClass().getResource("/Resources/zoom_original.png")));
        origSize.setMnemonic(KeyEvent.VK_1);
        origSize.setToolTipText("Original Size");
        origSize.setFocusable(false);
        origSize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        add(origSize);
        
        zoomIn = createButton(zoomIn, "/Resources/new_zoom_in.png", 
                KeyEvent.VK_OPEN_BRACKET
                , "Zoom In");
        zoomIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        add(zoomIn);

        addSeparator(new Dimension(10, 30));
        
        alwaysOnTop = new JToggleButton(new ImageIcon(getClass().getResource("/Resources/alwaysontop.png")));
        alwaysOnTop.setMnemonic(KeyEvent.VK_T);
        alwaysOnTop.setToolTipText("Always On Top");
        alwaysOnTop.setFocusable(false);
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
        addBookmark.setFocusable(false);
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
        
//        translator = createButton(translator, "/Resources/translate1.png",
//                KeyEvent.VK_R, "Translator");
//        translator.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                jButton20ActionPerformed(evt);
//            }
//        });
//        add(translator);
        
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
        
        addSeparator(new Dimension(10, 30));
        
        page = new JTextField();
        page.setEditable(false);
        page.setText(""+imagePanel.getIndex());
        page.setVisible(Settings.isPageNo());
        page.setPreferredSize(new Dimension(24, 24));
        page.setHorizontalAlignment(JTextField.CENTER);
        add(page);
        
        totalPages = new JLabel();
        totalPages.setText(" / " + ArchiveManager.getSize());
        totalPages.setPreferredSize(new Dimension(24, 24));
        totalPages.setVisible(Settings.isPageNo());
        add(totalPages);
        
        settings = createButton(settings, "/Resources/configure_shortcuts1.png",
                KeyEvent.VK_X, "Settings");
        settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                settings.transferFocusUpCycle();
                new SettingsDialog(mainFrame);
            }
        });
        add(settings);

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
        j.setFocusable(false);
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
    }

    /**
     * left button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
        mainFrame.prevPage(archiveManager);
    }

    /**
     * right button action
     * @param evt ActionEvent
     * @return null
     * @since v0.0.1
     */
    private void jButton2ActionPerformed(ActionEvent evt) {
        mainFrame.nextPage(archiveManager);
    }

    /**
     * fit Width button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton3ActionPerformed(ActionEvent evt) {
      mainFrame.fitImage(isFitWidthSelected());
    }

    /**
     * full screen button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton4ActionPerformed(ActionEvent evt) {
        Settings.setFullscreen(!Settings.isFullscreen());
        mainFrame.fullscreen();
    }

    /**
     * always on top button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton5ActionPerformed(ActionEvent evt) {
    mainFrame.alwaysOnTop();
    }

    /**
     * go to button action
     * @param evt ActionEvent
     * @return null
     * @since v0.0.1
     */
    private void jButton6ActionPerformed(ActionEvent evt) {
        mainFrame.jumpToPage();
    }

    /**
     * about button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton7ActionPerformed(ActionEvent evt) {
        new HelpDialog(mainFrame);
        //
    }

    /**
     * exit button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton8ActionPerformed(ActionEvent evt) {
        mainFrame.close();
    }

    /**
     * key shortcuts button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton9ActionPerformed(ActionEvent evt) {
        new ShortcutsDialog(mainFrame);
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

    }

    /**
     * add bookmark button action
     * @param Actionevent evt
     * @return null
     * @since v0.0.1
     */
    private void jButton11ActionPerformed(ActionEvent evt) {

        File file = MainFrame.getFile();
        if(file != null) {

            if(addBookmark.isSelected()) {
                BookmarksManager.add(file, imagePanel.getIndex());

            } else {
                BookmarksManager.remove(file, imagePanel.getIndex());
            }
        }
          else {
            addBookmark.setSelected(false);
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
     * Previous comic
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
     * Next Comic
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
     * bookmarks manager button action
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton15ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.origSize(isOrigSizeSelected());
    }
    
    /**
     * Zoom In
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton16ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.zoomIn();
    }
    
    /**
     * Zoom Out
     * @param evt ActonEvent
     * @return null
     * @since v0.0.3
     */
    private void jButton17ActionPerformed(ActionEvent evt) {
        if(!imagePanel.isImageEmpty(imagePanel.getIndex()))
            mainFrame.zoomOut();
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
    
    /**
     * Translator
     * @param evt ActonEvent
     * @return null
     * @since v0.0.4
     */
    private void jButton20ActionPerformed(ActionEvent evt) {
        new TranslatorDialog(mainFrame);
    }

    /**
     * sets fit width toggle button state
     * @return null
     * @since v0.0.1
     */
    static void setFitToggle() {
        
        fitWidth.setSelected(!isFitWidthSelected());
    }
    
    /**
     * sets origSize toggle button state
     * @return null
     * @since v0.0.3
     */
    static void setOrigSizeToggle() {
        boolean b = !isOrigSizeSelected();
        if(b) {
            imagePanel.setMode((short)4);
        }
        origSize.setSelected(b);
    }

    /**
     * sets addBookmark toggle button state
     * @return null
     * @since v0.0.1
     */
    static void setAddBookmarkToggle() {
        boolean b;
        if(addBookmark.isSelected()) {
            b = false;
        }
        else {
            b = true;
        }
        addBookmark.setSelected(b);
    }

    /**
     * sets always on top toggle button state
     * @return null
     * @since v0.0.1
     */
    static void setAlwaysOnTopToggle() {
        alwaysOnTop.setSelected(Settings.isAlwaysOnTop());
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
     * @return boolean state of toggle button origSize
     * @since v0.0.3
     */
    static boolean isOrigSizeSelected() {
        return origSize.isSelected();
    }

    /**
     *
     * @return boolean state of toggle button fitWidth
     * @since v0.0.1
     */
    static boolean isAddBookmarkSelected() {
        return addBookmark.isSelected();
    }
    
    static boolean isPageNumberVisible() {
        return page.isShowing();
    }
    
    static void showPageNumber(boolean show) {
        page.setVisible(show);
        totalPages.setVisible(show);
    }
    
    static void refreshPage() {
        if(ArchiveManager.getSize() == 0)
            page.setText("0");
        else
            page.setText(""+(imagePanel.getIndex() + 1));
        totalPages.setText(" / " + ArchiveManager.getSize());
        if(ArchiveManager.getSize() >= 100)
            totalPages.setPreferredSize(new Dimension(35, 24));
        else
            totalPages.setPreferredSize(new Dimension(24, 24));
    }
    
}
