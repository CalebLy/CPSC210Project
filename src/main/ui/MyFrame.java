package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import model.characters.Creature;
import model.rooms.Cave;
import ui.gui.Bats.BatsGUI;
import ui.gui.Bats.MouseEnterExitLabels;
import ui.gui.Cave.CaveGUI;
import ui.gui.Creature.CreatureGUI;
import ui.gui.StartUpScreen.StartUpScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    


    private JLayeredPane layeredPane;
    private StartUpScreen startUpScreen;
    private MouseEnterExitLabels inTheRangeOfBatLabel;
    private BatsGUI batsGUI;



    public MyFrame() {

        // Frame Properties
        this.setTitle("Frankenstein Adaptation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,5 ));
        this.setResizable(false);
        this.setSize(Constants.screenWidth, Constants.screenHeight);
        this.setVisible(true);

        // Application Icon
        ImageIcon logoImage = new ImageIcon("src\\main\\ui\\gui\\Icon\\Final Cropped Resized Logo.png");
        this.setIconImage(logoImage.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
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
        layeredPane.add(creatureGUI, Integer.valueOf(1));
        creatureGUI.setFocusable(true);
        creatureGUI.requestFocusInWindow();
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Adds the InTheRangeOfBatLabel onto layeredPane.
    public void inTheRangeOfBatLabelSetUp(MouseEnterExitLabels inTheRangeOfBatLabel) {
        this.inTheRangeOfBatLabel = inTheRangeOfBatLabel;
        layeredPane.add(inTheRangeOfBatLabel, Integer.valueOf(2));
        inTheRangeOfBatLabel.setVisible(true);
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Removes the InTheRangeOfBatLabel from layeredPane.
    public void inTheRangeOfBatLabelSetOff(MouseEnterExitLabels inTheRangeOfBatLabel) {
        layeredPane.remove(inTheRangeOfBatLabel);
    }

    // MODIFIES: this.
    // EFFECTS: Adds Bats to the screen.
    public void batsSetUp(Creature creature, Cave cave) {
        this.batsGUI = new BatsGUI(creature, cave, this, inTheRangeOfBatLabel);
        layeredPane.add(batsGUI, Integer.valueOf(2));
        batsGUI.setFocusable(true);
        batsGUI.requestFocusInWindow();
        revalidate();
        repaint();
    }

    // MODIFIES: this.
    // EFFECTS: Adds Cave background to the screen.
    public void caveSetUp() {
        CaveGUI cave = new CaveGUI();
        layeredPane.add(cave, Integer.valueOf(0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
