import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 320;  // Panel width in pixels
    public static final int HEIGHT = 240; // Panel height in pixels
    public static final int SCALE = 2;    // Scaling factor for rendering

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS; // Target time for FPS control

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    // Constructor to set up the panel
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    // This method is called when the panel is first displayed
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);  // Add KeyListener for input handling
            thread.start();
        }
    }

    // Initializes the game panel
    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create a BufferedImage to draw to
        g = (Graphics2D) image.getGraphics();  // Get Graphics2D object for drawing
        running = true;  // Set the game running flag to true

        gsm = new GameStateManager();  // Initialize GameStateManager
    }

    // The main game loop
    public void run() {
        init();  // Initialize the panel when the thread starts
        long start;
        long elapsed;
        long wait;

        while (running) {
            start = System.nanoTime();
            update();  // Update the game state
            draw();    // Draw the game state
            drawToScreen();  // Draw the image to the screen

            elapsed = System.nanoTime() - start;  // Time taken to process the loop
            wait = targetTime - elapsed / 1000000;  // Calculate time to wait to maintain FPS

            if (wait > 0) {
                try {
                    Thread.sleep(wait);  // Wait for the remainder of the frame time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Updates the game state
    private void update() {
        gsm.update();  // Update the current game state
    }

    // Draws the game state to the image
    private void draw() {
        gsm.draw(g);  // Draw the current game state onto the Graphics2D object
    }

    // Draw the buffered image to the screen
    private void drawToScreen() {
        Graphics g2 = getGraphics();  // Get the graphics object of the screen
        g2.drawImage(image, 0, 0, null);  // Draw the image to the screen
        g2.dispose();  // Dispose of the graphics object
    }

    // KeyListener methods
    @Override
    public void keyTyped(KeyEvent key) {}

    @Override
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());  // Pass key press to the current game state
    }

    @Override
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());  // Pass key release to the current game state
    }
}