package jogo;

import javax.swing.ImageIcon;

/**
 *
 * @author YU7
 */
public class Tiro_Nave extends Ator {
    
    private static final int LARGURA_TELA = 800;

    public Tiro_Nave(int posX, int posY){
        
        imagem = new ImageIcon("res/tiro.png").getImage();
        
        x = posX;
        y = posY;
        
        VELOCIDADE = 5;
        
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
        visivel = true;
        
    }

    public void movimentar(){
        
        x += VELOCIDADE;
        
        if(x > LARGURA_TELA){
            setVisivel(false);
        }
        
    }
    
}
