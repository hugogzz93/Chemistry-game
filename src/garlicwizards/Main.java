/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garlicwizards;

import javax.swing.JFrame;

/**
 *
 * @author hugo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.setVisible(true);
        juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
