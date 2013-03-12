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
 * The dialog to be displayed when login button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.4
 */
public class TranslatorLogin extends JDialog {
    
    private JLabel clientIdLabel, clientSecretLabel, info;
    private JTextField clientId;
    private JPasswordField clientSecret;
    private JCheckBox rememberMe;
    private JPanel center, clientIdPanel, clientSecretPanel, checkboxPanel,
            logInPanel, infoPanel;
    private JButton cancel, logIn;
    
    TranslatorLogin(final TranslatorDialog translatorDialog) {
        
        setTitle("Log In");
        setLayout(new BorderLayout());
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        
        clientIdLabel = new JLabel("Client Id : ");
        clientSecretLabel = new JLabel("Client Secret : ");
        clientId = new JTextField();
        if(!Settings.getClientId().equals("null")) {
            clientId.setText(Settings.getClientId());
        }
        clientId.setPreferredSize(new Dimension(250, 30));
        clientIdPanel = new JPanel();
        clientIdPanel.add(clientIdLabel);
        clientIdPanel.add(clientId);
        
        clientSecret = new JPasswordField();
        clientSecret.setPreferredSize(new Dimension(250, 30));
        clientSecretPanel = new JPanel();
        clientSecretPanel.add(clientSecretLabel);
        clientSecretPanel.add(clientSecret);
        
        rememberMe = new JCheckBox("Remember me");
        checkboxPanel = new JPanel();
        checkboxPanel.add(rememberMe);
        
        logIn = new JButton("Login");
        logIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logInActionPerformed(translatorDialog);
            }
        });
        logInPanel = new JPanel();
        logInPanel.add(logIn);
        logInPanel.add(cancel);
        info = new JLabel(" You will need a microsoft azure account to use this  feature. ");
        infoPanel = new JPanel();
        infoPanel.add(info);
        center = new JPanel(new GridLayout(0, 1));
        
        center.add(clientIdPanel);
        center.add(clientSecretPanel);
        center.add(checkboxPanel);
        center.add(logInPanel);
        center.add(infoPanel);
        getContentPane().add(center, BorderLayout.CENTER);
        setMinimumSize(new Dimension(300, 300));
        Point p = translatorDialog.getLocation();
        setLocation(p.x + Math.abs(translatorDialog.getWidth() - getWidth())/2, p.y + 
                Math.abs(translatorDialog.getHeight() - getHeight()) / 2);
        //setVisible(true);
        setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
        setVisible(true);
    }
    
    private void logInActionPerformed(TranslatorDialog t) {
        String tempClientId = clientId.getText();
        String tempClientPassword = new String(clientSecret.getPassword());
        if(rememberMe.isSelected()) {
            Settings.setClientId(tempClientId);
            Settings.setClientSecret(tempClientPassword);
        }
        LanguageTranslator.setId(tempClientId, tempClientPassword);
        t.setLogInButtonText();
        dispose();
    }
    
    /**
     * main method for testing.
     * @param args to pass command line arguments
     */
    public static void main(String args[]) {
        ArchiveManager s = new ArchiveManager();
                MainFrame ff = new MainFrame(s);
    }
    
}
