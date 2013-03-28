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
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;
/**
 * The dialog to be displayed when translate button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.4
 */
public class TranslatorDialog extends JDialog{
    
    private JLabel info, image, toLabel;
    private JPanel centerPanel, comboPanel, translateButtonPanel, accountPanel,
            infoPanel;
    private JTextArea input, output;
    private JComboBox<String> from, to;
    private JPanel jPanel, translator, inputPanel, outputPanel;
    private JButton close, translate, register, logIn;
    private JScrollPane inputScrollPane, outputScrollPane;
    
    TranslatorDialog(final MainFrame mainFrame) {
        
        setTitle("Translator");
        setLayout(new BorderLayout());
        image = new JLabel(
                new ImageIcon(getClass().getResource("/Resources/translate.png")));
        
        centerPanel = new JPanel(new GridLayout(0, 1));
        input = new JTextArea();
        output = new JTextArea();
        output.setEditable(false);
        translator = new JPanel(new GridLayout(0, 1));
        translate = new JButton("Translate");
        logIn = new JButton("Log In");
        logIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logInActionPerformed();
            }
        });
        register = new JButton("Register");
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registerActionPerformed();
            }
        });
        info = new JLabel("You will need a microsoft azure account to use this  feature.");
//        final LanguageTranslator languageTranslator = new LanguageTranslator();
        
//        if(!"null".equals(Settings.getClientSecret())) {
//            LanguageTranslator.setId(Settings.getClientId(), Settings.getClientSecret());
//            logIn.setText("Log Out");
//        }
        
        translate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                translateActionPerformed(languageTranslator);
            }
        
        });
        
        try {
//            from = new JComboBox<>(LanguageTranslator.getAvailableLanguages());
//            to = new JComboBox<>(LanguageTranslator.getAvailableLanguages());
            to.removeItemAt(0);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        comboPanel = new JPanel();
        translateButtonPanel = new JPanel();
        accountPanel = new JPanel();
        infoPanel = new JPanel();
        
        toLabel = new JLabel(" To :");
        comboPanel.add(from);
        comboPanel.add(toLabel);
        comboPanel.add(to);
        translateButtonPanel.add(translate);
        accountPanel.add(logIn);
        accountPanel.add(register);
        infoPanel.add(info);
        translator.add(comboPanel);
        translator.add(translateButtonPanel);
        translator.add(accountPanel);
        translator.add(infoPanel);
        inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
        outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        
        inputScrollPane = new JScrollPane(input);
        inputPanel.add(inputScrollPane);
        outputScrollPane = new JScrollPane(output);
        outputPanel.add(outputScrollPane);
        
        centerPanel.add(inputPanel);
        
        centerPanel.add(translator);
        centerPanel.add(outputPanel);
        close = new JButton("Close");
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                dispose();
            }
        });
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(close);
        getContentPane().add(image, BorderLayout.WEST);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(jPanel, BorderLayout.SOUTH);
        setMinimumSize(new Dimension(500, 600));
        Point p = mainFrame.getLocation();
        setLocation(p.x + Math.abs(mainFrame.getWidth() - getWidth())/2, p.y + 
                Math.abs(mainFrame.getHeight() - getHeight()) / 2);
        
        setPreferredSize(new Dimension(500, 500));
        setResizable(false);
        setModalityType(
           Dialog.ModalityType.TOOLKIT_MODAL);
        setVisible(true);
    }
    
//    private void translateActionPerformed(LanguageTranslator languageTranslator) {
//        languageTranslator.setLanguage1(from.getSelectedItem().toString());
//        languageTranslator.setLanguage2(to.getSelectedItem().toString());
//        System.out.println(""+languageTranslator.getLanguage1());
//        if(!"".equals(input.getText())) {
//            try {
//                output.setText(languageTranslator.Translate(input.getText()));
//                
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, ex);
//            }
//        }
//    }
    
    private void registerActionPerformed() {
        if(Desktop.isDesktopSupported()) {
            URL u;
            try {
                u = new URL("https://datamarket.azure.com/dataset/1899a118-d202-492c-aa16-ba21c33c06cb");
                Desktop.getDesktop().browse(u.toURI());
                } catch (URISyntaxException | IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                
        }
    }
    
    private void logInActionPerformed() {
        if("Log In".equals(logIn.getText())) {
//            new TranslatorLogin(this);
        } else {
//            Settings.setClientSecret("null");
            setLogInButtonText();
        }
    }
    
    public void setLogInButtonText() {
        if("Log In".equals(logIn.getText())) {
            logIn.setText("Log Out");
        } else {
            logIn.setText("Log In");
        }
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
