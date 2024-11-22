package ui.gui.inventory.items;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.Image;

import model.characters.Creature;
import model.items.Inventory;
import model.rooms.Cave;
import ui.Constants;

public class BatwingsGUI extends JPanel implements MouseListener {
    

    private JButton itemButton;

    private ImageIcon batwingIcon;
    private Image batwingScaledImage;
    private ItemTextLabels itemTextLabelsClickCondition;
    private ItemTextLabels itemTextLabelsHoverCondition;
    public static final int ITEM_SLOT_SIDE = 85;

    private Inventory inventory;
    private Creature creature;
    private Cave cave;

    // Effects: intanstializes an inventory
    public BatwingsGUI(Creature creature, Cave cave) {
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(false);

        this.inventory = creature.getInventory();
        this.creature = creature;
        this.cave = cave;
        batwingIcon = new ImageIcon("src\\main\\ui\\inventory\\BatwingImage.png");
        Image batwingIconImage = batwingIcon.getImage();
        batwingScaledImage = batwingIconImage.getScaledInstance(ITEM_SLOT_SIDE, ITEM_SLOT_SIDE, Image.SCALE_SMOOTH);

        itemButton = new JButton();
        gameButtonInitialization(itemButton, 410, 215, 85, 85);
    }


    // MODIFIES: this.
    // EFFECTS: Initializes a JButton and sets its bounds accordingly. Adds button to this.
    public void gameButtonInitialization(JButton button, int leftX, int topY, int width, int height) {
        button.setFocusable(false);
        button.setBounds(leftX, topY, width, height);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        this.add(button);
        button.addMouseListener(this);
    }

    
    @Override
    // EFFECTS: Draws the batwing image panel.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(batwingScaledImage, 410, 215, null);
    }


    @Override
    // MODIFIES: this.
    // EFFECTS: Determines if the user can use/drop a batwing. Creates the according textbox.
    public void mouseClicked(MouseEvent e) {
        
        if (itemTextLabelsClickCondition != null) {
            this.remove(itemTextLabelsClickCondition);
        }

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (inventory.getBatwings().getAmount() > 0) {
                inventory.getBatwings().useBatwing(creature, cave);
                itemTextLabelsClickCondition = new ItemTextLabels(creature, cave, 2);
            } else {
                itemTextLabelsClickCondition = new ItemTextLabels(creature, cave, 3);
            }
        // Right Click
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (inventory.getBatwings().getAmount() > 0) {
                inventory.getBatwings().removeObject();
                itemTextLabelsClickCondition = new ItemTextLabels(creature, cave, 4);
            } else {
                itemTextLabelsClickCondition = new ItemTextLabels(creature, cave, 5);
            }
        }
        this.add(itemTextLabelsClickCondition);
        itemTextLabelsClickCondition.setVisible(true);
    }


    @Override
    // MODIFIES: this.
    // EFFECTS: Creates and displays the description of the batwing.
    public void mouseEntered(MouseEvent e) {
        itemTextLabelsHoverCondition = new ItemTextLabels(creature, cave, 1);
        this.add(itemTextLabelsHoverCondition);
        itemTextLabelsHoverCondition.setVisible(true);
    }


    @Override 
    // MODIFIES: this.
    // EFFECTS: Removes the description of the batwing.
    public void mouseExited(MouseEvent e) {
        this.remove(itemTextLabelsHoverCondition);
        repaint();
    }


    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }


}
