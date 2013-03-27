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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Keys Panel
 * @author Abhishek Banerjee
 * @version v0.0.8
 * @since v0.0.8
 */
public class KeysPanel extends JPanel{
    
    private JLabel keys[], values[], left;
    private JButton change[], close;
    private JPanel bottom, center;
    private JScrollPane js;
    
    public KeysPanel(final SettingsDialog s) {
        
        setLayout(new BorderLayout());
        
        left = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/keyboard.png")));
        
        bottom = new JPanel();
        bottom.setLayout(new FlowLayout());

        close = new JButton("Close");
        close.setFocusable(false);
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                s.dispose();
            }
        });
        bottom.add(close);
        center = new JPanel(new GridLayout(0, 3));
        js = new JScrollPane(center);
        
        keys = new JLabel[Constants.getAssignedKeys().size()];
        values = new JLabel[Constants.getAssignedKeys().size()];
        change = new JButton[Constants.getAssignedKeys().size()];
        for(int i = 0; i < Constants.getAssignedKeys().size(); i++) {
            keys[i] = new JLabel(Constants.defaultTags[i + Constants.START_VAL]);
            values[i] = new JLabel(KeyEvent.getKeyText(Constants.getAssignedKeys().get(i)));
            change[i] = new JButton("...");
            final int j = i;
            change[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    changeButttonAction(s, j);
                }
            });
            change[i].setFocusable(false);
            center.add(keys[i]);
            center.add(values[i]);
            center.add(change[i]);
        }
        center.setPreferredSize(new Dimension(300, Constants.getAssignedKeys().size()*25));
        center.setVisible(true);
        js.setViewportView(center);
        js.setVisible(true);
        add(left, BorderLayout.WEST);
        add(bottom, BorderLayout.SOUTH);
        add(js, BorderLayout.CENTER);
        setVisible(true);
    }
    private void changeButttonAction(final SettingsDialog s, final int i) {
        final JDialog p = new JDialog(s, "Shortcut Key for " + Constants.defaultTags[i + Constants.START_VAL]);
        p.setMinimumSize(new Dimension(200, 200));
        p.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedAction(s, e, i);
                p.setVisible(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        Point pp = new Point(0, 0);
        if(s != null) {
            pp = s.getLocation();
        }
        p.setLocation(pp.x + Math.abs(s.getWidth() - getWidth())/2, pp.y +
                Math.abs(s.getHeight() - getHeight()) / 2);
        p.setVisible(true);
    }
    private void keyPressedAction(SettingsDialog s, KeyEvent e, int i) {
        int val = 0;
        val = e.getKeyCode();
        ArrayList<Integer> temp = Constants.assignedKeys;
        int f = 0;
        for(f = 0; f < temp.size(); f++) {
            if(temp.get(f) == val) {
                int ans = JOptionPane.showConfirmDialog(center, "This key Shortcut Already exists.\n"
                        + "Do you wish to continue ??", "Collision", JOptionPane.YES_NO_CANCEL_OPTION);
                if(ans == 0) {
                    Constants.addAssignedKey(i, val);
                    values[i].setText("" + KeyEvent.getKeyText(val));
                    //changeButttonAction(s, f);
                }
            }
        }
        if(f == temp.size()) {
            Constants.addAssignedKey(i, val);
            values[i].setText("" + KeyEvent.getKeyText(val));
        }
        
    }
}
