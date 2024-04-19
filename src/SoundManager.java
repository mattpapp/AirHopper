import javax.sound.sampled.*;
import java.io.*;

public class SoundManager {
    public static void main(String[] args) {
        playSound("src/sounds/die.wav");
        playSound("src/sounds/hitobstacle.wav");
        playSound("src/sounds/point.wav");
        playSound("src/sounds/flap.wav");
    }

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}