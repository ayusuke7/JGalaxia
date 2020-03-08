package jogo;

import javax.swing.ImageIcon;

/**
 *
 * @author YU7
 */
public class Tiro_Boss extends Ator {
    
    public Tiro_Boss(int posX, int posY){
        
        imagem = new ImageIcon("res/tiro_boss.png").getImage();
        
        x = posX;
        y = posY;
        
        VELOCIDADE = 8;
        
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
        
        visivel = true;
        
    }

    public void movimentar(){
        
        x -= VELOCIDADE;
        
        if(x < 0){
            setVisivel(false);
        }
        
    }
    
}
