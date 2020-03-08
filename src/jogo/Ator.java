package jogo;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author YU7
 */
public abstract class Ator {
 
    int ENERGIA;
    int VELOCIDADE;
    
    Image imagem;
    int x, y, altura, largura;
    boolean visivel;
    
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getAltura(), getLargura());
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
        
}
