import java.awt.Graphics;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class ScoreManager {
    private double score = 0;
    private Clip pointSoundClip;

    public ScoreManager() {
        // Load the point sound
        loadPointSound("./sounds/point.wav");
    }

    // Method to load the point sound
    private void loadPointSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            pointSoundClip = AudioSystem.getClip();
            pointSoundClip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Update the score based on the bird's position and the pipes
    public void updateScore(Bird bird, ArrayList<Pipe> pipes) {
        for (Pipe pipe : pipes) {
            // Check if the bird has passed the pipe
            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true; // Mark the pipe as passed
                score += 0.5; // Increment the score
                playPointSound(); // Play the point sound
            }
        }
    }

    // Method to play the point sound
    private void playPointSound() {
        if (pointSoundClip != null) {
            pointSoundClip.setFramePosition(0); // Rewind the sound to the beginning
            pointSoundClip.start(); // Play the sound
        }
    }

    // Draw the current score on the screen
    public void drawScore(Graphics g, int width, int height) {
        // Set font and color for the text
        Font font = new Font("Arial", Font.BOLD, 48); // Bigger and bolder font
        g.setFont(font);
        g.setColor(Color.white);
    
        // Draw the score
        String scoreText = "" + (int) score;
        g.drawString(scoreText, 10, 50); // Adjusted position for bigger font
    }    

    // Reset the score to zero
    public void resetScore() {
        score = 0;
    }

    // Get the current score
    public double getScore() {
        return score;
    }    
}