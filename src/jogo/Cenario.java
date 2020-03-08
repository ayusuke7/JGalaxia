package jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author YU7
 */
public class Cenario extends JPanel implements ActionListener {

    public Image fundo;
    public Timer time;
    public Nave nave;
    public Boss boss;
    public Sons som;
    public Conteiner conteiner;
    
    public int tempo = 0;
    
    public boolean emJogo;
    List<Inimigo> inimigos;
    public int QTD_INI, VEL_INI, NIVEL = 1, INIMIGOS;

    public Cenario(int inimigos) {
        INIMIGOS = inimigos;
        iniciarJogo();        
    }

    public final void iniciarJogo() {

        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.addKeyListener(new mapeiaTeclado());

        fundo = new ImageIcon("res/fundo.png").getImage();

        nave = new Nave();
        boss = new Boss();
        som = new Sons();

        emJogo = true;

        iniciarInimigos();

        time = new Timer(5, this);
        time.start();
               
        
    }

    public final void iniciarInimigos() {
        
        switch (NIVEL) {
            case 1:
                QTD_INI = INIMIGOS / 2;
                VEL_INI = 3;
                break;
            case 2:
                QTD_INI = (INIMIGOS / 2) / 2;
                VEL_INI = 4;
                break;
            case 3:
                QTD_INI = (INIMIGOS - (INIMIGOS / 2)) / 2;
                VEL_INI = 5;
                break;
        }

        //Sorteia a coordenadas onde irão aparecer os inimigos
        int[][] coordenadas = new int[QTD_INI][2];
        Random rd = new Random();

        for (int x = 0; x < QTD_INI; x++) {
            for (int y = 0; y < 2; y++) {
                if (y == 0) {
                    coordenadas[x][y] = rd.nextInt((1600 - 800) + 1) + 800;
                } else {
                    coordenadas[x][y] = rd.nextInt((510 - 10) + 1) + 10;
                }
            }
        }

        //Cria um Array dinamico de inimigos
        inimigos = new ArrayList<>();

        for (int[] coordenada : coordenadas) {
            inimigos.add(new Inimigo(coordenada[0], coordenada[1], VEL_INI));
        }

    }

    public final void verificaColisoes() {

        //Verifica colisao Nave --> Inimigo
        Rectangle forma_nave = nave.getBounds();
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo aux_inimigo = inimigos.get(i);
            if (forma_nave.intersects(aux_inimigo.getBounds())) {
                nave.ENERGIA -= 10;
                aux_inimigo.setVisivel(false);
            }

        }

        //Verifica colisao Tiro_Nave --> Inimigo
        Rectangle forma_tiro;
        List<Tiro_Nave> tiros = nave.getTiros();
        for (int i = 0; i < tiros.size(); i++) {

            Tiro_Nave aux_tiro = tiros.get(i);
            forma_tiro = aux_tiro.getBounds();

            for (int j = 0; j < inimigos.size(); j++) {

                Inimigo aux_inimigo = inimigos.get(j);

                if (forma_tiro.intersects(aux_inimigo.getBounds())) {
                    aux_tiro.setVisivel(false);
                    aux_inimigo.setVisivel(false);
                }

            }

        }

