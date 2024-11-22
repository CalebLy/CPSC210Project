package ui.gui.creature;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.characters.Creature;
import model.rooms.Cave;
import ui.Constants;

public class CreatureGUI extends JPanel {

    private Creature creature;
    private Cave cave;
    private ImageIcon creatureLeftIcon;
    private ImageIcon creatureFrontIcon;
    private ImageIcon creatureRightIcon;
    private ImageIcon creatureBackIcon;
    private Image currentCreatureOrientation;
    private int speed;

    public CreatureGUI(Creature creature, Cave cave) {
        this.creature = creature;
        this.cave = cave;
        this.speed = 10;
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setLayout(null);

        creatureLeftIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureLeftResized.png");
        creatureFrontIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureFrontResized.png");
        creatureRightIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureRightResized.png");
        creatureBackIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureBackResized.png");

        // Default orientation (initial) 
        currentCreatureOrientation = creatureFrontIcon.getImage();
    }

    // Effects: Invoked when a physical key is pressed down (Method call sent from Frame).
    //          Uses KeyCode, int output. Moves and updates the creature's position 
    public void moveCreatureGUI(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
                currentCreatureOrientation = creatureLeftIcon.getImage();
                creature.move("left", speed, cave);
                break;
            case KeyEvent.VK_UP:
                currentCreatureOrientation = creatureBackIcon.getImage();
                creature.move("down", speed, cave);
                break;
            case KeyEvent.VK_RIGHT:
                currentCreatureOrientation = creatureRightIcon.getImage();
                creature.move("right", speed, cave);
                break;
            case KeyEvent.VK_DOWN:
                currentCreatureOrientation = creatureFrontIcon.getImage();
                creature.move("up", speed, cave);
                break;
        }
        repaint();
    }

    @Override
    // Effects: Draws the creature
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(currentCreatureOrientation, creature.getPosX(), creature.getPosY(), null);
    }
    
}
