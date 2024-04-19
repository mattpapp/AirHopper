import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private int score;

    // Constructor to initialize the score panel
    public ScorePanel() {
        setPreferredSize(new Dimension(60, 40)); // Adjusted panel size
        setBackground(Color.black); // Set background color
        setForeground(Color.white); // Set text color
        setFont(new Font("Arial", Font.BOLD, 24)); // Set bigger and bolder font
        setBorder(BorderFactory.createLineBorder(Color.white, 2)); // Set border
        setScore(0); // Initialize score to 0
    }

    // Method to set the score and repaint the panel
    public void setScore(int score) {
        this.score = score;
        repaint(); // Repaint the panel to update the score display
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the score box
        int boxWidth = getWidth();
        int boxHeight = getHeight();
        g.setColor(Color.white);
        g.fillRect(0, 0, boxWidth, boxHeight);

        // Draw the score
        g.setColor(Color.black);
        String scoreText = String.valueOf(score);
        FontMetrics metrics = g.getFontMetrics();
        int textX = (boxWidth - metrics.stringWidth(scoreText)) / 2;
        int textY = ((boxHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(scoreText, textX, textY);
    }
}