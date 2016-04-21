/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;
import java.awt.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Beto
 */
public class Projectile extends GameObject {
    
    protected float fDirectionX; // value bet. 0-1
    protected float fDirectionY; // value bet. 0-1
    protected int iSpeed;
    protected int iType = -1;
    
    /**
     * move
     * Method that updates the projectile X and Y with the direction
     * vector and its speed.
     */
    public void move(){
        this.setX(this.iX + (int) (fDirectionX * iSpeed));
        this.setY(this.iY + (int) (fDirectionY * iSpeed));
    }
    
    /**
     * setDirection
     * Updates the projectile's direction components.
     * @param iDeltaX
     * @param iDeltaY
     */
    public void setDirection(float iDeltaX, float iDeltaY){
        this.fDirectionX = (iDeltaX) / (Math.abs(iDeltaX) + Math.abs(iDeltaY));
        this.fDirectionY = (iDeltaY) / (Math.abs(iDeltaY) + Math.abs(iDeltaX));
        System.out.printf("> Setting direction = [%f, %f]\n", this.fDirectionX, this.fDirectionY);
    }
    
    /**
     * setSpeed
     * Updates the projectile's speed attribute.
     * @param iSpeed
     */
    public void setSpeed(int iSpeed){
        System.out.printf("> Setting speed = %d\n", iSpeed);
        this.iSpeed = iSpeed;
    }
    
    /**
     * Constructor
     * @param imgImage
     * @param iX
     * @param iY
     */
    public Projectile(ImageIcon imgImage, int iX, int iY, int iType) {
        super(imgImage, iX, iY);
        this.iType = iType;
    }   
}
