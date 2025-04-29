import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {
    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(String s, double ms) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            if (image == null) {
                throw new IllegalArgumentException("Image not found: " + s);
            }
            moveScale = ms;
        } catch (Exception e) {
            System.err.println("Error loading background image: " + s);
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        // Wrap x and y to ensure they remain within bounds
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;

        // Ensure x and y are non-negative
        if (this.x < 0) this.x += GamePanel.WIDTH;
        if (this.y < 0) this.y += GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        // Move the background by the vector
        x += dx;
        y += dy;

        // Wrap around when the position exceeds the screen boundaries
        if (x < 0) x += GamePanel.WIDTH;
        if (x > GamePanel.WIDTH) x -= GamePanel.WIDTH;
        if (y < 0) y += GamePanel.HEIGHT;
        if (y > GamePanel.HEIGHT) y -= GamePanel.HEIGHT;
    }

    public void draw(Graphics2D g) {
        // Draw the primary image
        g.drawImage(image, (int) x, (int) y, null);

        // Draw wrapping images
        if (x < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
        }
        if (x > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
        }
        if (y < 0) {
            g.drawImage(image, (int) x, (int) y + GamePanel.HEIGHT, null);
        }
        if (y > 0) {
            g.drawImage(image, (int) x, (int) y - GamePanel.HEIGHT, null);
        }

        // Corner cases (diagonal wrapping)
        if (x < 0 && y < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y + GamePanel.HEIGHT, null);
        }
        if (x > 0 && y < 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y + GamePanel.HEIGHT, null);
        }
        if (x < 0 && y > 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y - GamePanel.HEIGHT, null);
        }
        if (x > 0 && y > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y - GamePanel.HEIGHT, null);
        }
    }
}