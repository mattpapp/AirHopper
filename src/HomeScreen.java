import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeScreen extends JPanel implements KeyListener {
    private Image backgroundImage;
    private ImageIcon playIcon, birdIcon;
    private JButton playButton;

    public HomeScreen() {
        // Load images
        loadImage();

        // Create icons from images and scale them
        playIcon = new ImageIcon(playIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        birdIcon = new ImageIcon(birdIcon.getImage().getScaledInstance(68, 52, Image.SCALE_SMOOTH));

        // Create play button
        playButton = new JButton(playIcon);

        // Remove button styling
        styleButton(playButton);

        // Add action listener to the play button
        playButton.addActionListener(e -> startGame());

        // Add key listener to the panel
        addKeyListener(this);

        // Set layout and constraints
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Bird label at the top
        JLabel birdLabel = new JLabel(birdIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0); // Adjusted padding around the bird
        gbc.anchor = GridBagConstraints.CENTER;
        add(birdLabel, gbc);

        // Play button below the bird
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0); // Adjusted padding around the button
        add(playButton, gbc);

        setPreferredSize(new Dimension(480, 800));

        // Set focusable to true so that the panel can receive key events
        setFocusable(true);
    }

    private void loadImage() {
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();
        playIcon = new ImageIcon(getClass().getResource("/assets/play.png"));
        birdIcon = new ImageIcon(getClass().getResource("/assets/bird.png"));
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image covering the entire panel
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void startGame() {
        // Get the parent JFrame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll(); // Remove the home screen
        GamePanel gamePanel = new GamePanel(); // Create a new game panel
        frame.add(gamePanel); // Add the game panel to the frame
        frame.revalidate(); // Re-validate the frame layout
        gamePanel.requestFocusInWindow(); // Make sure the game panel has focus to receive key events
        frame.repaint(); // Repaint the frame to update the display
    }

    // KeyListener implementation
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            startGame(); // Start the game when the space key is pressed
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}