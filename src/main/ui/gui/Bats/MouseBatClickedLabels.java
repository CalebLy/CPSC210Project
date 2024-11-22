package ui.gui.bats;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import model.characters.Creature;



public class MouseBatClickedLabels extends JLabel {


    private String text;

    private Creature creature;
    
    // EFFECTS: Creates the text that appears when the user clicks a bat
    public MouseBatClickedLabels(Creature creature, int condition) {
        this.creature = creature;

        this.setBounds(creature.getPosX(), creature.getPosY(), 300, 80);
        textCondition(condition);
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setForeground(Color.CYAN);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setOpaque(false);
    }


    // MODIFIES: this.text
    // EFFECTS: Changes this.text based on the condition of the creatures Attack Status
    public void textCondition(int condition) {
        switch (condition) {
            // CANHIT
            case 1:
                this.text = ("<html>The bat has succesfully been eliminated and harvested."
                          + "<br>You have obtained a Batwing!</html>");
                break;
            // NOTINRANGE
            case 2:
                this.text = ("<html>Oh no! Looks like you're not within 1 block of the bat!" 
                          + "<br>Bat elimination has failed.</html>");
                break;
            // ATTACK_ON_COOLDOWN
            case 3:
                this.text = ("\n\nOh no! Looks like you're currently fatigued!"
                           + " Please wait until your weaponCooldown is finished: " 
                           + creature.getAttackCooldownTime() + " miliseconds");
                break;
        }
    }   
}
