package ui.gui.Bats;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.rooms.Cave;
import ui.Constants;
import ui.MyFrame;
import model.characters.Bats;
import model.characters.Creature;


public class BatsGUI extends JPanel implements MouseListener, MouseMotionListener {

    private ImageIcon batIcon;
    private Image batImage;
    private Image batScaledImage;
    public static final int BAT_WIDTH = 100;
    public static final int BAT_HEIGHT = 100;
    private long lastClickTime = 0; 
    private static final long CLICK_INTERVAL = 2050; 


    private Creature creature;
    private Cave cave;
    private MyFrame myFrame;
    private MouseEnterExitLabels mouseEnterExitLabels;
    private MouseClickedLabels mouseClickedLabels;
    private List<Point> batPositions; 
    private List<JButton> batButtons;

    private Object sourceObject;




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
        Timer timer = new Timer(300, e -> batSpawnGUI(cave));
        timer.start();
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
        batButtons.add(button);
        button.addMouseListener(this);
        button.addMouseMotionListener(this);
    }


    // EFFECTS: Creates a button and stores positions and button for every spawned bat.
    public void batSpawnGUI(Cave cave) {
        this.cave = cave;
        for (JButton button: batButtons) {
            if (sourceObject != null && sourceObject.equals(button)) {
            } else {
                this.remove(button);
            }
        }

        batPositions.clear();
        for (Bats bat : cave.getBats()) {
            int x = bat.getPosX();
            int y = bat.getPosY();
            batPositions.add(new Point(x, y)); 
            JButton button = new JButton();
            batButtonInitialization(button, x, y, BAT_WIDTH, BAT_HEIGHT);
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
    //          set according to whether the hit succeeded, and why. Prevents spamming left click.
    public void mouseClicked(MouseEvent e) {

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime >= CLICK_INTERVAL) {
            lastClickTime = currentTime;

            if (creature.canAttack(cave)) {
                creature.attack(cave);
                batSpawnGUI(cave);
                mouseClickedLabels = new MouseClickedLabels(creature, cave, 1);
                this.add(mouseClickedLabels);
            } else if (creature.isInRange(cave) == -1) {
                mouseClickedLabels = new MouseClickedLabels(creature, cave, 2);
                this.add(mouseClickedLabels);
            } else if (creature.getAttackCooldown() == false) {
                mouseClickedLabels = new MouseClickedLabels(creature, cave, 3);
                this.add(mouseClickedLabels);
            }

            new Thread(() -> {
                try {
                    mouseClickedLabels.setVisible(true);
                    Thread.sleep(2000);
                    SwingUtilities.invokeLater(() -> {
                        mouseClickedLabels.setVisible(false);
                        this.remove(mouseClickedLabels);  
                    });
                } catch (InterruptedException i) {
                    i.printStackTrace();
                }
            }).start();

        }
    }



    @Override
    // MODIFIES: this.mouseEnterExitLabels.
    // EFFECTS: If a bat is in range when a mouse enters a bat, mouseEnterExitLabel's visibility is set to true.
    public void mouseEntered(MouseEvent e) {
        mouseEnterExitLabels = new MouseEnterExitLabels(creature, cave);
        if (creature.isInRange(cave) != -1) {
            sourceObject = e.getSource();
            this.add(mouseEnterExitLabels);
            mouseEnterExitLabels.setVisible(true);
        }

    }


    @Override
    // MODIFIES: this.mouseEnterExitLabels.
    // EFFECTS: Removes the mouseEnterExitLabel's visibility, if it was visible, when the mouse exits a bat.
    public void mouseExited(MouseEvent e) {
        if (mouseEnterExitLabels.isVisible()) {
            this.remove(mouseEnterExitLabels);
            repaint();
            System.out.println("mouseExited");
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (creature.isInRange(cave) == -1) {
            if (mouseEnterExitLabels != null && mouseEnterExitLabels.isVisible()) {
                mouseEnterExitLabels.setVisible(false);
            }
        } else {
            if (mouseEnterExitLabels != null && !mouseEnterExitLabels.isVisible()) {
                mouseEnterExitLabels.setVisible(true);
            }
        }
        repaint();
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }




}
