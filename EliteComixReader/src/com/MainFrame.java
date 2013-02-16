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
    private boolean fullscreen = false, alwaysOnTop = false;
    private static ToolBar t;
    private boolean toolBarStatus;
    private PopupMenu popupMenu;
    private static File file;
    
    /**
     * Constructor
     * @param d dimension of the frame
     * @param ext ArchiveManager instance
     * @since v0.0.1
     */
    public MainFrame (Dimension d, ArchiveManager ext) {
        
        initComponents(d, ext);
    }
    
    /**
     * Constructor
     * @param ext ArchiveManager instance
     * @since v0.0.1
     */
    public MainFrame(ArchiveManager ext) {
        
        this(new Dimension(400, 400), ext);
    }
    
    /**
     * Constructor
     * @param image BufferedImage to display 
     * @param ext ArchiveManager instance
     * @since v0.0.1
     */
    public MainFrame(BufferedImage image, ArchiveManager ext) {
        
        this(new Dimension(400, 400), ext);     
    }
    
    /**
     * 
     * @param d
     * @param ext 
     */
    private void initComponents(Dimension d, final ArchiveManager ext)
    {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if(MainFrame.getFile() != null)
                    BookmarksManager.setLastPageAsBookmark(MainFrame.getFile(), imagePanel.getIndex());
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 200);
        setVisible(true);
        setMinimumSize(new Dimension(200, 200));
        setSize(d);
        setTitle("Elite Comix Reader");
        imagePanel = new ImagePanel();
        addKeyListener(new KeyAdapter() {
                @Override
                    public void keyPressed(KeyEvent e) {
                    keyPressedAction(e,ext);
                }
                });
        t = new ToolBar(imagePanel, this, ext);
        
        getContentPane().add(t, BorderLayout.NORTH);
        getContentPane().add(imagePanel);
        if(System.getProperty("os.name").toLowerCase().startsWith("windows")) {
        try {

                UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException | InstantiationException 
                    | IllegalAccessException | UnsupportedLookAndFeelException e) {
            
            }
        }
        
        popupMenu = new PopupMenu(imagePanel, this, ext);
        FileDrop fileDrop = new  FileDrop( imagePanel, new FileDrop.Listener() {
            @Override 
            public void  filesDropped( java.io.File[] files )                                     
            {                              
                File f = files[0];
                doWork(ext, f);                    
            }
        });
        fileDrop = null;
        addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    fullscreen();
                }
                if(e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.showPopup(e);
                }
            }
            });
        scale(1.0, imagePanel);
         
    }
    
    void goToImage(int index) {
        if(index > -1)
            imagePanel.goToPage(index);
    }
    
    private void keyPressedAction(KeyEvent e, ArchiveManager ext) {
        
        if(e.getKeyCode() == KeyEvent.VK_O) {
                        open(ext);   
                    } else if(!imagePanel.isImageEmpty(imagePanel.getIndex())) {
                    if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                        moveDown();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_UP) {
                        if(imagePanel.getImageHeight() > imagePanel.getFrameHeight()) {   
                            if( Math.abs(imagePanel.getYPos() + 5) < 
                                    imagePanel.getImageHeight() - imagePanel.getFrameHeight() 
                                && imagePanel.getYPos() + 5 <= 0) {
                                imagePanel.setY(imagePanel.getYPos() + 5);
                                imagePanel.repaint();
                            }
                        }
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                      imagePanel.zoomOut();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
                      imagePanel.zoomIn();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_0) {
                        imagePanel.setTransform(90);
                        imagePanel.repaint();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        
                        imagePanel.nextPage(ext);
                        ToolBar.setAddBookmark();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                        imagePanel.prevPage(ext);
                        ToolBar.setAddBookmark();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_S) {
                        save();   
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_B) {
                        
                        File ff = MainFrame.getFile();
                        if(ff != null) {
                            BookmarksManager.add(ff, imagePanel.getIndex());
                            ToolBar.setAddBookmarkToggle();
                            
                        }
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_A) {
                        new BookmarksDialog(this, 3);
                    }
                    }
                    
    }
    
    static void open(ArchiveManager e) {
        
        JFileChooser chooser = new JFileChooser();
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
        doWork(e, file);
        chooser = null;
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
    
    private void moveDown() {
        
        if(imagePanel.getOrientation() == 0) {
            if(imagePanel.getImageHeight() > imagePanel.getFrameHeight()) {
                if(Math.abs(imagePanel.getYPos() - 3) < imagePanel.getImageHeight() 
                        - imagePanel.getFrameHeight() && imagePanel.getYPos() - 3 <= 0) {
                    imagePanel.setY(imagePanel.getYPos() - 3);
                    imagePanel.repaint();
                }
            }
        }
        else if(imagePanel.getOrientation() == 1) {
            
            if(imagePanel.getImageHeight() > imagePanel.getFrameHeight()) {
                
                if(Math.abs(imagePanel.getXPos() - 3) < imagePanel.getImageHeight() 
                        - imagePanel.getFrameHeight() 
                            && imagePanel.getXPos() - 3 <= 0) {
                    imagePanel.setX(imagePanel.getXPos() - 3);
                    imagePanel.repaint();
                }
            }
        }
    }
    
    static File getFile() {
        return file;
    }
    
    static void scale(Double scale, ImagePanel imagePanel) {
        imagePanel.setScale(scale);
    }
    
    void fitImage(boolean b) {
        
        imagePanel.toggleMode(b);
        if(ArchiveManager.getSize() != 0) {
            imagePanel.fit(imagePanel.getMode());
            imagePanel.repaint();
        }
        
    }
    
    void fullscreen() {
        
        if(isFullscreen()) {
            t.setVisible(isFullscreen());
            fullscreen = false;
            dispose();
            setUndecorated(false);
            GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(null);
            //setSize(400, 400);
            validate();
            setVisible(true);
        }
        else {
            t.setVisible(isFullscreen());
            fullscreen = true;
            dispose();
            setUndecorated(true);
            GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            gs.setFullScreenWindow(this);
            validate();
            //f.setVisible(true);
        }
    }
    
    /**
     * 
     * @return boolean denoting full screen mode
     * @since v0.0.1
     */
    private boolean isFullscreen() {
        return fullscreen;
    }
    
    /**
     * 
     * @return alwaysOnTop state
     * @since v0.0.1
     */
    private boolean isOnTop() {
        return alwaysOnTop;
    }
    
    /**
     * Toggle alwaysOnTop
     * @since v0.0.1
     */
    void alwaysOnTop() {
        
        setAlwaysOnTop(!isOnTop());
        alwaysOnTop = !isOnTop();
        
    }
    
    
    /**
     * 
     * @param ext ArchiveManager instance
     * @param f Comic Book File to open
     * @since v0.0.1
     */
    static void doWork(ArchiveManager ext, File f) {
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
            }
            }
            catch(NullPointerException r) {
            }
    }
    
    /**
     * Main method for Testing
     * @param args command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager e = new ArchiveManager();
        MainFrame mainFrame = new MainFrame(e);
        mainFrame = null;
    }
}
