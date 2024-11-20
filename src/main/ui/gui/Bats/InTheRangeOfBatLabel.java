package ui.gui.Bats;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InTheRangeOfBatLabel extends JLabel {
    
    // EFFECTS: Creates the JLabel that appears when the creature is close to a bat.
    public InTheRangeOfBatLabel() {
        this.setBounds(0, 334, 1376, 40);
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

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        
        String text = "YOU ARE IN THE RANGE OF A BAT!";
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getHeight();

        int x = (this.getWidth() - textWidth) / 2 + 2;
        int y = (this.getHeight() + textHeight) / 2 - 2;
        g2d.drawString(text, x, y);

        g2d.setColor(Color.RED);
        g2d.drawString(text, (this.getWidth() - textWidth) / 2, (this.getHeight() + textHeight) / 2);
    }
}
