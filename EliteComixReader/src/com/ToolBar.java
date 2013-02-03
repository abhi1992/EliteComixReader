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
import javax.swing.*;

/**
 *
 * @author Abhishek Banerjee
 */
public class ToolBar extends JToolBar {
    
    private static JButton open, left, right, about, exit,  fullscreen,  goTo, keyShorcuts, save;
    private static JToggleButton fitWidth, alwaysOnTop;
    private static ImagePanel imagePanel;
    private static MainFrame mainFrame;
    private static ArchiveManager archiveManager;
    
    //Constructor
    ToolBar(ImagePanel i, MainFrame f, ArchiveManager am){
        
        ToolBar.imagePanel = i;
        ToolBar.mainFrame = f;
        ToolBar.archiveManager = am;
        setFloatable(false);
        //setBorderPainted(false);
        setEnabled(false);
        setFocusable(false);
        setFocusCycleRoot(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        open = new JButton(new ImageIcon(getClass().getResource("/Resources/open1.png")));
        open.setMnemonic(KeyEvent.VK_O);
        open.setToolTipText("Open Comic");
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton0ActionPerformed(evt);
                
            }
        });
        add(open);
        
        save = new JButton(new ImageIcon(getClass().getResource("/Resources/document_save1.png")));
        save.setMnemonic(KeyEvent.VK_S);
        save.setToolTipText("Save Image");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton10ActionPerformed(evt);
                
            }
        });
        add(save);
        
        addSeparator(new Dimension(10, 30));
        
        left = new JButton(new ImageIcon(getClass().getResource("/Resources/go_previous_black1.png")));
        left.setMnemonic(KeyEvent.VK_LEFT);
        left.setToolTipText("Previous Page");
        left.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(left);

        right = new JButton(new ImageIcon(getClass().getResource("/Resources/go_next_black1.png")));
        right.setMnemonic(KeyEvent.VK_RIGHT);
        right.setToolTipText("Next Page");
        right.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(right);
        
        goTo = new JButton(new ImageIcon(getClass().getResource("/Resources/view_refresh1.png")));
        goTo.setMnemonic(KeyEvent.VK_J);
        goTo.setToolTipText("Jump To Page");
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
        
        fullscreen = new JButton(new ImageIcon(getClass().getResource("/Resources/full screen1.png")));
        fullscreen.setMnemonic(KeyEvent.VK_ESCAPE);
        fullscreen.setToolTipText("Fullscreen");
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
        
        keyShorcuts = new JButton(new ImageIcon(getClass().getResource("/Resources/keyboard1.png")));
        keyShorcuts.setMnemonic(KeyEvent.VK_K);
        keyShorcuts.setToolTipText("Key Board Shortcuts");
        keyShorcuts.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        add(keyShorcuts);
        
        about = new JButton(new ImageIcon(getClass().getResource("/Resources/help2.png")));
        about.setMnemonic(KeyEvent.VK_H);
        about.setToolTipText("Help");
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(about);
        
        exit = new JButton(new ImageIcon(getClass().getResource("/Resources/dialog_close1.png")));
        exit.setMnemonic(KeyEvent.VK_Q);
        exit.setToolTipText("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(exit);
        transferFocusUpCycle();
    }
    
    void hideToolBar(boolean b) {
        setVisible(b);
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
    mainFrame.fitImage(isFitWidthSelected());
    fitWidth.transferFocusUpCycle();
    }
    
    private void jButton4ActionPerformed(ActionEvent evt) {
    mainFrame.fullscreen();
    fullscreen.transferFocusUpCycle();
    }
    
    private void jButton5ActionPerformed(ActionEvent evt) {
    mainFrame.alwaysOnTop();
    alwaysOnTop.transferFocusUpCycle();
    }
    
    private void jButton6ActionPerformed(ActionEvent evt) {
        try{
            String res = JOptionPane.showInputDialog(mainFrame, "Enter page no: "
            +"( 0 - " + ArchiveManager.getSize() +" )");
            //System.out.print(res);
            if(res != null) {
                int page = Integer.parseInt(res);
                if(page > -1)
                imagePanel.goToPage(archiveManager, page);
            }
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(mainFrame, "Enter Integers only!!");
        }
    goTo.transferFocusUpCycle();
    }
    
    private void jButton7ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new HelpDialog(mainFrame, 2);
        //about.transferFocusUpCycle();
    }
    
    private void jButton8ActionPerformed(ActionEvent evt) {
        mainFrame.dispose();
        exit.transferFocusUpCycle();
    }
    
    private void jButton9ActionPerformed(ActionEvent evt) {
        //mainFrame.setEnabled(false);
        new ShortcutsDialog(mainFrame, 1);
        //about.transferFocusUpCycle();
    }
    
    private void jButton10ActionPerformed(ActionEvent evt) {
        mainFrame.save();
        save.transferFocusUpCycle();
    }

    static void setFitToggle() {
        boolean b = true;
        if(imagePanel.getMode() == 0)
            b = false;
        else if(imagePanel.getMode() == 1)
            b = true;
        fitWidth.setSelected(b);
    }
    
    
    static void setAlwaysOnTopToggle() {
        alwaysOnTop.setSelected(mainFrame.isAlwaysOnTop());
    }
    
    static void cycleFocus(int i) {
        if(i == 1) {
            keyShorcuts.transferFocusUpCycle();
        }
        else if(i == 2) {
            about.transferFocusUpCycle();
        }
    }
    
    static boolean isFitWidthSelected() {
        return fitWidth.isSelected();
    }
}
