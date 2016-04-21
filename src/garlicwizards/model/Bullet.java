/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;

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
        float fDeltaX = (float)(iDestinationX - iX);
        float fDeltaY = (float)(iDestinationY - iY);
        this.setDirection(fDeltaX, fDeltaY);
    }
    
    /**
     * isExploding
     * Checks if the bullet is exploding, and if it is, it reduces the 
     * exploding timer. When the timer is over, it resets it and the condition.
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
    
}
