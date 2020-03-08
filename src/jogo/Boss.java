package jogo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author YU7
 */
public class Boss extends Ator{

    private final List<Tiro_Boss> tiros;
    
    boolean flag = true;
    
    public Boss() {
    
        imagem = new ImageIcon("res/boss.png").getImage();
        
        x = 550;
        y = 500;
        
        VELOCIDADE = 2;
        ENERGIA = 100;
        
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    
        visivel = false;
        tiros = new ArrayList<>();
        
    }
    
    public void movimentar(){

       //Decrementa o EixoY quando 500 
       if(this.y <= 500 && flag){
           this.y -= VELOCIDADE;
           if(this.y <= 0){
                flag = false;
           }
       //Incrementa o EixoY quando 0
       }else if(!flag){
           this.y += VELOCIDADE;
           if(this.y >= 450){
               flag = true;
           }
       }
       
    }
    
    public void atirar(){
        tiros.add(new Tiro_Boss(this.x, this.y + 40));
    }
    
    public List<Tiro_Boss> getTiros() {
        return tiros;
    }
    
}
