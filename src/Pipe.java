import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pipe {
    public int x, y; 
    int width;
    public int height;
    private Image img;
    public boolean passed; // Indicates if the bird has passed this pipe

    public Pipe(String imagePath, int x, int y, int width, int height) {
        this.img = new ImageIcon(getClass().getResource(imagePath)).getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() { // Getter for x
        return x;
    }

    public int getY() { // Getter for y
        return y;
    }

    public int getWidth() { // Getter for width
        return width;
    }

    public int getHeight() { // Getter for height
        return height;
    }

    public void move(int velocityX) {
        x += velocityX; // Move the pipe
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null); // Draw the pipe
    }
}