import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Bird bird;
    private PipeManager pipeManager;
    private ScoreManager scoreManager;
    private Timer gameLoop, placePipesTimer;
    private Image backgroundImg;
    private boolean gameOver = false;
    private ScorePanel scorePanel;
    private Clip dieSoundClip;
    private Clip hitObstacleSoundClip;

    public GamePanel() {
        setPreferredSize(new Dimension(480, 800));
        setFocusable(true);
        addKeyListener(this);

        // Initialize bird, pipeManager, and scoreManager
        bird = new Bird("./assets/bird.png");
        pipeManager = new PipeManager();
        scoreManager = new ScoreManager();

        // Load background image
        backgroundImg = new ImageIcon(getClass().getResource("./assets/background.png")).getImage();

        // Timer to place pipes periodically
        placePipesTimer = new Timer(1500, e -> pipeManager.createPipes("./assets/toppipe.png", "./assets/bottompipe.png"));
        placePipesTimer.start();

        // Main game loop timer
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Initialize the score panel
        scorePanel = new ScorePanel();
        add(scorePanel);

        // Load sounds
        loadSounds();
    }

    private void loadSounds() {
        try {
            // Load die sound
            File dieSoundFile = new File("src/sounds/die.wav");
            AudioInputStream dieAudioInputStream = AudioSystem.getAudioInputStream(dieSoundFile);
            dieSoundClip = AudioSystem.getClip();
            dieSoundClip.open(dieAudioInputStream);
    
            // Load hit obstacle sound
            File hitObstacleSoundFile = new File("src/sounds/hitobstacle.wav");
            AudioInputStream hitObstacleAudioInputStream = AudioSystem.getAudioInputStream(hitObstacleSoundFile);
            hitObstacleSoundClip = AudioSystem.getClip();
            hitObstacleSoundClip.open(hitObstacleAudioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }     

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.move();
            pipeManager.movePipes(-4);
            scoreManager.updateScore(bird, pipeManager.pipes);
            checkGameOver();

            // Update the ScorePanel with the latest score
            scorePanel.setScore((int) scoreManager.getScore());
        }
        repaint();
    }

    private void checkGameOver() {
        if (bird.y > 800 || pipeManager.checkCollision(bird)) {
            gameOver = true;
            placePipesTimer.stop();
            gameLoop.stop();
            if (bird.y > 800) {
                playDieSound(); // Play the die sound if the bird falls
            } else {
                playHitObstacleSound(); // Play the hit obstacle sound if the bird hits a pipe
            }
        }
    }    

    private void playDieSound() {
        if (dieSoundClip != null) {
            dieSoundClip.setFramePosition(0); // Rewind the sound to the beginning
            dieSoundClip.start(); // Play the sound
        }
    }

    private void playHitObstacleSound() {
        if (hitObstacleSoundClip != null) {
            hitObstacleSoundClip.setFramePosition(0); // Rewind the sound to the beginning
            hitObstacleSoundClip.start(); // Play the sound
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background
        g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), this);

        // Draw bird
        bird.draw(g);

        // Draw pipes
        pipeManager.drawPipes(g);

        // If game is over, display "Game Over" image
        if (gameOver) {
            ImageIcon gameOverIcon = new ImageIcon(getClass().getResource("/assets/gameover.png"));
            g.drawImage(gameOverIcon.getImage(), getWidth() / 2 - gameOverIcon.getIconWidth() / 2,
                    getHeight() / 2 - gameOverIcon.getIconHeight() / 2, this);

            // Draw "Press any key" image
            ImageIcon pressAnyKeyIcon = new ImageIcon(getClass().getResource("/assets/pressanykey.png"));
            int pressAnyKeyWidth = pressAnyKeyIcon.getIconWidth();
            int pressAnyKeyHeight = pressAnyKeyIcon.getIconHeight();
            int scaledWidth = pressAnyKeyWidth / 2; // Adjust the width
            int scaledHeight = pressAnyKeyHeight / 2; // Adjust the height
            g.drawImage(pressAnyKeyIcon.getImage(), getWidth() / 2 - scaledWidth / 2,
                    getHeight() / 2 + gameOverIcon.getIconHeight() / 2 + 20, scaledWidth, scaledHeight, this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap(); // Flap the bird
            if (gameOver) {
                Main.switchToHomeScreen(); // Go back to home screen when space is pressed after game over
            }
        } else if (gameOver) {
            Main.switchToHomeScreen(); // Go back to home screen when any key (except space) is pressed after game over
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}