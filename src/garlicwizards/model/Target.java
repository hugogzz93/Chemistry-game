/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards.model;

import static garlicwizards.Game.AMMOUNT_TYPES;
import static garlicwizards.Game.MAX_SPEED;
import static garlicwizards.Game.MIN_SPEED;
import static garlicwizards.Game.SCREEN_HEIGHT;
import static garlicwizards.Game.SCREEN_WIDTH;
import javax.swing.ImageIcon;

/**
 *
 * @author Beto
 */
public class Target extends Projectile{
        
    public Target(ImageIcon imgImage, int iX, int iY, int iType) {
        super(imgImage, iX, iY, iType);
    }

    /**
     * reset
     * 
     * Sets a random speed, x-coordinate and destination.
     * @returns void
     */        
    public void reset() {
        int posX = (int) (Math.random()*SCREEN_WIDTH);
            int iDestX = (int) (Math.random()* (SCREEN_WIDTH/3) + SCREEN_WIDTH/3);
            int iSpeed = (int) (MIN_SPEED + (int)(Math.random() * ((MAX_SPEED - MIN_SPEED) + 1)));
            
            setSpeed(iSpeed);
            setX(posX);
            setY(0);
            setDirection((float)(iDestX - posX), (float)SCREEN_HEIGHT);
            bDead = false;
    }
}
