package ui.inventory.items;

import model.characters.Creature;
import model.rooms.Cave;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;



public class ItemTextLabels extends JLabel {

    private int condition;
    private String text;

    private Creature creature;
    private Cave cave;

    private int positionX;
    private int positionY;
    private int width;
    private int height;


    
    // EFFECTS: Creates the text boxes when the user's mouse hovers over an item
    public ItemTextLabels(Creature creature, Cave cave, int condition) {
        this.creature = creature;
        this.cave = cave;
        this.condition = condition;
        textCondition(condition);

        this.setBounds(positionX, positionY, width, height);
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setOpaque(false);
    }



    // MODIFIES: this.text
    // EFFECTS: Changes this.text based on which item is being hovered
    public void textCondition(int condition) {
        switch (condition) {
            // Batwing Hover
            case 1:
                this.text = ("<html>The witch indicates that she's unsure how many bat wings she may need."
                           + "<br>She recommends to get 10 or more.<br> Use a batwing for a special effect!"
                           + " Batwings: " + creature.getInventory().getBatwings().getAmount() + "</html>");
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
                this.text = ("<html>A Batwing has been dropped! Batwings left: " 
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
        positionX = 387;
        positionY = 455;
        width = 598;
        height = 162;
    }

    private void setBoundsPopUpBox() {
        positionX = 655;
        positionY = 60;
        width = 390;
        height = 120;
    }
    
}
