/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards;

import garlicwizards.model.Bullet;
import garlicwizards.model.Target;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Hugo Gonzalez Pinelo
 */
public final class Game extends JFrame implements Runnable, KeyListener, MouseListener{
    
//    constants
    public enum GAME_STATE {RUNNING, PAUSE, GAME_OVER, NOT_STARTED};

    public static final String BACKGROUND_IMG_URL = "espacio.jpg";
    public static final String GAMEOVER_IMG_URL = "gameOver.jpg";
    
    public static final String BULLET_IMG_URL1 = "gray_ball.png";
//    public static final String BULLET_IMG_URL2 = "imagen.gif";
//    public static final String BULLET_IMG_URL3 = "imagen.gif";
    
    public static final String TARGET_IMG_URL1 = "red_ball.png";
//    public static final String TARGET_IMG_URL2 = "imagen.gif";
//    public static final String TARGET_IMG_URL3 = "imagen.gif";
    
    public static final int AMMOUNT_TYPES = 1;
    
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    
    public static final int MIN_SPEED = 5;
    public static final int MAX_SPEED = 20;
    
    private Image    imgApplet;   // Imagen a proyectar en Applet   
    private Graphics graphApplet;  // Objeto grafico de la Imagen
    
    
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
         arrTargets = new LinkedList();
         arrBullets = new LinkedList();
         arrBulletImages = new LinkedList();
         arrTargetImages = new LinkedList();
         
