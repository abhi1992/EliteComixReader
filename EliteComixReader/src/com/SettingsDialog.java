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
 * The dialog to be displayed when help button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */

public class SettingsDialog extends JDialog{

    private JLabel jLabel1, jLabel2, color, lafLabel, advancedLabel;
    private JTabbedPane tab;
    private JPanel jPanel1, jPanel2, generalPanel, viewPanel, generalCenterPanel,
            viewCenterPanel, lafPanel, colorPanel, advancedPanel, extractDirPanel,
            advancedCenterPanel, advancedBasePanel, showPageNoPanel, 
            comicsPathPanel, scrollPanel, resetToDefaultsPanel,
            resetEastPanel, resetWestPanel, resetCenterPanel, resetSouthPanel;
    private JButton jButton1, jButton2, chooseColor, comicsPathBrowse,
            extractDirBrowse, advancedClose, reset;
    private JComboBox<String> laf;
    private JTextField comicsPath, extractPath;
    private JRadioButton none, thin, normal;
    private JCheckBox viewChkBox, comicsPathChkBox, extractDirChkBox, 
            keyShortcutsChkBox, selectAll, showPageNo;
    private ButtonGroup scrollGroup;
    
    /**
     * Constructor
     * @param mainFrame the frame on which the dialog is called
     */
    SettingsDialog(final MainFrame mainFrame) {

        tab = new JTabbedPane();
        setResizable(false);
        setTitle("Settings");
        setLayout(new BorderLayout());

        jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());

