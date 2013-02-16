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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * This class is the panel in which shows thumbnail of book marked pages.
 * @author Abhishek
 * @version v0.0.1
 * @since v0.0.1
 */
public class BookmarkIcon extends JPanel {

    private BufferedImage img;
    private final int panelWidth = 233, panelHeight = 255;
    
    /**
     * Constructor
     * @since v0.0.1
     */
    BookmarkIcon(BufferedImage image) {
        //setBackground(Color.BLACK);
        img = image;
        setBorder(new BevelBorder(0));
    }
    
    /**
     * paints the panel
     * @param g graphics object
     * @return null
     */
    @Override
    protected void paintComponent(Graphics g)  
    {
        super.paintComponent(g);
        AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        double scale = scale(img);
        double p[] = setTranslate(img);
        at.translate(p[0], p[1]);
        at.scale(scale, scale);
        
        
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
                                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawRenderedImage(img, at);
            
    }
    
    /**
     * Sets the scale
     * @param img the BufferedImage to scale
     * @return scale value in double
     * @since v0.0.1
     */
    private double scale(BufferedImage img) {
        double scale;
        int height = img.getHeight();
        int width = img.getWidth();
        
        if(height >= width) {
            scale = (double) panelHeight / height;
        } else {
            scale = (double) panelWidth / width;
        }
        return scale;
    }
    
    /**
     * Sets the translate value
     * @param img the BufferedImage to translate
     * @return translate point value in double[]
     * @since v0.0.1
     */
    private double[] setTranslate(BufferedImage img) {
        
        double scale, p[] = {0, 0};
        int height = img.getHeight();
        int width = img.getWidth();
        
        if(height >= width) {
            scale = (double) panelHeight / height;
            width = (int) (scale * width);
            p[0] = (panelWidth - width) / 2;
            p[1] = 0;
        }
        else {
            scale = (double) panelWidth / width;
            height = (int) scale * height;
            p[1] = (panelHeight - height) / 2;
            p[0] = 0;
        }
        return p;
    }
}
