package ui.gui.bats;

import javax.swing.*;

import model.characters.Bats;
import model.characters.Creature;
import model.rooms.Cave;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class MouseBatEnterExitLabels extends JLabel {


    private Creature creature;
    private Cave cave;
    private Point currentBatCoords;

    // EFFECTS: Creates the JLabel that appears when the creature is close to a bat.
    public MouseBatEnterExitLabels(Creature creature, Cave cave) {
        this.creature = creature;
        this.cave = cave;
        currentBatCoords = new Point(0,0);
        batInRangeCoordinateGetter();
        this.setBounds(currentBatCoords.x, currentBatCoords.y + 15, 500, 40);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setOpaque(false);
       
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Creates a text that pops out with its shadow effect.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        
        String text = "YOU ARE IN THE RANGE OF THIS BAT!";
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getHeight();

        int x = (this.getWidth() - textWidth) / 2 + 2;
        int y = (this.getHeight() + textHeight) / 2 - 2;
        g2d.drawString(text, x, y);

    }



    // MODIFIES: this.currentBatCoords
    // EFFECTS: Determines the coordinate of a bat that is in range. 
    public void batInRangeCoordinateGetter() {
        int batIndex = creature.isInRange(cave);
        if (batIndex != (-1)) {
            Bats currentBat = cave.getBat(batIndex);
            currentBatCoords.setLocation(currentBat.getPosX(), currentBat.getPosY());
        }
    }

}