        jButton1 = new JButton("Close");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //mainFrame.setEnabled(true);
                dispose();
            }
        });
        jPanel1.add(jButton1);
        jLabel1 = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/configure_shortcuts.png")));
        colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());
        colorPanel.setBorder(BorderFactory.createTitledBorder("Background Color"));
        color = new JLabel();
        color.setPreferredSize(new Dimension(24, 24));
        color.setOpaque(true);
        color.setBackground(Settings.getDefaultColor());
        chooseColor = new JButton("Choose");
        chooseColor.setFocusable(false);
        chooseColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                chooseColorActionPerformed(mainFrame);
            }
        });

        colorPanel.add(color);
        colorPanel.add(chooseColor);

        comicsPathPanel = new JPanel();
        comicsPathPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        comicsPathPanel.setBorder(BorderFactory.createTitledBorder("Set Comics Path"));

        comicsPath = new JTextField();
        comicsPath.setEditable(false);
        comicsPath.setText(Settings.getComicsPath());
        comicsPath.setPreferredSize(new Dimension(250, 25));
        comicsPathBrowse = new JButton("Browse");
        comicsPathBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                comicsPathActionPerformed(mainFrame);
            }
        });
        comicsPathBrowse.setFocusable(false);
        
        scrollPanel = new JPanel();
        scrollPanel.setBorder(BorderFactory.createTitledBorder("Set Scroll Bar Size"));
        scrollGroup = new ButtonGroup();
        none = new JRadioButton("None");
        none.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                setScrollBarSize(1);
            }
        });
        
        thin = new JRadioButton("Thin");
        thin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                setScrollBarSize(2);
            }
        });
        normal = new JRadioButton("Normal");
        normal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                setScrollBarSize(3);
            }
        });
        if(Settings.getScrollSize() == 0) {
            none.setSelected(true);
        } else if(Settings.getScrollSize() == 10) {
            thin.setSelected(true);
        } else {
            normal.setSelected(true);
        }
        
        showPageNo = new JCheckBox("Show Page Number");
        showPageNo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ToolBar.showPageNumber(!ToolBar.isPageNumberVisible());
                Settings.setPageNo(ToolBar.isPageNumberVisible());
                repaint();
            }
        });
        showPageNo.setSelected(ToolBar.isPageNumberVisible());
        showPageNoPanel = new JPanel();
        showPageNoPanel.setBorder(BorderFactory.createTitledBorder("Page Info"));
        showPageNoPanel.add(showPageNo);
        
        scrollGroup.add(none);
        scrollGroup.add(thin);
        scrollGroup.add(normal);
        scrollPanel.add(none);
        scrollPanel.add(thin);
        scrollPanel.add(normal);
        
        
        comicsPathPanel.add(comicsPath);
        comicsPathPanel.add(comicsPathBrowse);

        generalCenterPanel = new JPanel();
        generalCenterPanel.setLayout(new GridLayout(3,1));

        generalCenterPanel.add(scrollPanel);
        generalCenterPanel.add(showPageNoPanel);
        generalCenterPanel.add(comicsPathPanel);
        generalPanel = new JPanel(new BorderLayout());
        generalPanel.add(jLabel1, BorderLayout.WEST);
        generalPanel.add(jPanel1, BorderLayout.SOUTH);
        generalPanel.add(generalCenterPanel, BorderLayout.CENTER);
        tab.addTab("General", generalPanel);
        tab.transferFocusDownCycle();

        jLabel2 = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/preferences_desktop_theme.png")));

        viewPanel = new JPanel(new BorderLayout());
        viewPanel.add(jLabel2, BorderLayout.WEST);


        jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());

        jButton2 = new JButton("Close");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //mainFrame.setEnabled(true);
                dispose();
            }
        });

        lafLabel = new JLabel("Restart is needed for changes to take place.");
        jPanel2.add(jButton2);

        viewCenterPanel = new JPanel();
        viewCenterPanel.setLayout(new GridLayout(2, 1));
        lafPanel = new JPanel();
        lafPanel.setBorder(BorderFactory.createTitledBorder("Look And Feel"));
        lafPanel.setLayout(new FlowLayout());
        laf = new JComboBox<>(Settings.getAvailableLafName(Settings.getAvailableLafPath()));
        laf.setFocusable(false);
        laf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                lafActionPerformed();
            }
        });
        laf.setSelectedItem(Settings.getLafName(Settings.getLaf()));

        lafPanel.add(laf);
        lafPanel.add(lafLabel);
        viewCenterPanel.add(colorPanel);
        viewCenterPanel.add(lafPanel);
        //centerPanel.add(themePanel);
        viewPanel.add(viewCenterPanel, BorderLayout.CENTER);
        viewPanel.add(jPanel2, BorderLayout.SOUTH);
        
        tab.addTab("View", viewPanel);
        
        resetToDefaultsPanel = new JPanel(new BorderLayout());
        resetToDefaultsPanel.setBorder(BorderFactory.createTitledBorder("Reset To Defaults"));
        
        selectAll = createChkBox(selectAll, "Reset All");
        viewChkBox = createChkBox(viewChkBox, "Reset View");
        keyShortcutsChkBox = createChkBox(keyShortcutsChkBox, "Reset Key Shortcuts");
        comicsPathChkBox = createChkBox(comicsPathChkBox, "Reset Comics Path");
        extractDirChkBox = createChkBox(extractDirChkBox, "Reset Extracting Directory");
        
        resetEastPanel = new JPanel();
        resetWestPanel = new JPanel();
        resetCenterPanel = new JPanel(new GridLayout(0, 1));
        resetSouthPanel = new JPanel();
        
        resetCenterPanel.add(selectAll);
        resetCenterPanel.add(comicsPathChkBox);
        resetCenterPanel.add(extractDirChkBox);
        resetCenterPanel.add(keyShortcutsChkBox);
        resetCenterPanel.add(viewChkBox);
        
        reset = new JButton("Reset");
        
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                resetActionPerformed(mainFrame);
            }
        });
        resetSouthPanel.add(reset);
        resetToDefaultsPanel.add(resetEastPanel, BorderLayout.EAST);
        resetToDefaultsPanel.add(resetWestPanel, BorderLayout.WEST);
        resetToDefaultsPanel.add(resetCenterPanel, BorderLayout.CENTER);
        resetToDefaultsPanel.add(resetSouthPanel, BorderLayout.SOUTH);
        advancedCenterPanel = new JPanel(new GridLayout(0, 1));
        advancedCenterPanel.add(resetToDefaultsPanel);
        
        extractDirPanel = new JPanel();
        extractDirPanel.setBorder(BorderFactory.createTitledBorder("Set Extracting Directory"));
        extractPath = new JTextField();
        extractPath.setEditable(false);
        extractPath.setText(Settings.getExtractDir());
        
        extractPath.setPreferredSize(new Dimension(250, 25));
        extractDirBrowse = new JButton("Browse");
        extractDirBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                browsePathActionPerformed(mainFrame);
            }
        });
        extractDirBrowse.setFocusable(false);
        extractDirPanel.add(extractPath);
        extractDirPanel.add(extractDirBrowse);
        advancedCenterPanel.add(extractDirPanel);
        
        advancedLabel = new JLabel(new ImageIcon(getClass().getResource("/Resources/advancedsettings.png")));
        advancedClose = new JButton("Close");
        advancedClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        advancedBasePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        advancedBasePanel.add(advancedClose);
        
        advancedPanel = new JPanel(new BorderLayout());
        advancedPanel.add(advancedCenterPanel, BorderLayout.CENTER);
        advancedPanel.add(advancedLabel, BorderLayout.WEST);
        advancedPanel.add(advancedBasePanel, BorderLayout.SOUTH);
        tab.addTab("Advanced", advancedPanel);
        
        setMinimumSize(new Dimension(500, 600));
        Point p = new Point(0, 0);
        if(mainFrame != null) {
            p = mainFrame.getLocation();
        }
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y +
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        getContentPane().add(tab);
        setVisible(true);
        tab.setFocusable(false);
        //pack();
    }

    private void chooseColorActionPerformed(MainFrame mainFrame) {
        Color c = JColorChooser.showDialog(this , "Choose Background Color",
                Settings.getDefaultColor());
        if(c != null) {
            Settings.setDefaultColor(c);
            color.setBackground(c);
            mainFrame.repaint();
        }
        transferFocusBackward();
    }

    private void browsePathActionPerformed(MainFrame mainFrame) {
        JFileChooser chooser = new JFileChooser(Settings.getComicsPath());
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {

                return f.isDirectory();
             }

            @Override
            public String getDescription() { return "All files"; }});

        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if(file != null) {
            Settings.setExtractDir(file.getAbsolutePath());
            extractPath.setText(file.getAbsolutePath());
        }
    }
    
    private void comicsPathActionPerformed(MainFrame mainFrame) {
        JFileChooser chooser = new JFileChooser(Settings.getComicsPath());
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {

                return f.isDirectory();
             }

            @Override
            public String getDescription() { return "Comic Book files"; }});

        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        if(file != null) {
            Settings.setComicsPath(file.getAbsolutePath());
            comicsPath.setText(file.getAbsolutePath());
        }
    }

    private void lafActionPerformed() {
        for(String h : Settings.getAvailableLafPath()) {
            if(h.contains(laf.getSelectedItem().toString())) {
                Settings.setLaf(h);
            }
        }
    }
    
    private JCheckBox createChkBox(JCheckBox chk, String label) {
        chk = new JCheckBox(label);
        addChkBoxAction(chk);
        return chk;
    }
    
    private void addChkBoxAction(final JCheckBox chk) {
        chk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                resetChkBoxAction(chk);
            }
        });
    }
    
    private void resetChkBoxAction(JCheckBox chk) {
        
        switch(chk.getText()) {
            case "Reset All" :
                viewChkBox.setSelected(selectAll.isSelected());
                comicsPathChkBox.setSelected(selectAll.isSelected());
                extractDirChkBox.setSelected(selectAll.isSelected());
                keyShortcutsChkBox.setSelected(selectAll.isSelected());
                break;
            default:
                break;
        }
    }
    
    private void resetActionPerformed(MainFrame mainFrame) {
        int ans = JOptionPane.showConfirmDialog(this, "This Will Delete all Previously"
                + " saved settings.\n Do you wish to continue ??", "Reset Settings"
                , JOptionPane.YES_NO_CANCEL_OPTION);
        if(ans == 0) {
            if(viewChkBox.isSelected()) {
                Settings.setDefaultColor(Color.BLACK);
                Settings.setScrollSize(0);
                mainFrame.repaint();
            }
            if(keyShortcutsChkBox.isSelected()) {
                Constants.removeAllAssignedKeys();
                boolean start = false;
                int count = 0;
                for(String i : Constants.defaultTags) {

                    if(start) {
                        Constants.addAssignedKey(ans);
                    }
                    if(i.matches("Keys")) {
                        start = true;
                    }
                    count++;
                }
                
            }
            if(extractDirChkBox.isSelected()) {
                Settings.setExtractDir(Constants.defaultValues[22]);
            }
            if(comicsPathChkBox.isSelected()) {
                Settings.setComicsPath(Constants.defaultValues[20]);
            }
        }
    }
    
    void setScrollBarSize(int i) {
        if(i == 1) {
            Settings.setScrollSize(0);
        }
        else if(i == 2) {
            Settings.setScrollSize(10);
        }
        else {
            Settings.setScrollSize(20);
        }
    MainFrame.setScrollBarSize();
    }
    
    /**
     * main method for testing.
     * @param args to pass command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager s = new ArchiveManager();
        //MainFrame ff = new MainFrame(s);
    }
}
