package ui.gui.Bats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import model.characters.Creature;
import model.rooms.Cave;


public class MouseClickedLabels extends JLabel{

    private int condition;
    private String text;

    private Creature creature;
    private Cave cave;
    
    
    public MouseClickedLabels(Creature creature, Cave cave, int condition) {
        this.creature = creature;
        this.cave = cave;
        this.condition = condition;
        this.setBounds(creature.getPosX(), creature.getPosY(), 300, 80);
        textCondition(condition);
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setForeground(Color.CYAN);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setOpaque(false);
    }

    // @Override
    // // MODIFIES: this
    // // EFFECTS: Creates a text that pops out with its shadow effect.
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     Graphics2D g2d = (Graphics2D) g;

    //     g2d.setColor(Color.BLUE);
    //     g2d.setFont(new Font("Arial", Font.BOLD, 15));

    //     textCondition(condition);

    //     int textWidth = g2d.getFontMetrics().stringWidth(text);
    //     int textHeight = g2d.getFontMetrics().getHeight();

    //     int x = (this.getWidth() - textWidth) / 2 + 2;
    //     int y = (this.getHeight() + textHeight) / 2 - 2;
    //     g2d.drawString(text, x, y);

    // }


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
