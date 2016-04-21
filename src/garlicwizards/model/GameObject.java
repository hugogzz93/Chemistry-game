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
    protected float iX;
    protected float iY;
    protected ImageIcon imgImage;

    public GameObject(ImageIcon imgImage, float iX, float iY){
        this.imgImage = imgImage;
        this.iX = iX;
        this.iY = iY; 
    }

    /**
     * @return the iX
     */
    public float getX() {
        return iX;
    }

    /**
     * @return the iY
     */
    public float getY() {
        return iY;
    }

    /**
     * @return the imgImage
     */
    public Image getImage() {
        return imgImage.getImage();
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
    public void setX(float iX) {
        this.iX = iX;
    }

    /**
     * @param iY the iY to set
     */
    public void setY(float iY) {
        this.iY = iY;
    }
    
    
    
}
