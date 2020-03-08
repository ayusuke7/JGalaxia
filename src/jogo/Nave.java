package jogo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author YU7
 */
public class Nave extends Ator{

    private int direcaoX, direcaoY;
    private final List<Tiro_Nave> tiros;
    
    public Nave() {
        
        imagem = new ImageIcon("res/nave.png").getImage();
        x = 100;
        y = 100;

        VELOCIDADE = 5;
        ENERGIA = 100;
        
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);

        tiros = new ArrayList<>();

    }

    public void movimentar() {

        x += direcaoX;
        y += direcaoY;

        if (this.x < 2) {
            x = 2;
        }

        if (this.x > 715) {
            x = 715;
        }

        if (this.y < 2) {
            y = 2;
        }

        if (this.y > 490) {
            y = 490;
        }

    }

    public void atirar() {

        tiros.add(new Tiro_Nave(this.x + largura, this.y + altura / 2));

    }

    public void pressionarTecla(KeyEvent evt) {

        int cod = evt.getKeyCode();

        //deslocamento Horizontal EIXO X
        if (cod == KeyEvent.VK_SPACE) {
            atirar();
            //new Sons().play("res/shoot_laser.wav");         
        }

        //deslocamento Horizontal EIXO X
        if (cod == KeyEvent.VK_LEFT) {
            direcaoX = -VELOCIDADE;
        }

        if (cod == KeyEvent.VK_RIGHT) {
            direcaoX = VELOCIDADE;
        }

        //deslocamento Vertical EIXO Y
        if (cod == KeyEvent.VK_UP) {
            direcaoY = -VELOCIDADE;
        }

        if (cod == KeyEvent.VK_DOWN) {
            direcaoY = VELOCIDADE;
        }

    }

    public void soltarTecla(KeyEvent evt) {

        int cod = evt.getKeyCode();

        if (cod == KeyEvent.VK_LEFT) {
            direcaoX = 0;
        }

        if (cod == KeyEvent.VK_RIGHT) {
            direcaoX = 0;
        }

        if (cod == KeyEvent.VK_UP) {
            direcaoY = 0;
        }

        if (cod == KeyEvent.VK_DOWN) {
            direcaoY = 0;
        }

    }

    public List<Tiro_Nave> getTiros() {
        return tiros;
    }

}
