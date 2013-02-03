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
import javax.swing.*;
/**
 *
 * @author Abhishek Banerjee
 */
public class PopupMenu extends JPopupMenu{
    
    private JMenuItem open, left, right, fitWidth, 
            fullscreen, alwaysOnTop, goTo, shortcuts, about, exit, save;
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
        
        about = new JMenuItem("Help");
        about.setIcon(new ImageIcon(getClass().getResource("/Resources/help2.png")));
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0));
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
         imagePanel.prevPage(archiveManager);
         left.transferFocusUpCycle();
    }
    
    private void jButton2ActionPerformed(ActionEvent evt) {
        imagePanel.nextPage(archiveManager);
        right.transferFocusUpCycle();
    }
    
    private void jButton3ActionPerformed(ActionEvent evt) {
        ToolBar.setFitToggle();
        mainFrame.fitImage(ToolBar.isFitWidthSelected());
        
        //fitWidth.transferFocusUpCycle();
    }
    
    private void jButton4ActionPerformed(ActionEvent evt) {
        mainFrame.fullscreen();
        fullscreen.transferFocusUpCycle();
    }
    
    private void jButton5ActionPerformed(ActionEvent evt) {
        mainFrame.alwaysOnTop();
        ToolBar.setAlwaysOnTopToggle();
        alwaysOnTop.transferFocusUpCycle();
    }
    
    private void jButton6ActionPerformed(ActionEvent evt) {
        try {
            String res = JOptionPane.showInputDialog(mainFrame, "Enter page no: "
            +"( 0 - " + ArchiveManager.getSize() +" )");
            //System.out.print(res);
            if(res != null) {
                int page = Integer.parseInt(res);
                if(page > -1)
                imagePanel.goToPage(archiveManager, page);
            }
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Enter Integers only!!");
        }
        goTo.transferFocusUpCycle();
    }
    
    private void jButton7ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new HelpDialog(mainFrame, -1); 
        about.transferFocusUpCycle();
    }
    
    private void jButton8ActionPerformed(ActionEvent evt) {
        mainFrame.dispose();
        exit.transferFocusUpCycle();
    }
    
    private void jButton9ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        ShortcutsDialog shortcutsDialog = new ShortcutsDialog(mainFrame, -1);
        about.transferFocusUpCycle();
    }
    
    private void jButton10ActionPerformed(ActionEvent evt) {
        mainFrame.save();
        save.transferFocusUpCycle();
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
