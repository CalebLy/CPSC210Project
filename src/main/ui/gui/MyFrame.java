package ui.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import model.characters.Creature;
import model.rooms.Cave;
import ui.Constants;
import ui.gui.bats.BatsGUI;
import ui.gui.cave.CaveGUI;
import ui.gui.creature.CreatureGUI;
import ui.gui.escapemenu.EscapeMenu;
import ui.gui.inventory.InventoryGUI;
import ui.gui.inventory.items.BatwingsGUI;
import ui.gui.startupscreen.StartUpScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements ActionListener, KeyListener {
    


    private JLayeredPane layeredPane;
    private StartUpScreen startUpScreen;
    private BatsGUI batsGUI;
    private CreatureGUI creatureGUI;

    private EscapeMenu escapeMenu;
    private boolean menuVisible;

    private InventoryGUI inventoryGUI;
    private BatwingsGUI batwingsGUI;
    private boolean inventoryVisible;




    public MyFrame() {

        // Frame Properties
        this.setTitle("Frankenstein Adaptation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,5));
        this.setResizable(false);
        this.setSize(Constants.screenWidth, Constants.screenHeight);
        this.setVisible(true);
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.addKeyListener(this);

        // Application Icon
        ImageIcon logoImage = new ImageIcon("src\\main\\ui\\gui\\Icon\\Final Cropped Resized Logo.png");
        this.setIconImage(logoImage.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.add(layeredPane, BorderLayout.CENTER);

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

    // MODIFIES: this.layeredPane.
    // EFFECTS: Clears the startUpScreen. Adds Creature to the screen.
    public void creatureSetUp(Creature creature, Cave cave) {
        startUpScreenSetOff();
        creatureGUI = new CreatureGUI(creature, cave);
        layeredPane.add(creatureGUI, Integer.valueOf(1));
        revalidate();
        repaint();
    }


    // MODIFIES: this.layeredPane.
    // EFFECTS: Adds Bats to the screen.
    public void batsSetUp(Creature creature, Cave cave) {
        this.batsGUI = new BatsGUI(creature, cave);
        layeredPane.add(batsGUI, Integer.valueOf(2));
        revalidate();
        repaint();
    }

    // MODIFIES: this.layeredPane
    // EFFECTS: Adds Cave background to the screen.
    public void caveSetUp() {
        CaveGUI cave = new CaveGUI();
        layeredPane.add(cave, Integer.valueOf(0));
    }

    // MODIFIES: this.layeredPane.
    // EFFECTS: Adds escapeMenu to the layeredPane
    public void escapeMenuSetUp(EscapeMenu escapeMenu) {
        this.escapeMenu = escapeMenu;
        layeredPane.add(escapeMenu, Integer.valueOf(3));
    }

    // MODIFIES: this.
    // EFFECTS: Toggles escapeMenu's visibility.
    private void toggleMenu() {
        menuVisible = !menuVisible; 
        escapeMenu.setVisible(menuVisible);
    }

    // MODIFIES: this.layeredPane.
    // EFFECTS: Adds inventory to the layeredPane
    public void inventorySetUp(InventoryGUI inventoryGUI, Creature creature, Cave cave) {
        this.inventoryGUI = inventoryGUI;
        this.batwingsGUI = new BatwingsGUI(creature, cave);
        layeredPane.add(inventoryGUI, Integer.valueOf(3));
        layeredPane.add(batwingsGUI, Integer.valueOf(4));
    }

    // MODIFIES: this.
    // EFFECTS: Toggles Inventory's visibility.
    public void toggleInventory() {
        inventoryVisible = !inventoryVisible;
        inventoryGUI.setVisible(inventoryVisible);
        batwingsGUI.setVisible(inventoryVisible);
    }


    // EFFECTS: Keylistener that can toggle the escapeMenu or move the creature.
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_UP:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_DOWN:
                creatureGUI.moveCreatureGUI(e);
                break;
            case KeyEvent.VK_ESCAPE:
                if (!inventoryVisible) {
                    toggleMenu();
                }
                break;
            case KeyEvent.VK_I:
                if (!menuVisible) {
                    toggleInventory();
                }
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
