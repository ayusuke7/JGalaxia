package jogo;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author YU7
 */
public class Sons {

    private Clip clip;
    
    public void play(String path) {
       
        try {
            File arquivo = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao executar som\n" + ex, "AVISO", JOptionPane.OK_OPTION);
        }
    }
    
    public void pause(){
        clip.stop();
    }

}
