package ui.gui.Bats;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.rooms.Cave;
import model.characters.Bats;


public class BatsGUI extends JPanel {

    private ImageIcon batIcon;
    private Image batImage;
    private Image batScaledImage;
    private static final int BAT_WIDTH = 100;
    private static final int BAT_HEIGHT = 100;

    private List<Point> batPositions; // Store positions of all bats
    private List<JButton> batButtons; // Store buttons for all bats


    public BatsGUI(Cave cave) {
        batIcon = new ImageIcon("src\\main\\ui\\gui\\Bats\\Bat.png");
        batImage = batIcon.getImage();
        batScaledImage = batImage.getScaledInstance(BAT_WIDTH, BAT_HEIGHT, Image.SCALE_SMOOTH);

        this.setBounds(0, 0, 1376, 768);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setLayout(null);

        batPositions = new ArrayList<>();
        batButtons = new ArrayList<>();
        batSpawnGUI(cave);
    }

    
    // MODIFIES: this
    // EFFECTS: Initializes a JButton and sets its bounds accordingly. Adds button to this.
    public void batButtonInitialization(JButton button, int leftX, int topY, int width, int height) {
        button.setFocusable(false);
        button.setBounds(leftX, topY, width, height);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false); 
        button.setVisible(true);
        this.add(button);
    }

    // EFFECTS: Creates a button and stores positions and button for every spawned bat.
    public void batSpawnGUI(Cave cave) {
        for (Bats bat : cave.getBats()) {
            int x = bat.getPosX();
            int y = bat.getPosY();
            batPositions.add(new Point(x, y)); 
            JButton button = new JButton();
            batButtonInitialization(button, x, y, BAT_WIDTH, BAT_HEIGHT);
            batButtons.add(button); 
        }
        repaint(); 
    }


    @Override
    // EFFECTS: Draws all bats at their respective positions
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for (Point pos : batPositions) {
            g2D.drawImage(batScaledImage, pos.x, pos.y, null);
        }
    }

}
