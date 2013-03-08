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
            frameHeight, index, zoomPixel, zoomMultiplier, constWidth, constHeight;
    private AffineTransform at;
    private int orientation = 0;
    private final short FIT_WIDTH = 0, FIT_HEIGHT = 1, ZOOM_IN = 3, ZOOM_OUT = 2
            ,ORIG_SIZE = 4, STRAIGHT = 0, LEFT = 1, RIGHT = 3, UPSIDE = 2;
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
        zoomPixel = 2;
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
            
            frameWidth  = MainFrame.getScrollPaneSize().width;
            frameHeight = MainFrame.getScrollPaneSize().height;
            
//            if(orientation % 2 == 0) {
//                imageWidth  = getImageSize().width;
//                imageHeight = getImageSize().height;
//            }
//            else {
//                imageWidth  = getImageSize().height;
//                imageHeight = getImageSize().width;
//            }
            fit(mode);
            setTransform();
            g2.drawRenderedImage(image, at);
        }
    }

    void setTransform() {
        //Center the image
        //Third Transform
        at = AffineTransform.getTranslateInstance(x, y);
        
        //Rotate the Image
        //Second Transform
        at.quadrantRotate(orientation);
        
        //Zoom Image
        //Third Transform
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
        
        int oldImageWidth = origWidth;
        int oldImageHeight = origHeight;
        
        if(orientation % 2 != 0) {
            oldImageWidth = origHeight;
            oldImageHeight = origWidth;
            //swapDimensions();
        }
        if(b == FIT_WIDTH) {
            
            if(orientation == STRAIGHT) {
                
                imageWidth = frameWidth;
                imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                
                x = 0;
                if(imageHeight >= imageWidth && !(imageHeight <= frameHeight)
                    || imageHeight < imageWidth && !(imageHeight <= frameHeight))
                    y = 0;
                else
                    y = Math.abs(frameHeight - imageHeight) / 2;
                setScale((double)imageWidth / origWidth);
            }
            else if(orientation == LEFT) {
                
                //swapDimensions();
                imageHeight = frameWidth;
                imageWidth = (imageHeight * oldImageHeight) / oldImageWidth;
                
                setScale((double)imageWidth / origWidth);
                
                if(imageHeight <= frameWidth)
                    x = (imageHeight + Math.abs(imageHeight - frameWidth) / 2);
                else
                    x = imageHeight;
                if(imageWidth >= frameHeight) {
                    y = 0;
                }
                else
                    y = Math.abs(frameHeight - imageWidth) / 2;
                swapDimensions();
            }
            else if(orientation == RIGHT) {
                //swapDimensions();
                imageHeight = frameWidth;
                imageWidth = (imageHeight * oldImageHeight) / oldImageWidth;
                
                setScale((double)imageWidth / origWidth);
                if(imageHeight <= frameWidth)
                    x = (Math.abs(imageHeight - frameWidth) / 2);
                else
                    x = 0;
                if(imageWidth >= frameHeight) {
                    y = imageWidth;
                }
                else
                    y = imageWidth + Math.abs(frameHeight - imageWidth) / 2;
                
                swapDimensions();
            }
            else if(orientation == UPSIDE) {
                imageWidth = frameWidth;
                imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                
                x = imageWidth + (frameWidth - imageWidth) / 2;
                
                if(imageHeight >= imageWidth && !(imageHeight <= frameHeight)
                        || imageHeight < imageWidth && !(imageHeight <= frameHeight))
                    y = imageHeight;
                else
                    y = imageHeight + Math.abs(frameHeight - imageHeight) / 2;
                setScale((double)imageWidth / origWidth);
            }
            setPreferredSize(new Dimension(imageWidth, imageHeight));
        }
        else if(b == FIT_HEIGHT) {
            
            if(orientation == STRAIGHT) {
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                if(frameWidth <= imageWidth ) {
                    imageWidth = frameWidth;
                    imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                }
                x = (frameWidth - imageWidth) / 2;
                if(imageHeight >= imageWidth && !(imageHeight <= frameHeight)
                    || imageHeight < imageWidth && !(imageHeight <= frameHeight))
                    y = 0;
                else
                    y = Math.abs(frameHeight - imageHeight) / 2;
                setScale((double)imageWidth / origWidth);
                setPreferredSize(new Dimension(imageWidth, imageHeight));
            }
            else if(orientation == LEFT) {
                //swapDimensions();
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                if(frameWidth <= imageWidth ) {
                    imageWidth = frameWidth;
                    imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                }
                if(frameWidth > imageWidth )
                    x = (imageWidth + Math.abs(imageWidth - frameWidth) / 2);
                else
                    x = imageWidth;
                if(imageHeight > imageWidth && imageHeight == frameHeight) {
                    y = 0;
                }
                else
                    y = Math.abs(frameHeight - imageHeight) / 2;
                setPreferredSize(new Dimension(imageWidth, imageHeight));
                swapDimensions();
                setScale((double)imageWidth / origWidth);
            }
            else if(orientation == UPSIDE ) {
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                
                if(frameWidth <= imageWidth ) {
                    imageWidth = frameWidth;
                    imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                }
                
                x = imageWidth + (frameWidth - imageWidth) / 2;
                
                if(imageHeight >= imageWidth
                        && (imageWidth > frameWidth))
                    y = imageHeight;
                else
                    y = imageHeight + Math.abs(frameHeight - imageHeight) / 2;
                setPreferredSize(new Dimension(imageWidth, imageHeight));
                setScale((double)imageWidth / origWidth);
            }
            else if(orientation == RIGHT) {
                //swapDimensions();
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                if(frameWidth <= imageWidth ) {
                    imageWidth = frameWidth;
                    imageHeight = (imageWidth * oldImageHeight) / oldImageWidth;
                }
                
                if(imageWidth <= frameWidth)
                    x = (Math.abs(imageWidth - frameWidth) / 2);
                else
                    x = 0;

                    y = imageHeight + (Math.abs(imageHeight - frameHeight) / 2);
                if(imageHeight >= frameHeight)
                    y = frameHeight;
                setPreferredSize(new Dimension(imageWidth, imageHeight));
                swapDimensions();
                setScale((double)imageWidth / origWidth);
        }
            
        }
        else if(b == ZOOM_OUT) {
            if(orientation %2 == 0) {
                imageHeight = constHeight - Math.abs(zoomPixel * zoomMultiplier);
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
            }
            else {
                imageHeight = constHeight - Math.abs(zoomPixel * zoomMultiplier);
                imageWidth = (imageHeight * oldImageHeight) / oldImageWidth;
            }
            if(orientation == 0) {
                x = (frameWidth - imageWidth) / 2;

                if(imageHeight >= imageWidth && !(mode == 0 && imageHeight <= frameHeight)
                        && !(mode == 1 && imageWidth <= frameWidth))
                    y = 0;
                else
                    y = Math.abs(frameHeight - imageHeight) / 2;
                setPreferredSize(new Dimension(imageWidth, imageHeight));
                setScale((double)imageWidth / origWidth);
            }
            if(imageHeight < frameHeight) {
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                setMode((short)1);
            }
        }
        else if(b == ZOOM_IN) {
                if(orientation == STRAIGHT) {
                    
                    imageHeight = constHeight + Math.abs(zoomPixel * zoomMultiplier);
                    imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                    x = (frameWidth - imageWidth) / 2;
                    
                    if(imageHeight >= imageWidth || imageWidth > imageHeight)
                        y = 0;
                    else
                        y = Math.abs(frameHeight - imageHeight) / 2;
                    setPreferredSize(new Dimension(imageWidth, imageHeight));
                    setScale((double)imageWidth / origWidth);
                }
                else if(orientation == LEFT) {
                    imageWidth = constHeight + Math.abs(zoomPixel * zoomMultiplier);
                    imageHeight = (imageWidth * oldImageWidth) / oldImageHeight;
                    setScale((double)imageWidth / origWidth);
                    
                    if(frameWidth > imageWidth && imageWidth > imageHeight)
                        x = (imageHeight + Math.abs(imageHeight - frameWidth) / 2);
                    else
                        x = imageHeight;
                    
                    if(imageHeight < imageWidth  || imageHeight >= frameHeight) {
                        y = 0;
                    }
                    else
                        y = Math.abs(frameHeight - imageWidth) / 2;
                    setPreferredSize(new Dimension(imageWidth, imageHeight));
                    swapDimensions();
                }
                else if(orientation == UPSIDE ) {
                    imageHeight = constHeight + Math.abs(zoomPixel * zoomMultiplier);
                    imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                    x = imageWidth + (frameWidth - imageWidth) / 2;
                    System.out.println(" " + imageHeight+" " + imageWidth);
                    if(imageHeight >= imageWidth)
                        y = imageHeight;
                    else
                        y = imageHeight + Math.abs(frameHeight - imageHeight) / 2;
                    setPreferredSize(new Dimension(imageWidth, imageHeight));
                    setScale((double)imageWidth / origWidth);
                }
                
                //knkkn
            else {
                imageHeight = constHeight - Math.abs(zoomPixel * zoomMultiplier);
                imageWidth = (imageHeight * oldImageHeight) / oldImageWidth;
            }
            if(imageHeight < frameHeight) {
                imageHeight = frameHeight;
                imageWidth = (imageHeight * oldImageWidth) / oldImageHeight;
                setMode((short)1);
            }
            setPreferredSize(new Dimension(imageWidth, imageHeight));
        }
        else if(b == ORIG_SIZE) {
            imageHeight = origHeight;
            imageWidth = origWidth;
            if(imageHeight < frameHeight) {
                
            }
        }
        
//        if(orientation % 2 != 0) {
//            //System.out.println(orientation + " " + imageHeight + " " + imageWidth);
//            swapDimensions();
//            //System.out.println(imageHeight + " " + imageWidth);
//        }
//        
//        if(orientation == 0) {
//            x = (frameWidth - imageWidth) / 2;
//            
//            if(imageHeight >= imageWidth && !(mode == 0 && imageHeight <= frameHeight)
//                    && !(mode == 1 && imageWidth <= frameWidth))
//                y = 0;
//            else
//                y = Math.abs(frameHeight - imageHeight) / 2;
//            
//            setScale((double)imageWidth / origWidth);
//        }
//        else if(orientation == 1) {
//            setScale((double)imageWidth / origWidth);
//            if(imageHeight <= frameWidth)
//                x = (imageHeight + Math.abs(imageHeight - frameWidth) / 2);
//            else
//                x = imageHeight;
//            
//            if(imageHeight < imageWidth ) {
//                y = 0;
//            }
//            else
//                y = Math.abs(frameHeight - imageWidth) / 2;
//        }
//        else if(orientation == 2 ) {
//            
//            x = imageWidth + (frameWidth - imageWidth) / 2;
//            
//            if(imageHeight >= imageWidth && !(mode == 0 && imageHeight <= frameHeight)
//                    && !(mode == 1 && imageWidth <= frameWidth))
//                y = imageHeight;
//            else
//                y = imageHeight + Math.abs(frameHeight - imageHeight) / 2;
//            
//            setScale((double)imageWidth / origWidth);
//        }
//        else if(orientation == 3) {
//            setScale((double)imageWidth / origWidth);
//            if(imageHeight <= frameWidth)
//                x = (Math.abs(imageHeight - frameWidth) / 2);
//            else
//                x = 0;
//            
//                y = imageWidth;
//            if(mode == 1)
//                y = imageWidth + (frameHeight - imageWidth) / 2;
//        }
        
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
        if(imageHeight == frameHeight) {
            zoomMultiplier = 1;
            setMode((short) 1);
            
        }
        else {
        setMode( (short) 2);
        
        constHeight = imageHeight;
        constWidth = imageWidth;
        repaint();
        if(imageHeight >= frameHeight && zoomMultiplier > -20)
            zoomMultiplier--;
        repaint();
        }
        
    }

    void zoomIn() {
        setMode( (short) 3);
        constHeight = imageHeight;
        constWidth = imageWidth;
        if(zoomMultiplier <= -20)
            zoomMultiplier += 2;
        if(zoomMultiplier <= 5)
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
