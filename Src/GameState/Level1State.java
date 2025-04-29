import TileMap.*;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

    // Class properties
    private TileMap tileMap;      // Tile map for the level
    private Background bg;        // Background image
    private Player player;        // Player character

    // Constructor
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    // Initialize the level
    public void init() {
        // Initialize tile map
        tileMap = new TileMap(30); // Each tile is 30x30 pixels
        try {
            tileMap.loadTiles("/Tilesets/grassTileset.gif");
            tileMap.loadMap("/Maps/level1-1.map");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading tile map or tileset.");
        }
        tileMap.setPosition(0, 0);

        // Initialize background
        try {
            bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading background image.");
        }

        // Initialize player
        player = new Player(tileMap);
        player.setPosition(100, 100); // Starting position
    }

    // Update game logic
    public void update() {
        // Update player state
        player.update();

        // Adjust tile map position based on player location
        tileMap.setPosition(
            GamePanel.WIDTH / 2 - player.getx(),
            GamePanel.HEIGHT / 2 - player.gety()
        );
    }

    // Render the game state
    public void draw(Graphics2D g) {
        // Draw background
        if (bg != null) {
            bg.draw(g);
        }

        // Draw tile map
        if (tileMap != null) {
            tileMap.draw(g);
        }

        // Draw player
        if (player != null) {
            player.draw(g);
        }
    }

    // Handle key press events
    public void keyPressed(int k) {
        if (player == null) return;

        switch (k) {
            case KeyEvent.VK_LEFT -> player.setLeft(true);
            case KeyEvent.VK_RIGHT -> player.setRight(true);
            case KeyEvent.VK_UP -> player.setUp(true);
            case KeyEvent.VK_DOWN -> player.setDown(true);
            case KeyEvent.VK_W -> player.setJumping(true);
            case KeyEvent.VK_E -> player.setGliding(true);
            case KeyEvent.VK_R -> player.setScratching(); // Verify Player class method
            case KeyEvent.VK_F -> player.setFiring();     // Verify Player class method
        }
    }

    // Handle key release events
    public void keyReleased(int k) {
        if (player == null) return;

        switch (k) {
            case KeyEvent.VK_LEFT -> player.setLeft(false);
            case KeyEvent.VK_RIGHT -> player.setRight(false);
            case KeyEvent.VK_UP -> player.setUp(false);
            case KeyEvent.VK_DOWN -> player.setDown(false);
            case KeyEvent.VK_W -> player.setJumping(false);
            case KeyEvent.VK_E -> player.setGliding(false);
        }
    }
}


    




