package ui.gui.Creature;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.characters.Creature;
import model.rooms.Cave;
import ui.MyFrame;

public class CreatureGUI extends JPanel implements KeyListener, ActionListener {

    private Creature creature;
    private Cave cave;
    private MyFrame myFrame;
    private ImageIcon creatureLeftIcon;
    private ImageIcon creatureFrontIcon;
    private ImageIcon creatureRightIcon;
    private ImageIcon creatureBackIcon;
    private Image currentCreatureOrientation;

    public CreatureGUI(Creature creature, Cave cave, MyFrame myFrame) {
        this.creature = creature;
        this.cave = cave;
        this.myFrame = myFrame;
        this.setBounds(myFrame.getBoundaryX(), myFrame.getBoundaryY());
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));

        creatureLeftIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureLeft.png");
        creatureFrontIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureFront.png");
        creatureRightIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureRight.png");
        creatureBackIcon = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureBack.png");

        // Default orientation (initial) 
        currentCreatureOrientation = creatureFrontIcon.getImage();

        // Check if image is loaded
        if (creatureFrontIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Image not loaded: CreatureFront.png");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override   
    // Effects: Invoked when a key is typed. Uses KeyChar, char output
    public void keyTyped(KeyEvent e) {

    }

    @Override
    // Effects: Invoked when a physical key is pressed down. Uses KeyCode, int output
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // 37, 38, 39, 40 = left, up, right, down
            case 37: 
                currentCreatureOrientation = creatureLeftIcon.getImage();
                creature.move("left", 5, cave);
                break;
            case 38:
                currentCreatureOrientation = creatureBackIcon.getImage();
                creature.move("up", 5, cave);
                break;
            case 39:
                currentCreatureOrientation = creatureRightIcon.getImage();
                creature.move("right", 5, cave);
                break;
            case 40:
                currentCreatureOrientation = creatureFrontIcon.getImage();
                creature.move("down", 5, cave);
                break;
        }
        repaint();
    }

    @Override
    // Effects: Called whenever a button is released.
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key char: " + e.getKeyChar());
        System.out.println("You released key code: " + e.getKeyCode());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(currentCreatureOrientation, creature.getPosX(), creature.getPosY(), null);
    }
    
}
