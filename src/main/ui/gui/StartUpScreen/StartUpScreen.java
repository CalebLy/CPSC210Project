package ui.gui.StartUpScreen;
import javax.swing.*;

import model.GameState;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.io.IOException;  

public class StartUpScreen extends JPanel implements ActionListener {

    private ImageIcon startUpScreenImage;
    private JButton newGameButton;
    private JButton loadGameButton;
    private static final String JSON_STORE = "./data/gamestate.json";
    final boolean[] result = {false};

    public StartUpScreen(GameState gs) {
        this.setBounds(0, 0, 1376, 768);
        this.setFocusable(true);
        this.setLayout(null);

        startUpScreenImage = new ImageIcon("src\\main\\ui\\gui\\StartUpScreen\\StartUpScreen Cropped Resized.png");
        repaint();

        newGameButton = new JButton();
        loadGameButton = new JButton();
        gameButtonInitialization(newGameButton, 455, 270, 450, 70);
        gameButtonInitialization(loadGameButton, 460, 390, 440, 70);

        

        newGameButton.addActionListener(e -> {
            gs.loadDefaultGameState();
            result[0] = true;
        });

        loadGameButton.addActionListener(e -> {
            try {
                gs.loadGameState();
                System.out.println("\nLoaded your game from " + JSON_STORE);
                result[0] = true;
            } catch (IOException i) {
                System.out.println("\nUnable to read from file: " + JSON_STORE);
                result[0] = false;
            }
        });
    }

    // MODIFIES: this.
    // EFFECTS: Initializes a JButton and sets its bounds accordingly. Adds button to this.
    public void gameButtonInitialization(JButton button, int leftX, int topY, int width, int height) {
        button.setFocusable(false);
        button.setBounds(leftX, topY, width, height);
        button.setContentAreaFilled(false);
        button.setVisible(true);
        this.add(button);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    // Effects: Draws the startUpScreen panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(startUpScreenImage.getImage(), 0, 0, null);
    }

    // Getter methods
    public boolean getResult() {
        return result[0];
    }
}