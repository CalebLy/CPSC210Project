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

public class InventoryGUI extends JPanel {
    
    private ImageIcon inventoryIcon;

    private JButton itemButton;

    private ImageIcon batwingIcon;
    private Image batwingScaledImage;
    public static final int ITEM_SLOT_SIDE_LENGTH = 85;
    
    // Effects: intanstializes an inventory
    public InventoryGUI() {
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.setLayout(null);
        this.setOpaque(true);
        this.setVisible(false);

        inventoryIcon = new ImageIcon("src\\main\\ui\\inventory\\InventoryImage.png");
        batwingIcon = new ImageIcon("src\\main\\ui\\inventory\\BatwingImage.png");
        batwingScaledImage = batwingIcon.getImage().getScaledInstance(ITEM_SLOT_SIDE_LENGTH, ITEM_SLOT_SIDE_LENGTH, Image.SCALE_SMOOTH);

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
        g2D.drawImage(batwingIcon.getImage(), 410, 215, null);
    }



    // // Effects: Displays inventory. Gives user option to view amount of Batwings,
    // //          use Batwings, drop Batwings, or view description of Batwings.
    // @SuppressWarnings("methodlength")
    // public void displayInventory(Creature creature, Cave cave) {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("\nType 'a' to view amount of Batwings" 
    //                        + "\nType 'u' to use a Batwing" 
    //                        + "\nType 'd' to drop a Batwing" 
    //                        + "\nType 'v' to view description of Batwings" 
    //                        + "\nType 'i' to close the inventory");
    //     String userChoice = scanner.nextLine();
    //     switch (userChoice.toLowerCase()) {
    //         case "a":
    //             System.out.println("\nBatwings: " + this.getBatwings().getAmount());
    //             break;
    //         case "u":
    //             if (this.getBatwings().getAmount() > 0 && this.getBatwings().useBatwing(creature, cave)) {
    //                 this.getBatwings().useBatwing(creature, cave);
    //                 System.out.println("\nA Batwing has been used! Batwings left: " + this.getBatwings().getAmount());
    //                 System.out.println("AttackCooldownTime is now: " 
    //                                 + creature.getAttackCooldownTime() + " miliseconds" 
    //                                 + "\nMaxBats is now: " + cave.getMaxBats() 
    //                                 + "\nBatSpawnRate is now: " + cave.getBatSpawnRate() + " miliseconds"); 
    //             } else if (this.getBatwings().getAmount() == 0) {
    //                 System.out.println("\nOh no! You do not have any Batwings available to use!");
    //             } else if (!this.getBatwings().useBatwing(creature, cave)) {
    //                 System.out.println("You've used the maximum amount of batwings!");
    //             }
    //             break;
    //         case "d":
    //             if (this.getBatwings().getAmount() > 0) {
    //                 this.getBatwings().removeObject();
    //                 System.out.println("\nA Batwing has been dropped! Batwings left: " 
    //                                 + this.getBatwings().getAmount());
    //             } else {
    //                 System.out.println("\nOh no! You do not have any Batwings available to drop!");
    //             }
    //             break;
    //         case "v":
    //             System.out.println("" + this.getBatwings().getDescription());
    //             break;
    //         case "i":
    //             inventoryOpen = false;
    //             break;
    //     }
    // }


}
