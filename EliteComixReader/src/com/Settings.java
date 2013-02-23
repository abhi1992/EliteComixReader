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
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * The dialog to be displayed when help button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.2
 * @since v0.0.2
 */
public class Settings {
    
    private static String laf;
    private static Dimension size;
    private static short fitWidth;
    private static Color defaultColor;
    private static boolean maximized, fullscreen, alwaysOnTop;
    private static int X, Y;
    private static ArrayList<String> availableLaf;
    static final String DECORATION_ARG = "decoration:";
    static final String DEFAULT_DECORATION = "default";
    static final String SYSTEM_DECORATION = "system";
    static final String NO_DECORATION = "none";
    private static String decorationStyle = NO_DECORATION;
    /**
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public static void load() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File f = new File(ExtractorModel.getAppDir()+"/Properties.xml");
        if(f.exists()) {
        Document doc = builder.parse(f);
        Element root = doc.getDocumentElement();
        if("Properties".equals(root.getTagName())) {
            NodeList children = root.getChildNodes();
            for(int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if(child instanceof Element) {
                    Element childElement = (Element) child;
                switch(childElement.getTagName()) {
                    case "laf":
                        availableLaf = new ArrayList<>();
                        NodeList n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++)
                        {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {
                                
                                if(fd.getNodeName().equals("Default")) {
                                    laf = fd.getTextContent().trim();
                                }
                                else {
                                    availableLaf.add(fd.getTextContent().trim());
                                }
                            }
                        }
                        break;
                    
                    case "size":
                        int h = 0,w = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++)
                        {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {
                            
                                if(fd.getNodeName().equals("height")) {
                                    h = Integer.parseInt(fd.getTextContent().trim());
                                }
                        
                                if(fd.getNodeName().equals("width")) {
                                    w = Integer.parseInt(fd.getTextContent().trim());
                                }
                            }
                        }
                        size = new Dimension(w, h);
                        break;
                    
                    case "location":
                        h = 0;
                        w = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++) {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {
                            
                                if(fd.getNodeName().equals("x")) {
                                    h = Integer.parseInt(fd.getTextContent().trim());
                                }
                        
                                if(fd.getNodeName().equals("y")) {
                                    w = Integer.parseInt(fd.getTextContent().trim());
                                }
                            }
                        }
                        X = h; 
                        Y = w;
                        break;
                        
                    case "fitWidth":
                        String text = child.getTextContent().trim();
                        fitWidth = Short.parseShort(text);
                        break;
                    
                    case "fullscreen":
                        fullscreen = Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                    
                    case "alwaysOnTop" :
                        alwaysOnTop = Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                        
                    case "maximized" :
                      maximized =  Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                    
                    case "color" :
                        int r = 0, g = 0, b = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++) {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {
                            
                                if(fd.getNodeName().equals("red")) {
                                    r = Integer.parseInt(fd.getTextContent().trim());
                                }
                        
                                if(fd.getNodeName().equals("green")) {
                                    g = Integer.parseInt(fd.getTextContent().trim());
                                }
                                
                                if(fd.getNodeName().equals("blue")) {
                                    b = Integer.parseInt(fd.getTextContent().trim());
                                }
                            }
                        }
                        setDefaultColor(new Color(r, g, b));
                        
                        break;
                    default :
                        break;
                }
            }
        }
        }
        } else {
            createPropertiesFile();
            load();
        }
        
    }
    
    public static void store(MainFrame mainFrame) {
        String taglist[] = {"Default", "height", "width", "x" ,"y", "maximized",
            "fitWidth", "fullscreen", "alwaysOnTop",
        "red", "green", "blue"}, values[] = new String[12];
        values[0] = getLaf().toString();
        values[1] = Integer.toString(mainFrame.getFrameSize().height);
        values[2] = Integer.toString(mainFrame.getFrameSize().width);
        values[3] = Integer.toString(mainFrame.getX());
        values[4] = Integer.toString(mainFrame.getY());
        values[5] = Boolean.toString(mainFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH);
        values[6] = Short.toString(getFitWidth());
        values[7] = Boolean.toString(isFullscreen());
        values[8] = Boolean.toString(isAlwaysOnTop());
        values[9] = Integer.toString(getDefaultColor().getRed());
        values[10] = Integer.toString(getDefaultColor().getGreen());
        values[11] = Integer.toString(getDefaultColor().getBlue());
        new XmlWriter(ExtractorModel.getAppDir() + "/Properties.xml", taglist, values);
    }
    
    static String[] getAvailableLafName(ArrayList<String> path) {
        String names[] = new String[path.size() + 1];
        for(int i = 0; i < path.size(); i++) {
            names[i] = getLafName(path.get(i));
        }
        names[path.size()] = "System";
        return names;
    }
    
    /**
     * 
     * @param path full class path of the look and feel
     * @return name of the look and feel
     * @since v0.0.2
     */
    static String getLafName(String path) {
        String name = null;
        if(!"System".equals(path)) {
        
        StringTokenizer st = new StringTokenizer(path, ".");
        while(st.hasMoreTokens())
        {
            name = st.nextToken();
        }
        name = name.substring(0, name.indexOf("LookAndFeel"));
        }
        else {
            name = "System";
        }
        return name;
    }
    
