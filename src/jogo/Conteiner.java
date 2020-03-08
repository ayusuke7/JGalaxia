/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author YU7
 */
public class Conteiner extends JFrame {

    public Conteiner(int inimigos){        
        this.add(new Cenario(inimigos));
        this.setTitle("JGalaxia Naves");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String[] args) {

        Menu menu = new Menu(new JFrame(), true);
        menu.setVisible(true);

        if (menu.isConfirma()) {
            new Conteiner(menu.getInimigos());
        }

    }     
    
}