         iTargets = 15;
         iHp = 100;
         iSelectedType = 0;
         currentGameState = GAME_STATE.NOT_STARTED;
         setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                  
//       image setup
         URL urlBulletImg1 = this.getClass().getResource(BULLET_IMG_URL1);
//         URL urlBulletImg2 = this.getClass().getResource(BULLET_IMG_URL2);
//         URL urlBulletImg3 = this.getClass().getResource(BULLET_IMG_URL3);
//           Image imgBullet1 = new ImageIcon(this.getClass().getResource(BULLET_IMG_URL1)).getImage();
         Image imgBullet1 = Toolkit.getDefaultToolkit().getImage(urlBulletImg1);
//         Image imgBullet2 = Toolkit.getDefaultToolkit().getImage(urlBulletImg2);
//         Image imgBullet3 = Toolkit.getDefaultToolkit().getImage(urlBulletImg3);         
         arrBulletImages.add(new ImageIcon(imgBullet1));
//         arrBulletImages.add(imgBullet2);
//         arrBulletImages.add(imgBullet3);
         
         
         URL urlTargetImg1 = this.getClass().getResource(TARGET_IMG_URL1);         
//         URL urlTargetImg2 = this.getClass().getResource(TARGET_IMG_URL2);
//         URL urlTargetImg3 = this.getClass().getResource(TARGET_IMG_URL3);
         Image imgTarget1 = Toolkit.getDefaultToolkit().getImage(urlTargetImg1);
//         Image imgTarget2 = Toolkit.getDefaultToolkit().getImage(urlTargetImg2);
//         Image imgTarget3 = Toolkit.getDefaultToolkit().getImage(urlTargetImg3);
         arrTargetImages.add(new ImageIcon(imgTarget1));
//         arrTargetImages.add(imgTarget2);
//         arrTargetImages.add(imgTarget3);       
         
         
        // initializes bullets & targets
        for(int i  = 0; i < iTargets; i++) {
            int posX = (int) (Math.random()*SCREEN_WIDTH);
            int posY = SCREEN_HEIGHT;
            int iDestY = (int) (Math.random()*SCREEN_WIDTH);
            int iSpeed = (int) (MIN_SPEED + (int)(Math.random() * ((MAX_SPEED - MIN_SPEED) + 1)));
            int iType = (int) (Math.random() * (AMMOUNT_TYPES + 1));
            
            Target target = new Target((ImageIcon) arrTargetImages.get(0), 
                    posX, posY, iType);
            
            arrTargets.add(target);
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
            try {
                Thread.sleep (20);
            }
            catch (InterruptedException iexError)   {
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString());
            }
            while(currentGameState == GAME_STATE.PAUSE) {
                repaint();
            }
    }
    }
    
    /** 
     * update
     * 
     * Changes x and y coordinates of appropriate objects.
     * @return void
     * 
     */
    private void update() {
        for(Object bullet : arrBullets) {
            ((Bullet)bullet).move();
        }
        
        for(Object target : arrTargets) {
            ((Target)target).move();
        }
    }
    
    /** 
     * checkCollisions
     * 
     * Checks if there are collisions between bullets & targets or if targets
     * reach the ground.
     * 
     * @return void
     * 
     */
    private void checkCollisions() {
        for(Object bulletObj: arrBullets) {
            Bullet bullet = (Bullet)bulletObj;
            if(bullet.isExploding()) {
                for(Object target : arrTargets ) {
                    bullet.collidesWithObject((Target)target);
                }
            }
        }
    }
    
    /** 
     * generateBullet
     * 
     * Creates a bullet with a given direction & speed and adds it to memory.
     * (Shoots a bullet.)
     * 
     * @param iDestX <code>int</code> indicating destination's x-coordinate
     * @param iDestY <code>int</code> indicating destination's y-coordinate
     * @return void
     * 
     */ 
    private void generateBullet(int iDestX, int iDestY) {
        int iSpeed = 5;    
        int iType = iSelectedType;
        int iPosX = SCREEN_WIDTH/2;
        int iPosY = SCREEN_HEIGHT;
        
        Bullet bullet = new Bullet((ImageIcon) arrBulletImages.get(iType), iPosX, iPosY,
                iType, iDestX, iDestY);
        bullet.setY(SCREEN_HEIGHT - bullet.getHeight());
        bullet.setSpeed(iSpeed);
        
        arrBullets.add(bullet);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {    
                iSelectedType = iSelectedType == 0 ? AMMOUNT_TYPES: iSelectedType - 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {    
                iSelectedType = (iSelectedType + 1) % AMMOUNT_TYPES;
        }
        else if(e.getKeyCode() == KeyEvent.VK_P) {
            currentGameState = GAME_STATE.PAUSE;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("Coords = X: %d, Y: %d\n", e.getX(), e.getY());
        Point pos = e.getPoint();
        int x = pos.x;
        int y = pos.y;
        generateBullet(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public void paint (Graphics graGrafico){
        // Inicializan el DoubleBuffer
        if (imgApplet == null){
                imgApplet = createImage (this.getSize().width, 
                        this.getSize().height);
                graphApplet = imgApplet.getGraphics ();
        }
        
        
        //crea imagen para el background
        URL urlBackgroundImg;
        Image imgBackground;
        if(iHp>0){
             urlBackgroundImg = this.getClass().getResource(BACKGROUND_IMG_URL);
            imgBackground = Toolkit.getDefaultToolkit().getImage(urlBackgroundImg);
        }
        else{   //imagen de game over cuando se pierde el juego
            urlBackgroundImg = this.getClass().getResource(GAMEOVER_IMG_URL);
            imgBackground = Toolkit.getDefaultToolkit().getImage(urlBackgroundImg);
        }
        //despliega la imagen
        graphApplet.drawImage(imgBackground, 0, 0, 
                getWidth(), getHeight(), this);
        
       // Actualiza el Foreground.
        graphApplet.setColor (getForeground());
        paintAux(graphApplet);

        // Dibuja la imagen actualizada
        graGrafico.drawImage (imgApplet, 0, 0, this);
        
        
    }
    
    
    public void paintAux(Graphics g) {
        //se despliegan las vidas en la esquina superior izquierda
        g.setColor(Color.RED);
        g.drawString("Vidas: "+ iHp, 20, 35);
        g.drawString("Score: " + iScore, 20, 50);
        if(currentGameState == GAME_STATE.PAUSE) {
            g.drawString("PAUSA", getWidth()/2, 50);
        }
                
        //Dibuja los caminadores en la posicion actualizada
        for (Object iterTarget : arrTargets) {
            Target target = (Target)iterTarget;
            g.drawImage(target.getImage(), target.getX(),
                    target.getY(), this);
        }
        
        //Dibuja lso corredores en la posicion actualizada
        for (Object iterBullet : arrBullets) {
            Bullet bullet = (Bullet)iterBullet;
            g.drawImage(bullet.getImage(), bullet.getX(),
                    bullet.getY(), this);
        }

    }
    
}
