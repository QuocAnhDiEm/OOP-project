import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the game window
        JFrame window = new JFrame("Dragon Tale");

       // Set up the game panel and add it to the window
       window.setContentPane(new GamePanel());


        // Set the default close operation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Disable resizing for a fixed-size game window
        window.setResizable(false);

        // Pack the window to fit the preferred size of the GamePanel
        window.pack();

        window.setLocationRelativeTo(null);

        // Make the window visible
        window.setVisible(true);
    }
}