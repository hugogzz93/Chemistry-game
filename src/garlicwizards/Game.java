/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author hugo
 */
public final class Game extends JFrame implements Runnable, KeyListener, MouseListener{
    
//    constants
    public enum GAME_STATE {RUNNING, PAUSE, GAME_OVER, NOT_STARTED};
    
    public static final String BULLET_IMG_URL1 = "imagen.gif";
    public static final String BULLET_IMG_URL2 = "imagen.gif";
    public static final String BULLET_IMG_URL3 = "imagen.gif";
    
    public static final String TARGET_IMG_URL1 = "imagen.gif";
    public static final String TARGET_IMG_URL2 = "imagen.gif";
    public static final String TARGET_IMG_URL3 = "imagen.gif";
    
    public static final int AMMOUNT_TYPES = 3;
    
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    
    public static final int MIN_SPEED = 5;
    public static final int MAX_SPEED = 20;
    
    
    private int iScore;
    private int iHp;
    private int iSelectedType;
    private GAME_STATE currentGameState;
    
    private int iTargets;
    private LinkedList arrTargets;
    private LinkedList arrBullets;
    private LinkedList arrBulletImages;
    private LinkedList arrTargetImages;
    
    
    public Game() {
        init();
        start();
    }
    
    /** 
     * init
     * 
     * Sets initial values to all objects & variables in game.
     * 
     * @return  void
     * 
     */
    
     public void init() {
         iTargets = 15;
         iSelectedType = 0;
         currentGameState = GAME_STATE.NOT_STARTED;
         setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
         
//       image setup
         URL urlBulletImg1 = this.getClass().getResource(BULLET_IMG_URL1);
         URL urlBulletImg2 = this.getClass().getResource(BULLET_IMG_URL2);
         URL urlBulletImg3 = this.getClass().getResource(BULLET_IMG_URL3);
         Image imgBullet1 = Toolkit.getDefaultToolkit().getImage(urlBulletImg1);
         Image imgBullet2 = Toolkit.getDefaultToolkit().getImage(urlBulletImg2);
         Image imgBullet3 = Toolkit.getDefaultToolkit().getImage(urlBulletImg3);         
         arrBulletImages.add(imgBullet1);
         arrBulletImages.add(imgBullet2);
         arrBulletImages.add(imgBullet3);
         
         
         URL urlTargetImg1 = this.getClass().getResource(TARGET_IMG_URL1);
         URL urlTargetImg2 = this.getClass().getResource(TARGET_IMG_URL2);
         URL urlTargetImg3 = this.getClass().getResource(TARGET_IMG_URL3);
         Image imgTarget1 = Toolkit.getDefaultToolkit().getImage(urlTargetImg1);
         Image imgTarget2 = Toolkit.getDefaultToolkit().getImage(urlTargetImg2);
         Image imgTarget3 = Toolkit.getDefaultToolkit().getImage(urlTargetImg3);
         arrTargetImages.add(imgTarget1);
         arrTargetImages.add(imgTarget2);
         arrTargetImages.add(imgTarget3);       
         
         
        // initializes bullets & targets
        for(int i  = 0; i < iTargets; i++) {
            int posX = (int) (Math.random() *(0 + SCREEN_WIDTH) - SCREEN_WIDTH);    
            int posY = 0;
            int iSpeed = (int) (Math.random() *(MIN_SPEED + MAX_SPEED) - MAX_SPEED);    
            int iType = (int) (Math.random() *(0 + AMMOUNT_TYPES) - AMMOUNT_TYPES);
            
            Target target = new Target(iType, (Image) arrTargetImages.get(iType), 
                    posX, posY);
            Bullet bullet = new Bullet(iType, (Image) arrBulletImages.get(iType), 
                    SCREEN_HEIGHT/2, SCREEN_HEIGHT, iSpeed);
            
            targets.add(target);
            bullet.add(bullet);
        }
        
        addKeyListener(this);
        addMouseListener(this);
    }
    
    public void start() {
        Thread th = new Thread ((Runnable) this);
        th.start ();
    }

    @Override
    public void run() {
        
        while (iHp>0) {
            update();
            checkCollisions();
            repaint();           
//            checkGameState();
            try	{
                Thread.sleep (20);
            }
            catch (InterruptedException iexError)	{
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString());
            }
            while(currentGameState == GAME_STATE.PAUSE) {
                repaint();
            }
	}
    }
    
    private void update() {
        for(Bullet bullet : arrBullets) {
            bullet.move();
        }
        
        for(Target target : arrTarget) {
            target.move();
        }
    }

    private void checkCollisions() {
        for(Bullet bullet: arrBullet) {
            if(bullet.isExploding()) {
                for(Target target : arrTarget ) {
                    bullet.collidesWith(target);
                }
            }
        }
    }
    
    private void generateBullet(int iDestX, int iDestY) {
        int iSpeed = (int) (Math.random() *(MIN_SPEED + MAX_SPEED) - MAX_SPEED);    
        int iType = iSelectedType;
        int iPosX = SCREEN_WIDTH/2;
        int iPosY = 0;
        
        Bullet bullet = new Bullet((Image) arrBulletImages.get(iType), iPosX, iPosY,
                iType, iDestX, iDestY);
        
        arrBullet.add(bullet);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        generateBullet(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
