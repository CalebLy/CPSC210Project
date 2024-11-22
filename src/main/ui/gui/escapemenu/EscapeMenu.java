package ui.gui.escapemenu;

import javax.swing.*;

import model.GameState;
import ui.Constants;
import ui.GameLoop;
import ui.gui.MyFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.io.FileNotFoundException;



public class EscapeMenu extends JPanel implements ActionListener {
    
    private ImageIcon escapeMenuImage;
    private JButton saveGameButton;
    private JButton endGameButton;

    private static final String JSON_STORE = "./data/gamestate.json";

    public EscapeMenu(GameState gs, GameLoop gameLoop, MyFrame myFrame) {
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.setLayout(null);
        this.setOpaque(true);
        this.setVisible(false);

        escapeMenuImage = new ImageIcon("src\\main\\ui\\gui\\EscapeMenu\\EscapeMenu Image.png");

        saveGameButton = new JButton();
        endGameButton = new JButton();
        gameButtonInitialization(saveGameButton, 380, 263, 580, 105);
        gameButtonInitialization(endGameButton, 380, 380, 580, 105);

        saveGameButton.addActionListener(e -> {
            try {
                gs.saveGame(gs.getCreature(), gs.getCave());
            } catch (FileNotFoundException f) {
                System.err.println("Unable to write to file: " + JSON_STORE);
            }
        });

        endGameButton.addActionListener(e -> {
            gs.endGame(gs.getCreature(), gs.getCave());
            myFrame.dispose();
            System.exit(0);
        });

    }

    // MODIFIES: this.
    // EFFECTS: Initializes a JButton and sets its bounds accordingly. Adds button to this.
    public void gameButtonInitialization(JButton button, int leftX, int topY, int width, int height) {
        button.setFocusable(false);
        button.setBounds(leftX, topY, width, height);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    // Effects: Draws the escapeMenu panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(escapeMenuImage.getImage(), 0, 0, null);
    }


}       
