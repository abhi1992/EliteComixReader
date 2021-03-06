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

    private JLabel jLabel1, jLabel2, color, lafLabel;
    private JTabbedPane tab;
    private JPanel jPanel1, jPanel2, generalPanel, viewPanel, generalCenterPanel, viewCenterPanel, lafPanel, colorPanel,
            comicsPathPanel;
    private JButton jButton1, jButton2, chooseColor, browse;
    private JComboBox<String> laf;
    private JTextField comicsPath;

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
        browse = new JButton("Browse");
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                comicsPathActionPerformed(mainFrame);
            }
        });
        browse.setFocusable(false);

        comicsPathPanel.add(comicsPath);
        comicsPathPanel.add(browse);

        generalCenterPanel = new JPanel();
        generalCenterPanel.setLayout(new GridLayout(2,1));

        generalCenterPanel.add(colorPanel);
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
        viewCenterPanel.setLayout(new GridLayout());
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
        viewCenterPanel.add(lafPanel);
        //centerPanel.add(themePanel);
        viewPanel.add(viewCenterPanel, BorderLayout.CENTER);
        viewPanel.add(jPanel2, BorderLayout.SOUTH);

        tab.addTab("View", viewPanel);

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
    /**
     * main method for testing.
     * @param args to pass command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager s = new ArchiveManager();
        //MainFrame ff = new MainFrame(s);
    }

}
