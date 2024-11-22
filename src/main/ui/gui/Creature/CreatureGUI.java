package ui.gui.Creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.characters.Creature;
import model.rooms.Cave;
import ui.Constants;

public class CreatureGUI extends JPanel implements KeyListener, ActionListener {

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
        this.addKeyListener(this);
        this.setOpaque(false);
        this.setLayout(null);

        creatureLeftIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureLeftResized.png");
        creatureFrontIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureFrontResized.png");
        creatureRightIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureRightResized.png");
        creatureBackIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureBackResized.png");

        // Default orientation (initial) 
        currentCreatureOrientation = creatureFrontIcon.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override   
    // Effects: Invoked when a key is typed. Uses KeyChar, char output
    public void keyTyped(KeyEvent e) {

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
    // Effects: Called whenever a button is released.
    public void keyReleased(KeyEvent e) {
        // System.out.println("You released key char: " + e.getKeyChar());
        // System.out.println("You released key code: " + e.getKeyCode());
    }

    @Override
    // Effects: Draws the creature
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(currentCreatureOrientation, creature.getPosX(), creature.getPosY(), null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
}
