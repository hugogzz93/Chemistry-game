/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Beto
 */
public class Bullet extends Projectile {
    
    private int iDestinationX;          // X component of destination point
    private int iDestinationY;          // Y component of destination point
    private int iExplodeTimer = 750;
    private int iDoF = 10;              // Degrees of freedom to reach dest.
    private boolean bExploding = false;
    
    /**
     * Constructor
     * 
     * @param imgImage
     * @param iX
     * @param iY
     * @param iType
     * @param iDestinationX
     * @param iDestinationY
     */
    public Bullet(ImageIcon imgImage, int iX, int iY, int iType, 
            int iDestinationX, int iDestinationY) {
        super(imgImage, iX, iY, iType);
        this.iDestinationX = iDestinationX;
        this.iDestinationY = iDestinationY;
        float fDeltaX = (float)(iDestinationX - (iX - this.getWidth()/2));
        float fDeltaY = (float)(iDestinationY - (iY - this.getHeight()/2));
        System.out.printf("Positions = X: %d, Y: %d\n", iX, iY);
        System.out.printf("Deltas = dX: %.2f, dY: %.2f\n", fDeltaX, fDeltaY);
        this.setDirection(fDeltaX, fDeltaY);
    }
    
    /**
     * isExploding
     * 
     * Checks if the bullet is exploding, and if it is, it reduces the 
     * exploding timer. When the timer is over, it resets it and the condition.
     * 
     * @return <code>bExploding</code>
     */
    public boolean isExploding(){
        if (bExploding){
            if (iExplodeTimer > 0){
                iExplodeTimer -= 1;
            } else {
                iExplodeTimer = 750; // resetting the timer
                bExploding = false;
            }
        }
        return bExploding;
    }
    
    /**
     * move
     * 
     * Superclass overriden method that also checks if the bullet has to
     * explode in contact to the mouse click destination zone.
     */
    @Override
    public void move(){
        super.move();
        
        // if the bullet direction in X is negative, invert the degrees of freedom
        int iAuxDoF = (fDirectionX > 0) ? iDoF : -iDoF;
        
        // if the bullet has reached the enclosed area of its destination
        if (iX <= (iDestinationX + iAuxDoF) && iX >= (iDestinationX - iAuxDoF) &&
            iY <= (iDestinationX + iAuxDoF) && iY >= (iDestinationX - iAuxDoF))
        {
            bExploding = true;
        }
    }
    
    /**
     * isMoving
     * Checks if the bullet is in motion.
     * @return <code>iSpeed > 0</code>
     */
    public boolean isMoving() {
        return (iSpeed > 0);
    }
    
    /** 
     * collidesWithCoordinates
     * 
     * Method to check if the <code>Bullet</code> object collides with a point
     * int the screen. Uses temporal <code>Rectangle</code> object to achieve this.
     * 
     * @param iX
     * @param iY
     * @return true if collided, false otherwise
     * 
     */
    public boolean collidesWithCoordinates(int iX, int iY) {
        Rectangle recObjeto = new Rectangle(this.getX(),this.getY(),
                this.getWidth(), this.getHeight());
        return recObjeto.contains(iX, iY);
    }
    
    /** 
     * collidesWithObject
     * 
     * Method to check if the <code>Bullet</code> object collides with another 
     * <code>GameObject</code> on the screen. Uses temporal 
     * <code>Rectangle</code> object to achieve this.
     * 
     * @param gobjObject
     * @return  true if collided, false otherwise
     * 
     */
    public boolean collidesWithObject(GameObject gobjObject) {
        Rectangle recObjeto = new Rectangle(this.getX(),this.getY(),
                this.getWidth(), this.getHeight());
        
        Rectangle recParametro = new Rectangle(gobjObject.getX(),
                gobjObject.getY(), gobjObject.getWidth(), gobjObject.getHeight());
        
        return recObjeto.intersects(recParametro);
    }

}
