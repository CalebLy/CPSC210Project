package ui.gui.inventory.items;

import model.characters.Creature;
import model.rooms.Cave;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;



public class ItemTextLabels extends JLabel {

    private String text;

    private int leftX;
    private int topY;
    private int width;
    private int height;
    private int textSize;


    
    // EFFECTS: Creates the text boxes when the user's mouse hovers over an item
    public ItemTextLabels(Creature creature, Cave cave, int condition) {
           
        textCondition(condition, creature, cave);

        this.setBounds(leftX, topY, width, height);
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, textSize));
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.LEFT);
        this.setVerticalAlignment(JLabel.BOTTOM);
        this.setOpaque(false);
    }


    @SuppressWarnings("methodlength")
    // MODIFIES: this.text
    // EFFECTS: Changes this.text based on which item is being hovered
    public void textCondition(int condition, Creature creature, Cave cave) {
        switch (condition) {
            // Batwing Hover
            case 1:
                this.text = ("<html>The witch indicates that she's unsure how many bat wings she may need."
                           + "<br>She recommends to get 10 or more.<br> Use a batwing for a special effect!"
                           + "<br>Batwings: " + creature.getInventory().getBatwings().getAmount() + "</html>");
                setBoundsDescriptionBox();
                break;
            // Batwing Use. Case: Success
            case 2:
                int batwingAmount = creature.getInventory().getBatwings().getAmount();
                this.text = ("<html>A Batwing has been used! Batwings left: " + batwingAmount 
                           + "<br>AttackCooldownTime is now: " + creature.getAttackCooldownTime() + " miliseconds"
                           + "<br>MaxBats is now: " + cave.getMaxBats()
                           + "<br>BatSpawnRate is now: " + cave.getBatSpawnRate() + " miliseconds.</html>");
                setBoundsPopUpBox();
                break;
            // Batwing Use. Case: Failure
            case 3: 
                this.text = ("<html>Oh no! You do not have any Batwings available to use!</html>");
                setBoundsPopUpBox();
                break;
            // Batwing Drop. Case: Success
            case 4:
                this.text = ("<html>A Batwing has been dropped! <br>Batwings left: " 
                            + creature.getInventory().getBatwings().getAmount() + "</html>");
                setBoundsPopUpBox();
                break;
            case 5:
                this.text = ("<html>Oh no! You do not have any Batwings available to drop!</html>");
                setBoundsPopUpBox();
                break;
        }
    }   

    // Setter methods.
    private void setBoundsDescriptionBox() {
        leftX = 387;
        topY = 450;
        width = 598;
        height = 162;
        textSize = 25;
    }

    private void setBoundsPopUpBox() {
        leftX = 654;
        topY = 62;
        width = 390;
        height = 120;
        textSize = 17;
    }
    
}
