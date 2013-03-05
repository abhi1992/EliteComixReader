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

/**
 *
 * @author Abhishek Banerjee
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

class ImagePanel extends JPanel {
    
    private short mode = 1;
    private BufferedImage image;
    private double scale, x, y;
    private int origWidth, origHeight, imageWidth, imageHeight, frameWidth,
            frameHeight, index, zoomPixel, zoomMultiplier;
    private AffineTransform at;
    private int orientation = 0;
    //private ArrayList<BufferedImage> pages;


    //Constructor
    public ImagePanel() {
        this(null);
    }

    public ImagePanel(BufferedImage img ) {
        //pages = new ArrayList<>(21);
        //if(img != null)
            //pages.add(img);
        index = 0;
        scale = 1.0;
        zoomPixel = 20;
        zoomMultiplier = 0;
        orientation = 0;
        loadImage(img);

        setBackground(Settings.getDefaultColor());

    }

    void setEmptyImage() {
        image = null;
        scale = 1.0;
        index = 0;
        setBackground(Settings.getDefaultColor());
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        setBackground(Settings.getDefaultColor());
        if(image != null) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//            if(!trans) {
                //adjustImage();
                frameWidth  = MainFrame.getScrollPaneSize().width;
                frameHeight = MainFrame.getScrollPaneSize().height;
                if(orientation % 2 == 0) {
                    imageWidth  = getImageSize().width;
                    imageHeight = getImageSize().height;
                }
                else {
                    imageWidth  = getImageSize().height;
                    imageHeight = getImageSize().width;
                }
                //System.out.println(mode);
                fit(mode);
                //x = (frameWidth - imageWidth) / 2;
                setTransform();
                
                //adjustImage();
//            }

            g2.drawRenderedImage(image, at);
        }
        else {
            setBackground(Settings.getDefaultColor());
        }
    }
//
//    void adjustImage() {
//
//        if(y<0 && MainFrame.getScrollPaneSize().height < frameHeight) {
//            y += (frameHeight - MainFrame.getScrollPaneSize().height) / scale;
//        }
//        if(y<0 && MainFrame.getScrollPaneSize().width < frameWidth) {
//            y +=  (frameWidth - MainFrame.getScrollPaneSize().width)/ scale;
//        }
//        if(y>0 && orientation == 0) {
//            y = 0;
//        }
//        
//        
//    }

    void setTransform() {
        //Center the image
        //Third Transform
        if(orientation == 0) {
            at = AffineTransform.getTranslateInstance(x, y);
        }
        else if(orientation == 1) {
            at = AffineTransform.getTranslateInstance(x, y);
        }
        else if(orientation == 2) {
            at = AffineTransform.getTranslateInstance(x, y);
        }
        else if(orientation == 3) {
            at = AffineTransform.getTranslateInstance(x, y);
        }
        if (orientation == 0) {
            at.quadrantRotate(orientation);
        }
        else if(orientation == 1) {
            at.quadrantRotate(orientation);
        }
        else if(orientation == 2) {
            at.quadrantRotate(orientation);
        }
        else if(orientation == 3) {
            at.quadrantRotate(orientation);
        }
        
        at.scale(scale, scale);
        
    }

    void toggleMode(boolean b) {
        if(b)
            mode = 0;
        else
            mode = 1;
    }

    void setMode(short s) {
        mode = s;
    }

    short getMode() {
        return mode;
    }


    void fit(short b) {
        
        int oldImageWidth = imageWidth;
        int oldImageHeight = imageHeight;
        
        if(b == 0) {
            
            imageWidth = frameWidth;
            imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
            
        }
        else if(b == 1) {
            imageHeight = frameHeight;
            imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            
        }
        else if(b == 2) {
            //imageHeight *= scale;
            imageHeight += zoomPixel * zoomMultiplier;
            imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            if(imageHeight < frameHeight + zoomPixel) {
                imageHeight = frameHeight + zoomPixel;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            }
            //System.out.println("zoom in\nh= "+imageHeight+"\nw= "+imageWidth);
            
        }
        else if(b == 3) {
            //imageHeight *= scale;
            imageHeight += zoomPixel * zoomMultiplier;
            imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            if(imageHeight < frameHeight + zoomPixel) {
                imageHeight = frameHeight + zoomPixel;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            }
            //System.out.println("zoom out \nh= "+imageHeight+"\nw= "+imageWidth);
        }
        else if(b == 4) {
            imageHeight = origHeight;
            imageWidth = origWidth;
            if(imageHeight < frameHeight + zoomPixel) {
                imageHeight = frameHeight + zoomPixel;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            }
        }
        setPreferredSize(new Dimension(imageWidth, imageHeight));
        if(orientation % 2 != 0) {
            swapDimensions();
        }
        
        if(orientation == 0) {
            x = (frameWidth - imageWidth) / 2;
            y = 0;
            setScale((double)imageWidth / origWidth);
        }
        else if(orientation == 1) {
            setScale((double)imageWidth / origWidth);
            if(imageHeight <= frameWidth)
                x = (imageHeight + Math.abs(imageHeight - frameWidth) / 2);
            else
                x = imageHeight;
            y = 0;
            //System.out.println(x + " "+imageHeight+" " + getImageSize().height * scale);
        }
        else if(orientation == 2) {
            
            x = imageWidth + (frameWidth - imageWidth) / 2;
            
            y = imageHeight;
            setScale((double)imageWidth / origWidth);
        }
        else if(orientation == 3) {
            setScale((double)imageWidth / origWidth);
            if(imageHeight <= frameWidth)
                x = (Math.abs(imageHeight - frameWidth) / 2);
            else
                x = 0;
            
                y = imageWidth;
            if(mode == 1)
                y = frameHeight;
        }
        
        revalidate();
    }

    boolean isImageEmpty(int index) {
        return image == null;
    }

    int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    void zoomOut() {
        setMode( (short) 2);
        if(imageHeight > frameHeight + zoomPixel)
            zoomMultiplier--;
        repaint();
    }

    void zoomIn() {
        setMode( (short) 3);
        if(zoomMultiplier <= -20)
            zoomMultiplier += 2;
        if(zoomMultiplier <= 50)
            zoomMultiplier++;
        repaint();
    }
    
    void origSize(boolean val) {
        if(val)
            setMode( (short) 4);
        else
            setMode((short) 1);
        repaint();
    }

    void setBackgroundColor(Color c) {
        setBackgroundColor(c);
    }

    void setIndex(int index) {
        this.index = index;
    }

    int getIndex() {
        return index;
    }



    protected Dimension getImageSize() {
        int w = image.getWidth();
        int h = image.getHeight();
        return new Dimension(w, h);
    }

    void setScale(double s) {
        scale = s;
        repaint();
    }

    double getScale() {
        return scale;
    }

    void setX(double s) {
        x = s;
    }

    void setY(double s) {
        y = s;
    }

    void setImageHeight(int s) {
        imageHeight = s;
    }

    void setFrameHeight(int s) {
        frameHeight = s;
    }

    void swapDimensions() {
        int temp = imageHeight;
        imageHeight = imageWidth;
        imageWidth = temp;
    }

    double getXPos() {
        return x;
    }

    double getYPos() {
        return y;
    }

    int getImageHeight() {
        return imageHeight;
    }

    int getFrameHeight() {
        return frameHeight;
    }

    int loadImage(BufferedImage input) {
        if(input != null) {

            image = input;
            origWidth = getImageSize().width;
            origHeight = getImageSize().height;
            y=0;
            return 0;
        }
        return -1;
    }

    void displayImage(BufferedImage img) {
        if(img != null) {

            image = img;
            origWidth = getImageSize().width;
            origHeight = getImageSize().height;
            y=0;
        }
            
    }

    void prevPage(ArchiveManager ext) {
        if(index - 1 >= 0) {
            index--;
            try {
                    int val = loadImage(ext.getImage(index));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            repaint();
        }
    }

    void nextPage(ArchiveManager ext) {

        if(index + 1 < ArchiveManager.getSize()) {
            index++;
            try {
                    int val = loadImage(ext.getImage(index));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            
            repaint();
        }
    }

    void goToPage(int page) {

        if(page <= ArchiveManager.getSize()) {
            try {
                    int val = loadImage(ArchiveManager.getImage(page));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            index = page;
            repaint();
        }
    }

    public static void main(String args[]) {
        
    }

}