    private static void createPropertiesFile() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Properties");
            doc.appendChild(rootElement);
            
            Element BaseNode = doc.createElement("laf");
            rootElement.appendChild(BaseNode);
            
            Element node = doc.createElement("Default");
            node.appendChild(doc.createTextNode("System"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("Nimbus");
            node.appendChild(doc.createTextNode("javax.swing.plaf.nimbus.NimbusLookAndFeel"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("Motif");
            node.appendChild(doc.createTextNode("com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("Metal");
            node.appendChild(doc.createTextNode("javax.swing.plaf.metal.MetalLookAndFeel"));
            BaseNode.appendChild(node);
            
            BaseNode = doc.createElement("size");
            rootElement.appendChild(BaseNode);
            
            node = doc.createElement("height");
            node.appendChild(doc.createTextNode("300"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("width");
            node.appendChild(doc.createTextNode("200"));
            BaseNode.appendChild(node);
            
            BaseNode = doc.createElement("location");
            rootElement.appendChild(BaseNode);
            
            node = doc.createElement("x");
            node.appendChild(doc.createTextNode("50"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("y");
            node.appendChild(doc.createTextNode("50"));
            BaseNode.appendChild(node);
            
            BaseNode = doc.createElement("fitWidth");
            BaseNode.appendChild(doc.createTextNode("1"));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement("fullscreen");
            BaseNode.appendChild(doc.createTextNode("false"));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement("alwaysOnTop");
            BaseNode.appendChild(doc.createTextNode("false"));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement("maximized");
            BaseNode.appendChild(doc.createTextNode("true"));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement("color");
            rootElement.appendChild(BaseNode);
            
            node = doc.createElement("red");
            node.appendChild(doc.createTextNode("0"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("green");
            node.appendChild(doc.createTextNode("0"));
            BaseNode.appendChild(node);
            
            node = doc.createElement("blue");
            node.appendChild(doc.createTextNode("0"));
            BaseNode.appendChild(node);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ExtractorModel.getAppDir()+"/Properties.xml"));
            
            transformer.transform(source, result);

        } catch (ParserConfigurationException | DOMException | TransformerFactoryConfigurationError 
                | IllegalArgumentException | TransformerException pce) {
        //pce.printStackTrace();
        }
	

    }

    public static short getFitWidth() {
        return fitWidth;
    }

    public static Color getDefaultColor() {
        return defaultColor;
    }

    public static String getLaf() {
        return laf;
    }

    public static String getDecorationStyle() {
        return decorationStyle;
    }

    public static void setDecorationStyle(String decorationStyle) {
        Settings.decorationStyle = decorationStyle;
    }

    public static Dimension getSize() {
        return size;
    }

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    public static ArrayList<String> getAvailableLafPath() {
        return availableLaf;
    }

    public static boolean isFullscreen() {
        return fullscreen;
    }

    public static boolean isAlwaysOnTop() {
        return alwaysOnTop;
    }

    public static void setAlwaysOnTop(boolean alwaysOnTop) {
        Settings.alwaysOnTop = alwaysOnTop;
    }
    
    public static void setFullscreen(boolean fullscreen) {
        Settings.fullscreen = fullscreen;
    }

    public static void setX(int X) {
        Settings.X = X;
    }

    public static void setY(int Y) {
        Settings.Y = Y;
    }

    public static void setDefaultColor(Color defaultColor) {
        Settings.defaultColor = defaultColor;
    }

    public static void setFitWidth(short fitWidth) {
        Settings.fitWidth = fitWidth;
    }

    public static void setLaf(String laf) {
        Settings.laf = laf;
    }

    public static void setSize(Dimension size) {
        Settings.size = size;
    }

    public static boolean isMaximized() {
        return maximized;
    }

    public static void setMaximized(boolean maximized) {
        Settings.maximized = maximized;
    }
    
}
