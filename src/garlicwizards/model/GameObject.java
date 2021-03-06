/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Beto
 */
public class GameObject {
    protected int iX;
    protected int iY;
    protected ImageIcon imgImage;

    public GameObject(ImageIcon imgImage, int iX, int iY){
        this.imgImage = imgImage;
        this.iX = iX;
        this.iY = iY; 
    }

    /**
     * @return the iX
     */
    public int getX() {
        return iX;
    }

    /**
     * @return the iY
     */
    public int getY() {
        return iY;
    }

    /**
     * @return the imgImage
     */
    public Image getImage() {
        return imgImage.getImage();
    }
    
    /**
     * sets the imgImage
     * @param imgImage
     */
    public void setImage(ImageIcon imgImage) {
        this.imgImage = imgImage;
    }
    
    /**
     * @return the width
     */
    public int getWidth() {
        return imgImage.getIconWidth();
    }
    
    /**
     * @return the height
     */
    public int getHeight(){
        return imgImage.getIconHeight();
    }

    /**
     * @param iX the iX to set
     */
    public void setX(int iX) {
        this.iX = iX;
    }

    /**
     * @param iY the iY to set
     */
    public void setY(int iY) {
        this.iY = iY;
    }
    
    
    
}
