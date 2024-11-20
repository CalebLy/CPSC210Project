package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

import model.GameState;
import model.characters.Creature;
import model.rooms.Cave;
import ui.gui.Bats.InTheRangeOfBatLabel;
import ui.gui.Creature.CreatureGUI;
import ui.gui.StartUpScreen.StartUpScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements ActionListener {
    
    private int boundaryX = 1366;
    private int boundaryY = 768;

    private JLayeredPane layeredPane;
    private StartUpScreen startUpScreen;
    private InTheRangeOfBatLabel inTheRangeOfBatLabel;



    public MyFrame() {

        // Frame Properties
        this.setTitle("Frankenstein Adaptation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,5 ));
        this.setResizable(false);
        this.setSize(boundaryX, boundaryY);
        this.setVisible(true);

        // Application Icon
        ImageIcon logoImage = new ImageIcon("src\\main\\ui\\gui\\Icon\\Final Cropped Resized Logo.png");
        this.setIconImage(logoImage.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1376, 768);
        this.add(layeredPane, BorderLayout.CENTER);

        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Adds startUpScreen onto the frame.
    public void startUpScreenSetUp(StartUpScreen startUpScreen) {
        this.startUpScreen = startUpScreen;
        this.add(startUpScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Removes startUpScreen from the frame.
    public void startUpScreenSetOff() {
        this.remove(startUpScreen);
    }

    // MODIFIES: this.
    // EFFECTS: Clears the startUpScreen. Adds Creature to the screen.
    public void creatureSetUp(Creature creature, Cave cave) {
        startUpScreenSetOff();
        CreatureGUI creatureGUI = new CreatureGUI(creature, cave);
        layeredPane.add(creatureGUI, Integer.valueOf(0));
        creatureGUI.setFocusable(true);
        creatureGUI.requestFocusInWindow();
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Adds the InTheRangeOfBatLabel onto layeredPane.
    public void inTheRangeOfBatLabelSetUp(InTheRangeOfBatLabel inTheRangeOfBatLabel) {
        this.inTheRangeOfBatLabel = inTheRangeOfBatLabel;
        layeredPane.add(inTheRangeOfBatLabel, Integer.valueOf(1));
        inTheRangeOfBatLabel.setVisible(true);
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Removes the InTheRangeOfBatLabel from layeredPane.
    public void inTheRangeOfBatLabelSetOff(InTheRangeOfBatLabel inTheRangeOfBatLabel) {
        layeredPane.remove(inTheRangeOfBatLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // Getter methods
    public int getBoundaryX() {
        return boundaryX;
    }

    public int getBoundaryY() {
        return boundaryY;
    }

    // Setter methods
    public void setBoundaryX(int boundaryX) {
        this.boundaryX = boundaryX;
    }

    public void setBoundaryY(int boundaryY) {
        this.boundaryY = boundaryY;
    }
}
