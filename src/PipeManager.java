import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class PipeManager {
    public ArrayList<Pipe> pipes; // List to store the pipes
    private Random random; // Random object for generating random pipe heights
    private static final int PIPE_WIDTH = 90; // Width of the pipes
    private static final int PIPE_HEIGHT = 680; // Height of the pipes
    private static final int BOARD_WIDTH = 480; // Width of the game board

    // Constructor to initialize the PipeManager
    public PipeManager() {
        pipes = new ArrayList<>(); // Initialize the list of pipes
        random = new Random(); // Initialize the Random object
    }

    // Method to create a pair of pipes
    public void createPipes(String topPipeImagePath, String bottomPipeImagePath) {
        int gapHeight = 200; // Gap height between the top and bottom pipes
        int minHeightTopPipe = 100; // Minimum height of the top pipe
        int maxHeightTopPipe = 450; // Maximum height of the top pipe

        // Generate a random height for the top pipe within the specified range
        int randomHeight = minHeightTopPipe + random.nextInt(maxHeightTopPipe - minHeightTopPipe);
        int topPipeY = -PIPE_HEIGHT + randomHeight; // Adjust the position of the top pipe

        // Create the top and bottom pipes with the specified positions and dimensions
        Pipe topPipe = new Pipe(topPipeImagePath, BOARD_WIDTH, topPipeY, PIPE_WIDTH, PIPE_HEIGHT);
        Pipe bottomPipe = new Pipe(bottomPipeImagePath, BOARD_WIDTH, topPipeY + PIPE_HEIGHT + gapHeight, PIPE_WIDTH, PIPE_HEIGHT);

        // Add the pipes to the list
        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    // Method to move all pipes horizontally
    public void movePipes(int velocityX) {
        // Move each pipe in the list horizontally
        pipes.forEach(pipe -> pipe.move(velocityX));
    }

    // Method to draw all pipes
    public void drawPipes(Graphics g) {
        // Draw each pipe in the list
        pipes.forEach(pipe -> pipe.draw(g));
    }

    // Method to check collision between the bird and pipes
    public boolean checkCollision(Bird bird) {
        // Iterate through each pipe in the list
        for (Pipe pipe : pipes) {
            // Get the coordinates and dimensions of the bird and pipe
            int birdX = bird.x;
            int birdY = bird.y;
            int birdWidth = bird.width;
            int birdHeight = bird.height;
            int pipeX = pipe.x;
            int pipeY = pipe.y;
            int pipeWidth = pipe.width;
            int pipeHeight = pipe.height;

            // Check for collision between the bird and pipe
            if (birdX < pipeX + pipeWidth &&
                birdX + birdWidth > pipeX &&
                birdY < pipeY + pipeHeight &&
                birdY + birdHeight > pipeY) {
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }

    // Method to reset the list of pipes
    public void reset() {
        pipes.clear(); // Clear the list of pipes
    }
}