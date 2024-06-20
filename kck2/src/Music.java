import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    public Music() {
    }

    public static void soundtrackcar() {
        File soundtrack = new File("music/Arcade-background-music-retro-style.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundtrack);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

}