        //Se o BOSS visivel
        if (boss.isVisivel()) {

            //Verifica colisao Nave --> Boss e causa dano total
            if (forma_nave.intersects(boss.getBounds())) {
                nave.ENERGIA -= nave.ENERGIA;
            }

            //Verifica colisao Tiro_Nave --> Boss e causa dano
            for (int i = 0; i < tiros.size(); i++) {

                Tiro_Nave aux_tiro = tiros.get(i);
                forma_tiro = aux_tiro.getBounds();

                if (forma_tiro.intersects(boss.getBounds())) {
                    boss.ENERGIA -= 50;
                    aux_tiro.setVisivel(false);
                }
            }

            //Verifica colisao Tiro_Boss --> Nave causa dano -20;
            List<Tiro_Boss> tiros_boss = boss.getTiros();
            for (int i = 0; i < tiros_boss.size(); i++) {

                Tiro_Boss aux_tiro = tiros_boss.get(i);
                forma_tiro = aux_tiro.getBounds();

                if (forma_tiro.intersects(nave.getBounds())) {
                    nave.ENERGIA -= 20;
                    aux_tiro.setVisivel(false);
                }

            }

        }

    }

    @Override
    public void paint(Graphics gf) {

        tempo++;
                
        Graphics2D grafic = (Graphics2D) gf;
        //Desenha o funod na janela
        grafic.drawImage(fundo, 0, 0, null);

        if (emJogo) {

            //Desenha a Nave
            grafic.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            //Desenha os tiros_da_nave
            List<Tiro_Nave> tiros = nave.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro_Nave tiro = tiros.get(i);
                grafic.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);
            }

            //Desenha os inimigos
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = inimigos.get(i);
                grafic.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            //Desenha o BOSS quando não houver mais inimigos
            if (inimigos.isEmpty()) {
                if (NIVEL > 3) {
                    //fundo = new ImageIcon("res/fundo_boss.png").getImage();
                    grafic.setColor(Color.yellow);
                    grafic.drawString("Nave Imperial : " + boss.ENERGIA, 650, 50);
                    grafic.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);
                    boss.setVisivel(true);

                    //Desenha os tiros do BOSS
                    List<Tiro_Boss> tir = boss.getTiros();
                    for (int i = 0; i < tir.size(); i++) {
                        Tiro_Boss t = tir.get(i);
                        grafic.drawImage(t.getImagem(), t.getX(), t.getY(), this);
                    }
                } else {
                    NIVEL++;
                    iniciarInimigos();
                }

            }

            //Desenha o contador de Inimigos
            grafic.setColor(Color.WHITE);
            grafic.drawString("NIVEL " + NIVEL + " - Inimigos " + inimigos.size(), 650, 30);
            grafic.drawString("Jogador  : " + nave.ENERGIA + "%", 10, 30);
        
        } else if (boss.ENERGIA <= 0) {
            ImageIcon gameOver = new ImageIcon("res/victore.png");
            grafic.drawImage(gameOver.getImage(), 0, 0, null);           
        } else {
            ImageIcon gameOver = new ImageIcon("res/game_over.png");
            grafic.drawImage(gameOver.getImage(), 0, 0, null);
        }

        gf.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (boss.ENERGIA <= 0) {
            emJogo = false;
            boss.setVisivel(false);
        } else if (nave.ENERGIA <= 0) {
            emJogo = false;
            nave.setVisivel(false);
        }

        //Movimenta a Nave
        nave.movimentar();

        //Movimenta os tiros_da_nave
        List<Tiro_Nave> tiros_nave = nave.getTiros();
        for (int i = 0; i < tiros_nave.size(); i++) {
            Tiro_Nave t = tiros_nave.get(i);
            if (t.isVisivel()) {
                t.movimentar();
            } else {
                tiros_nave.remove(i);
            }
        }

        //Movimenta os Inimigos
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo in = inimigos.get(i);
            if (in.isVisivel()) {
                in.perseguir();
            } else {
                inimigos.remove(i);
            }
        }

        //Movimenta o BOSS e movimenta os tiros_do_boss
        if (boss.isVisivel()) {
            boss.movimentar();

            if (boss.getY() % 130 == 0) {
                boss.atirar();
            }

            List<Tiro_Boss> tiro_boss = boss.getTiros();
            for (int i = 0; i < tiro_boss.size(); i++) {
                Tiro_Boss t = tiro_boss.get(i);
                if (t.isVisivel()) {
                    t.movimentar();
                } else {
                    tiro_boss.remove(i);
                }
            }

        }

        verificaColisoes();
        repaint();

    }

    //Cria uma classe para mapear a teclas no Cenario    
    public class mapeiaTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER && emJogo == false) {
                emJogo = true;
                NIVEL = 1;
                nave = new Nave();
                boss = new Boss();
                iniciarInimigos();
                boss.setVisivel(false);
            }

            if (evt.getKeyCode() == KeyEvent.VK_P) {
                if (time.isRunning()) {
                    time.stop();
                } else {
                    time.start();
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
               System.exit(0);
            }

            nave.pressionarTecla(evt);

        }

        @Override
        public void keyReleased(KeyEvent evt) {
            nave.soltarTecla(evt);
        }

    }

}
