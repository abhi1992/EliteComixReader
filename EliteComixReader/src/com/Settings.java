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

//import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The dialog to be displayed when help button pressed.
 * @author Abhishek Banerjee
 * @version v0.0.2
 * @since v0.0.2
 */

public class Settings {

    private static String laf, ExtractDir;
    private static Dimension size;
    private static short fitWidth;
    private static Color defaultColor;
    private static boolean maximized, fullscreen, alwaysOnTop, loadingImage
            , PageNo, timeInfo, pageInfo;
    private static int X, Y, scrollSize;
    private static ArrayList<String> availableLaf;
//    static final String DECORATION_ARG = "decoration:";
//    static final String DEFAULT_DECORATION = "default";
//    static final String SYSTEM_DECORATION = "system";
//    static final String NO_DECORATION = "none";
//    private static String decorationStyle = NO_DECORATION;
    private static ArrayList<String> fileList;
    private static short index;
    private static String comicsPath;
//    private static String clientId, clientSecret;
    
    
    /**
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void load() throws ParserConfigurationException, SAXException, IOException {
        fileList = new ArrayList<>();
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
                    case "Laf":
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
                        availableLaf.add("System");
                        break;

                    case "Size":
                        int h = 0,w = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++)
                        {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {

                                if(fd.getNodeName().equals("Height")) {
                                    h = Integer.parseInt(fd.getTextContent().trim());
                                }

                                if(fd.getNodeName().equals("Width")) {
                                    w = Integer.parseInt(fd.getTextContent().trim());
                                }
                            }
                        }
                        size = new Dimension(w, h);
                        break;

                    case "Location":
                        h = 0;
                        w = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++) {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {

                                if(fd.getNodeName().equals("X")) {
                                    h = Integer.parseInt(fd.getTextContent().trim());
                                }

                                if(fd.getNodeName().equals("Y")) {
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

                    case "Maximized" :
                      maximized =  false;
                        break;

                    case "Color" :
                        int r = 0, g = 0, b = 0;
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++) {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {

                                if(fd.getNodeName().equals("Red")) {
                                    r = Integer.parseInt(fd.getTextContent().trim());
                                }

                                if(fd.getNodeName().equals("Green")) {
                                    g = Integer.parseInt(fd.getTextContent().trim());
                                }

                                if(fd.getNodeName().equals("Blue")) {
                                    b = Integer.parseInt(fd.getTextContent().trim());
                                }
                            }
                        }
                        setDefaultColor(new Color(r, g, b));
                        break;
                    case "ComicsPath":
                        comicsPath = child.getTextContent().trim();
                        break;
                    case "ScrollBar":
                        scrollSize = Integer.parseInt(child.getTextContent().trim());
                        //System.out.print(scrollSize);
                        break;
                    case "ExtractDir":
                        ExtractDir = child.getTextContent().trim();
                        break;
                    case "PageNo":
                        PageNo = Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                    case "timeInfo":
                        timeInfo = Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                    case "pageInfo":
                        pageInfo = Boolean.parseBoolean(child.getTextContent().trim());
                        break;
                    case "Keys":
                        n = child.getChildNodes();
                        for(int j = 0; j < n.getLength(); j++) {
                            Node fd = n.item(j);
                            if((fd instanceof Element)) {
                                for(String di : Constants.defaultTags) {
                                    if(fd.getNodeName().equals(di))
                                        Constants.assignedKeys.add(
                                                Integer.parseInt(fd.getTextContent().trim()));
                                    }
                            }
                        }
                        
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
        //System.out.println(Constants.assignedKeys.size());
    }

    static void loadFileList(File ff) {
        fileList = new ArrayList<>();
        if(ff != null) {
        File f = new File(ff.getParent());

        String arr[] = f.list();
        //Arrays.sort(arr);
        for(String h: arr)
        {
            if(h.toLowerCase().endsWith(".cbr") || h.toLowerCase().endsWith(".cbz"))
                fileList.add(ff.getParent()+"/"+h);
            //System.out.println("\n"+h);
        }
        }
        //System.out.println(f.getAbsolutePath()+"\n"+fileList.size());
        index = -1;

    }

    static File getNextComicFile() {
        if(index+1 < fileList.size()) {
            index++;
            return new File(fileList.get(index));

        }
        return null;
    }

    static File getPrevComicFile() {
        if(index-1 < fileList.size() && index-1 >=0) {
            index--;
            return new File(fileList.get(index));
        }
        return null;
    }

    public static void store(MainFrame mainFrame) {
        
        ArrayList<String> tags = new ArrayList<>(), values = new ArrayList<>();
        tags.add(Constants.defaultTags[2]);
        
        values.add(getLaf().toString());
        
        if(mainFrame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            tags.add(Constants.defaultTags[7]);
            tags.add(Constants.defaultTags[8]);
            tags.add(Constants.defaultTags[10]);
            tags.add(Constants.defaultTags[11]);
            values.add(Integer.toString(mainFrame.getFrameSize().height));
            values.add(Integer.toString(mainFrame.getFrameSize().width));
            values.add(Integer.toString(mainFrame.getX()));
            values.add(Integer.toString(mainFrame.getY()));
        }
        tags.add(Constants.defaultTags[15]);
        values.add(Boolean.toString(false));
        tags.add("fitWidth");
        values.add(Short.toString(getFitWidth()));
        tags.add("fullscreen");
        values.add(Boolean.toString(isFullscreen()));
        tags.add("alwaysOnTop");
        values.add(Boolean.toString(isAlwaysOnTop()));
        tags.add(Constants.defaultTags[17]);
        values.add(Integer.toString(getDefaultColor().getRed()));
        tags.add(Constants.defaultTags[18]);
        values.add(Integer.toString(getDefaultColor().getGreen()));
        tags.add(Constants.defaultTags[19]);
        values.add(Integer.toString(getDefaultColor().getBlue()));
        tags.add(Constants.defaultTags[20]);
        values.add(Settings.getComicsPath());
        tags.add(Constants.defaultTags[21]);
        values.add(Integer.toString(Settings.getScrollSize()));
        tags.add(Constants.defaultTags[22]);
        values.add(ExtractDir);
        tags.add(Constants.defaultTags[23]);
        values.add(Boolean.toString(PageNo));
        tags.add(Constants.defaultTags[24]);
        values.add(Boolean.toString(timeInfo));
        tags.add(Constants.defaultTags[25]);
        values.add(Boolean.toString(pageInfo));
        for(int i = 0; i < Constants.getAssignedKeys().size(); i++) {
            tags.add(Constants.defaultTags[i + Constants.START_VAL]);
            values.add(Integer.toString(Constants.getAssignedKeys().get(i)));
        }
        new XmlWriter(ExtractorModel.getAppDir() + "/Properties.xml", tags, values);
    }

    static String[] getAvailableLafName(ArrayList<String> path) {
        String names[] = new String[path.size()];
        for(int i = 0; i < path.size(); i++) {
            names[i] = getLafName(path.get(i));
        }
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
            Element rootElement = doc.createElement(Constants.defaultTags[0]);
            doc.appendChild(rootElement);

            Element BaseNode = doc.createElement(Constants.defaultTags[1]);
            rootElement.appendChild(BaseNode);

            Element node = doc.createElement(Constants.defaultTags[2]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[2]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[3]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[3]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[4]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[4]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[5]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[5]));
            BaseNode.appendChild(node);

            BaseNode = doc.createElement(Constants.defaultTags[6]);
            rootElement.appendChild(BaseNode);

            node = doc.createElement(Constants.defaultTags[7]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[7]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[8]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[8]));
            BaseNode.appendChild(node);

            BaseNode = doc.createElement(Constants.defaultTags[9]);
            rootElement.appendChild(BaseNode);

            node = doc.createElement(Constants.defaultTags[10]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[10]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[11]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[11]));
            BaseNode.appendChild(node);

            BaseNode = doc.createElement(Constants.defaultTags[12]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[12]));
            rootElement.appendChild(BaseNode);

            BaseNode = doc.createElement(Constants.defaultTags[13]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[13]));
            rootElement.appendChild(BaseNode);

            BaseNode = doc.createElement(Constants.defaultTags[14]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[14]));
            rootElement.appendChild(BaseNode);

            BaseNode = doc.createElement(Constants.defaultTags[15]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[15]));
            rootElement.appendChild(BaseNode);

            BaseNode = doc.createElement(Constants.defaultTags[16]);
            rootElement.appendChild(BaseNode);

            node = doc.createElement(Constants.defaultTags[17]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[17]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[18]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[18]));
            BaseNode.appendChild(node);

            node = doc.createElement(Constants.defaultTags[19]);
            node.appendChild(doc.createTextNode(Constants.defaultValues[19]));
            BaseNode.appendChild(node);

            BaseNode = doc.createElement(Constants.defaultTags[20]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[20]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[21]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[21]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[22]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[22]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[23]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[23]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[24]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[24]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[25]);
            BaseNode.appendChild(doc.createTextNode(Constants.defaultValues[25]));
            rootElement.appendChild(BaseNode);
            
            BaseNode = doc.createElement(Constants.defaultTags[26]);
            
            boolean start = false;
            int count = 0;
            for(String i : Constants.defaultTags) {
                
                if(start) {
                    node = doc.createElement(i);
                    node.appendChild(doc.createTextNode(Constants.defaultValues[count]));
                    BaseNode.appendChild(node);
                }
                if(i.matches("Keys")) {
                    start = true;
                }
                count++;
            }
            rootElement.appendChild(BaseNode);
            //ToDo Work here
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

//    public static String getDecorationStyle() {
//        return decorationStyle;
//    }

    public static String getComicsPath() {
        return comicsPath;
    }

    public static String getExtractDir() {
        return ExtractDir;
    }

    public static boolean isPageInfo() {
        return pageInfo;
    }
    
    public static boolean isTimeInfo() {
        return timeInfo;
    }

    public static void setPageNo(boolean PageNo) {
        Settings.PageNo = PageNo;
    }

    public static boolean isPageNo() {
        return PageNo;
    }

    public static void setPageInfo(boolean Info) {
        Settings.pageInfo = Info;
    }
    
    public static void setTimeInfo(boolean Info) {
        Settings.timeInfo = Info;
    }

    public static void setExtractDir(String ExtractDir) {
        Settings.ExtractDir = ExtractDir;
    }

    public static void setComicsPath(String comicsPath) {
        Settings.comicsPath = comicsPath;
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

    public static int getScrollSize() {
        return scrollSize;
    }

    public static boolean isLoadingImage() {
        return loadingImage;
    }
    
    public static boolean isFullscreen() {
        return fullscreen;
    }
    
    public static boolean isAlwaysOnTop() {
        return alwaysOnTop;
    }

    public static void setLoadingImage(boolean loadingImage) {
        Settings.loadingImage = loadingImage;
    }

    public static void setScrollSize(int scrollSize) {
        Settings.scrollSize = scrollSize;
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
