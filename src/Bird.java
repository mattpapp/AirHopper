import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Bird {
    public int x; // Horizontal position
    public int y; // Vertical position
    int width; // Width of the bird
    public int height; // Height of the bird
    private Image img; // Image of the bird
    private int velocityY; // Vertical velocity
    private static final int GRAVITY = 1; // Gravity affecting the bird's motion

    // Constructor to initialize the bird with the given image path
    public Bird(String imagePath) {
        this.img = new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.width = 68; // Default width of the bird
        this.height = 56; // Default height of the bird
        this.x = 480 / 8; // Initial horizontal position
        this.y = 800 / 2; // Initial vertical position
    }

    // Method to make the bird flap
    public void flap() {
        velocityY = -14; // Set upward velocity when the bird flaps
        playFlapSound(); // Play the flap sound
    }

    private void playFlapSound() {
        try {
            File soundFile = new File("src/sounds/flap.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to move the bird
    public void move() {
        velocityY += GRAVITY; // Apply gravity to the vertical velocity
        y += velocityY; // Update the vertical position based on velocity
        // Ensure the bird cannot move above the screen
        y = Math.max(y, 0);
    }

    // Method to draw the bird on the screen
    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null); // Draw the bird
    }

    // Method to reset the bird's position and velocity
    public void reset() {
        // Reset the position of the bird to its initial position
        x = 100; // Initial horizontal position
        y = 400; // Initial vertical position
        velocityY = 0; // Reset the vertical velocity
    }
}