import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static HomeScreen homeScreen;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        int boardWidth = 480;
        int boardHeight = 800;

        frame = new JFrame("Air Hopper");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize home screen and add it to the frame
        homeScreen = new HomeScreen();
        frame.add(homeScreen);
        frame.pack();
        homeScreen.requestFocus();
        frame.setVisible(true);
    }

    // Method to switch to the game panel
    public static void switchToGamePanel() {
        frame.getContentPane().removeAll(); // Remove the current content
        if (gamePanel == null) {
            gamePanel = new GamePanel(); // Create a new game panel if it doesn't exist
        }
        frame.add(gamePanel); // Add the game panel to the frame
        frame.revalidate(); // Re-validate the frame layout
        gamePanel.requestFocusInWindow(); // Make sure the game panel has focus to receive key events
        gamePanel.repaint(); // Repaint the game panel to update the display
    }

    // Method to switch to the home screen
    public static void switchToHomeScreen() {
        frame.getContentPane().removeAll(); // Remove the current content
        frame.add(homeScreen); // Add the home screen to the frame
        frame.revalidate(); // Re-validate the frame layout
        homeScreen.requestFocus(); // Make sure the home screen has focus
        homeScreen.repaint(); // Repaint the home screen to update the display
    }
}