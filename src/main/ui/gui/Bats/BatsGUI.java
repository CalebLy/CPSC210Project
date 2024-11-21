package ui.gui.Bats;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.rooms.Cave;
import ui.Constants;
import ui.MyFrame;
import model.characters.Bats;
import model.characters.Creature;


public class BatsGUI extends JPanel implements MouseListener {

    private ImageIcon batIcon;
    private Image batImage;
    private Image batScaledImage;
    public static final int BAT_WIDTH = 100;
    public static final int BAT_HEIGHT = 100;

    private Creature creature;
    private Cave cave;
    private MyFrame myFrame;
    private MouseEnterExitLabels mouseEnterExitLabels;


    private MouseClickedLabels mouseClickedLabels;
    private List<Point> batPositions; // Store positions of all bats
    private List<JButton> batButtons; // Store buttons for all bats

    //private MouseListener mouseListener;


    public BatsGUI(Creature creature, Cave cave) {
        this.creature = creature;
        this.cave = cave;
        batIcon = new ImageIcon("src\\main\\ui\\gui\\Bats\\Bat.png");
        batImage = batIcon.getImage();
        batScaledImage = batImage.getScaledInstance(BAT_WIDTH, BAT_HEIGHT, Image.SCALE_SMOOTH);

        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
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
        button.addMouseListener(this);
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


    @Override
    // MODIFIES: this, Creature, Cave, Bats.
    // EFFECTS: On mouse click, either attacks a bat that the user clicks, or doesn't. mouseClickedLabel is set to visible with text
    //          set according to whether the hit succeeded, and why. 
    public void mouseClicked(MouseEvent e) {

        if (creature.canAttack(cave)) {
            creature.attack(cave);
            mouseClickedLabels = new MouseClickedLabels(creature, cave, 1);
            this.add(mouseClickedLabels);
        } else if (creature.isInRange(cave) == -1) {
            mouseClickedLabels = new MouseClickedLabels(creature, cave, 1);
            this.add(mouseClickedLabels);
        } else if (creature.getAttackCooldown() == false) {
            mouseClickedLabels = new MouseClickedLabels(creature, cave, 1);
            this.add(mouseClickedLabels);
        }

        new Thread(() -> {
            try {
                mouseClickedLabels.setVisible(true);
                Thread.sleep(2000);
                mouseClickedLabels.setVisible(false);
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
        }).start();
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    // MODIFIES: this.mouseEnterExitLabels.
    // EFFECTS: If a bat is in range when a mouse enters a bat, mouseEnterExitLabel's visibility is set to true.
    public void mouseEntered(MouseEvent e) {
        if (creature.isInRange(cave) != -1) {
            mouseEnterExitLabels = new MouseEnterExitLabels(creature, cave);
            this.add(mouseEnterExitLabels);
            mouseEnterExitLabels.setVisible(true);
        }
    }


    @Override
    // MODIFIES: this.mouseEnterExitLabels.
    // EFFECTS: Removes the mouseEnterExitLabel's visibility, if it was visible, when the mouse exits a bat.
    public void mouseExited(MouseEvent e) {
        if (mouseEnterExitLabels.isVisible()) {
            mouseEnterExitLabels.setVisible(false);
        }
    }

}
