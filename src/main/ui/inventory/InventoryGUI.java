package ui.inventory;

import java.util.Scanner;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.Image;

import model.characters.Creature;
import model.items.Batwings;
import model.rooms.Cave;
import ui.Constants;
import ui.inventory.items.BatwingsGUI;

public class InventoryGUI extends JPanel {
    
    private ImageIcon inventoryIcon;
    private BatwingsGUI batwingsGUI;
    


    public static final int ITEM_SLOT_SIDE_LENGTH = 85;
    
    // Effects: intanstializes an inventory
    public InventoryGUI() {
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.setLayout(null);
        this.setOpaque(true);
        this.setVisible(false);
        inventoryIcon = new ImageIcon("src\\main\\ui\\inventory\\InventoryImage.png");
    }


    // MODIFIES: this.
    // EFFECTS: Initializes a JButton and sets its bounds accordingly. Adds button to this.
    public void gameButtonInitialization(JButton button, int leftX, int topY, int width, int height) {
        button.setFocusable(false);
        button.setBounds(leftX, topY, width, height);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        this.add(button);
    }

    
    @Override
    // Effects: Draws the escapeMenu panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(inventoryIcon.getImage(), 0, 0, null);
    }

}
