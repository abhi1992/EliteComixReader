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

import java.io.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * This is the class which writes the Properties.xml file
 * @author Abhishek Banerjee
 * @version v0.0.1
 * @since v0.0.1
 */
public class XmlWriter { 

XmlWriter(String xmlFilePath, String[] tagename, String[] newValue) { 

    try { 
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFilePath);
        
        for(int i = 0; i < tagename.length; i++) {
            replaceValue(doc, tagename[i], newValue[i]);
        }
        
        Transformer t = TransformerFactory.newInstance().newTransformer();
        Result result = new StreamResult(new File(xmlFilePath));
        Source source = new DOMSource(doc);
        t.transform(source, result);
    } 
    catch(ParserConfigurationException 
            | SAXException | IOException 
            | TransformerFactoryConfigurationError | TransformerException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

}

private static void replaceValue(Document doc, String tagName, String replaceValue) {
    NodeList nodeList = doc.getElementsByTagName(tagName);
    Node node = nodeList.item(0);
    node.getFirstChild().setNodeValue(replaceValue);
}
}
