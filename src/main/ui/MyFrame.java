package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import model.GameState;
import model.characters.Creature;
import model.rooms.Cave;
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
    
    private JButton testButton;
    private int boundaryX = 1366;
    private int boundaryY = 768;



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

        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Adds startUpScreen onto the frame.
    public void startUpScreenSetUp(StartUpScreen startUpScreen) {
        this.add(startUpScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    public void creatureSetUp() {
        getContentPane().removeAll();
        CreatureGUI creatureGUI = new CreatureGUI(new Creature(200,200), new Cave(1376,768));
        this.add(creatureGUI, BorderLayout.CENTER);
        creatureGUI.setFocusable(true);
        creatureGUI.requestFocusInWindow();
        revalidate();
        repaint();
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
