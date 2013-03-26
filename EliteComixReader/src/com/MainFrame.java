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
import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import javax.swing.*;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author Abhishek Banerjee
 */
public class MainFrame extends JFrame {

    private static ImagePanel imagePanel;
    private static ToolBar t;
    private boolean toolBarStatus;
    private PopupMenu popupMenu;
    private static File file;
    private static JScrollPane scrollPane;

    /**
     * Constructor
     * @param ext ArchiveManager instance
     * @since v0.0.1
     */
    public MainFrame (ArchiveManager ext){
        super("Elite Comix Reader");
        initComponents(ext);
    }

    public MainFrame (String file, ArchiveManager ext){
        super("Elite Comix Reader");
        initComponents(ext);
        JOptionPane.showConfirmDialog(rootPane, "iT woRkS!!");
        displayComic(ext, new File(file));
        Settings.loadFileList(new File(file));
    }

    /**
     *
     * @param ext
     */
    private void initComponents(final ArchiveManager ext) {
        setSize(Settings.getSize());
        setLocation(Settings.getX(), Settings.getY());
        setAlwaysOnTop(Settings.isAlwaysOnTop());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(200, 200));
        setTitle("Elite Comix Reader");
        ImageIcon img = new ImageIcon(getClass().getResource("/Resources/elite_comix_reader_small.png"));
        setIconImage(img.getImage());
        scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        
        imagePanel = new ImagePanel();
        scrollPane.setViewportView(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setScrollPaneView();
        t = new ToolBar(imagePanel, this, ext);

        popupMenu = new PopupMenu(imagePanel, this, ext);
        if(Settings.isFullscreen()) {
            fullscreen();
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();

            }
        });
        
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                imagePanel.setMode((short)1);
                //scrollPane.revalidate();
                imagePanel.repaint();
                if(ToolBar.isFitWidthSelected())
                ToolBar.setFitToggle();
                if(ToolBar.isOrigSizeSelected())
                    ToolBar.setOrigSizeToggle();
            }
        });

        addKeyListener(new KeyAdapter() {
                @Override
                    public void keyPressed(KeyEvent e) {
                    keyPressedAction(e,ext);
                }
                });

        scrollPane.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    Settings.setFullscreen(!Settings.isFullscreen());
                    fullscreen();
                }
                if(e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.showPopup(e);
                }
            }
            });

        new  FileDrop( imagePanel, new FileDrop.Listener() {
            @Override
            public void  filesDropped( java.io.File[] files ) {
                File f = files[0];
                displayComic(ext, f);
                Settings.loadFileList(f);
            }
        });

        scale(1.0, imagePanel);
        getContentPane().add(t, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        if(Settings.isMaximized()) {
            setExtendedState(MainFrame.MAXIMIZED_BOTH);
        }

        setVisible(true);
        t.transferFocusUpCycle();
    }

    void formWindowStateChanged(java.awt.event.WindowEvent evt) {
        if(evt.getNewState() == JFrame.MAXIMIZED_BOTH) {
            //Settings.setSize(evt.getOppositeWindow().getPreferredSize());
            //System.out.println(" " + getPreferredSize()+" ");
        }
        else {
            //System.out.println(" " + getPreferredSize()+" "+Settings.getSize());
            setPreferredSize(getPreferredSize());
            validate();
        }
    }
    
    static void setScrollPaneView() {
        
        int s = Settings.getScrollSize();
        scrollPane.getVerticalScrollBar().setPreferredSize (new Dimension(s, s));
        scrollPane.getHorizontalScrollBar().setPreferredSize (new Dimension(s,s));
        scrollPane.setViewportView(imagePanel);
        scrollPane.revalidate();
    }

    void close() {
        if(MainFrame.getFile() != null) {
            BookmarksManager.setLastPageAsBookmark(MainFrame.getFile(), imagePanel.getIndex());
        }
        Settings.store(MainFrame.this);
        System.exit(0);
    }

    void goToImage(int index) {
        if(index > -1) {
            imagePanel.goToPage(index);
        }
    }

    private void keyPressedAction(KeyEvent e, ArchiveManager ext) {

        if(e.getKeyCode() == KeyEvent.VK_O) {
            open(ext);
        }
        else if(e.getKeyCode() == KeyEvent.VK_Q) {
            close();
        }
        else if(e.getKeyCode() == KeyEvent.VK_K) {
            new ShortcutsDialog(this);
        }
        else if(e.getKeyCode() == KeyEvent.VK_X) {
            new SettingsDialog(this);
        }
        else if(e.getKeyCode() == KeyEvent.VK_Z) {
            new HelpDialog(this);
        }
        else if(!imagePanel.isImageEmpty(imagePanel.getIndex())) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                moveScrollDown();
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP) {
                moveScrollUp();
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Settings.setFullscreen(!Settings.isFullscreen());
                fullscreen();
            }
            else if(e.getKeyCode() == KeyEvent.VK_W) {
                ToolBar.setFitToggle();
                imagePanel.toggleMode(ToolBar.isFitWidthSelected());
                fitImage(ToolBar.isFitWidthSelected());
            }
            else if(e.getKeyCode() == KeyEvent.VK_H) {
                toolBarStatus = !toolBarStatus;
                t.setVisible(toolBarStatus);
            }
            else if(e.getKeyCode() == KeyEvent.VK_T) {
                alwaysOnTop();
                ToolBar.setAlwaysOnTopToggle();
            }
            else if(e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
                zoomOut();
            }
            else if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
                zoomIn();
            }
            else if(e.getKeyCode() == KeyEvent.VK_9) {
                rotateLeft();
            }
            else if(e.getKeyCode() == KeyEvent.VK_0) {
                rotateRight();
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

                nextPage(ext);
                
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                
                prevPage(ext);
            }
            else if(e.getKeyCode() == KeyEvent.VK_S) {
                save();
            }
            else if(e.getKeyCode() == KeyEvent.VK_B) {
                File ff = MainFrame.getFile();
                if(ff != null) {
                    if(!ToolBar.isAddBookmarkSelected()) {
                        BookmarksManager.add(ff, imagePanel.getIndex());
                    }
                    else {
                        BookmarksManager.remove(ff, imagePanel.getIndex());
                    }
                    ToolBar.setAddBookmarkToggle();
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_A) {
                new BookmarksDialog(this);
            }
            else if(e.getKeyCode() == KeyEvent.VK_J) {
                jumpToPage();
            }
            else if(e.getKeyCode() == KeyEvent.VK_1) {
                imagePanel.origSize(ToolBar.isOrigSizeSelected());
                ToolBar.setOrigSizeToggle();
            }
        }
    }

    static void open(ArchiveManager e) {
        imagePanel.setEmptyImage();
        Settings.setLoadingImage(true);
        imagePanel.repaint();
        JFileChooser chooser = new JFileChooser(Settings.getComicsPath());
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {

                return     f.isDirectory()
                        || f.getName().toLowerCase().endsWith(".cbr")
                        || f.getName().toLowerCase().endsWith(".cbz")
                        || f.getName().toLowerCase().endsWith(".rar")
                        || f.getName().toLowerCase().endsWith(".zip");
             }

            @Override
            public String getDescription() { return "Comic Book files"; }});

        chooser.showOpenDialog(imagePanel);
        file = chooser.getSelectedFile();
        displayComic(e, file);
        Settings.loadFileList(file);
        chooser = null;
        Settings.setLoadingImage(false);
        imagePanel.repaint();
    }

    void save() {
        if(ArchiveManager.getSize() != 0) {
            JFileChooser ch = new JFileChooser();
            ch.setSelectedFile(ArchiveManager.getFile(imagePanel.getIndex()));
            ch.setCurrentDirectory(null);
            int approve = ch.showSaveDialog(imagePanel);
            if(approve == JFileChooser.APPROVE_OPTION) {
                File f = ch.getSelectedFile();
                try {
                    Files.copy(ArchiveManager.getFile(imagePanel.getIndex()).toPath(),
                            f.toPath(), COPY_ATTRIBUTES);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }
        }
    }

    private void moveScrollDown() {
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + 10);
    }
    
    private void moveScrollUp() {
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() - 10);
    }

    void jumpToPage() {
        try {
            String res = JOptionPane.showInputDialog(this, "Enter page no: "
            +"( 1 - " + ArchiveManager.getSize() +" )");
            //System.out.print(res);
            if(res != null) {
                int page = Integer.parseInt(res);
                if(page > 0) {
                    imagePanel.goToPage(page - 1);
                }
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter Integers only!!");
        }
        ToolBar.refreshPage();
    }

    void prevPage(ArchiveManager ext) {
        imagePanel.prevPage(ext);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        ToolBar.setAddBookmark();
        ToolBar.refreshPage();
    }
    
    void nextPage(ArchiveManager ext) {
        imagePanel.nextPage(ext);
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
        ToolBar.setAddBookmark();
        ToolBar.refreshPage();
    }
    
    static File getFile() {
        return file;
    }

    public Dimension getFrameSize() {
        return getSize();
    }
    
    public static Dimension getScrollPaneSize() {
        return new Dimension(scrollPane.getWidth() - Settings.getScrollSize(), 
                scrollPane.getHeight() - Settings.getScrollSize());
    }
    
    static void setScrollBarSize() {
        scrollPane.getHorizontalScrollBar().setPreferredSize(
                new Dimension(Settings.getScrollSize(), Settings.getScrollSize()));
        scrollPane.getVerticalScrollBar().setPreferredSize(
                new Dimension(Settings.getScrollSize(), Settings.getScrollSize()));
    }
    
    static void scale(Double scale, ImagePanel imagePanel) {
        imagePanel.setScale(scale);
    }

    void fitImage(boolean b) {

        imagePanel.toggleMode(b);
        if(ArchiveManager.getSize() != 0) {
            imagePanel.fit(imagePanel.getMode());
            imagePanel.repaint();
            setScrollPaneView();
        }
        if(ToolBar.isOrigSizeSelected())
            ToolBar.setOrigSizeToggle();
    }
    
    void origSize(boolean b) {
        imagePanel.origSize(b);
        if(ToolBar.isOrigSizeSelected() && ToolBar.isFitWidthSelected())
            ToolBar.setFitToggle();
    }
    
    void zoomIn() {
        imagePanel.zoomIn();
        if(ToolBar.isFitWidthSelected())
            ToolBar.setFitToggle();
        if(ToolBar.isOrigSizeSelected())
            ToolBar.setOrigSizeToggle();
    }
    
    void zoomOut() {
        imagePanel.zoomOut();
        if(ToolBar.isFitWidthSelected())
            ToolBar.setFitToggle();
        if(ToolBar.isOrigSizeSelected())
            ToolBar.setOrigSizeToggle();
    }
    
    void rotateLeft() {
        if(imagePanel.getOrientation() > 0)
            imagePanel.setOrientation(imagePanel.getOrientation() - 1);
        else
            imagePanel.setOrientation(3);
        imagePanel.repaint();
    }
    
    void rotateRight() {
        imagePanel.setOrientation(Math.abs(imagePanel.getOrientation() + 1 )% 4);
        imagePanel.repaint();
    }

    void fullscreen() {

        if(Settings.isFullscreen()) {
            t.setVisible(!Settings.isFullscreen());
            
            Settings.setMaximized(getExtendedState() == JFrame.MAXIMIZED_BOTH);
            //dispose();
            //setVisible(false);
            setExtendedState(JFrame.NORMAL);
            dispose();
            setUndecorated(true);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(this);
            validate();
            setVisible(true);
        }
        else {
            t.setVisible(!Settings.isFullscreen());
            
            dispose();
            setUndecorated(false);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(null);
            
            //System.out.println(" " + " "+getFrameSize());
//            if(Settings.isMaximized())
                setExtendedState(JFrame.NORMAL);
            setPreferredSize(Settings.getSize());
            validate();
            setVisible(true);
        }
    }

    void fullscreen(boolean b) {

        if(b) {
            t.setVisible(!Settings.isFullscreen());
            dispose();
//            if (Settings.NO_DECORATION.equals(Settings.getDecorationStyle()))
            setUndecorated(true);
            GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(this);
            //Settings.setDecorationStyle(Settings.NO_DECORATION);
            //Settings.setLookAndFeel(true, "default");
            setVisible(true);
        } else {
            t.setVisible(!Settings.isFullscreen());
            dispose();
            //setUndecorated(false);
            GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(null);
            
            setVisible(true);
        }

    }
    /**
     * Toggle alwaysOnTop
     * @since v0.0.1
     */
    void alwaysOnTop() {
        Settings.setAlwaysOnTop(!Settings.isAlwaysOnTop());
    }

    /**
     *
     * @param ext ArchiveManager instance
     * @param f Comic Book File to open
     * @since v0.0.1
     */
    static void displayComic(ArchiveManager ext, File f) {
        
        try {
            
            BufferedImage a = null;
                try {
                    int success = ext.extract(f);
                    if(success == 0) {
                        imagePanel.setIndex(0);

                        if(imagePanel.getIndex() <= ArchiveManager.getSize()
                                && ArchiveManager.getSize() != 0) {
                            a = ArchiveManager.getImage(imagePanel.getIndex());
                            BookmarksManager.load(f);
                            ToolBar.setAddBookmark();
                        }
                        else if(ArchiveManager.getSize() == 0) {
                            imagePanel.setEmptyImage();
                            JOptionPane.showMessageDialog(imagePanel, "No images in the File!!");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(imagePanel, ArchiveManager.getError());
                    }
                } catch (IOException ex) {
                    //
                }
            if(a != null) {
                imagePanel.loadImage(a);
                imagePanel.repaint();
                setScrollPaneView();
                
            }
            
        }
            catch(NullPointerException r) {
            }
        ToolBar.refreshPage();
    }

    /**
     * Main method for Testing
     * @param args command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager e = new ArchiveManager();
        //MainFrame mainFrame = new MainFrame(e);
        //mainFrame = null;
    }




}
