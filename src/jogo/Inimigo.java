package jogo;

import javax.swing.ImageIcon;

/**
 *
 * @author YU7
 */
public class Inimigo extends Ator {
    
    private static final int LARGURA_TELA = 800;
    private static int contador = 0;
    
    public Inimigo(int posX, int posY, int velocidade){
        
        if(contador++ % 3 == 0){
            imagem = new ImageIcon("res/inimigo2.png").getImage();
        }else{
            imagem = new ImageIcon("res/inimigo1.png").getImage();
        }
        
        x = posX;
        y = posY;
        
        VELOCIDADE = velocidade;
        
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
        
        visivel = true;
        
    }

    public void perseguir(){
        
       if(this.getX() < 0){
           this.x = LARGURA_TELA;
       }else{
           this.x -= VELOCIDADE;
       }
    }
    
}
