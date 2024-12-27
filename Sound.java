import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[11];

    public Sound(){
        try {
            soundURL[0] = new URL("file:./src/sounds/battle.wav");
            soundURL[1] = new URL("file:./src/sounds/Playboi Carti - Kid Cudi ft. Lil Uzi Vert, Young Nudy, A$AP Rocky (Official Audio).wav"); //???????? WHY CAN I PLAY THIS BUT NOT MY ACTUAL MUSIC
            soundURL[2] = new URL("file:./src/sounds/skeletonHit.wav");
            soundURL[3] = new URL("file:./src/sounds/death.wav");
            soundURL[4] = new URL("file:./src/sounds/playerHurt.wav");
            soundURL[5] = new URL("file:./src/sounds/swordSlash.wav");
            soundURL[6] = new URL("file:./src/sounds/LMFAO.wav");
            soundURL[7] = new URL("file:./src/sounds/spin.wav");
            soundURL[8] = new URL("file:./src/sounds/victory.wav");
            soundURL[9] = new URL("file:./src/sounds/america.wav");
            soundURL[10] = new URL("file:./src/sounds/finalWave.wav");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setFile(int i)
    {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
