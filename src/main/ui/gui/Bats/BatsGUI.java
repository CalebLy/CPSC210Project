package ui.gui.Bats;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.rooms.Cave;
import model.characters.Bats;

public class BatsGUI extends JLabel {
    public BatsGUI() {
        this.setText("Bat");
        ImageIcon testImage = new ImageIcon("src\\main\\ui\\gui\\Bats\\Bat.png");
        Image batImage = testImage.getImage().getScaledInstance(155, 155, Image.SCALE_SMOOTH);
        testImage = new ImageIcon(batImage);
        this.setIcon(testImage);
        this.setBounds(600, 0, 200, 200);
        
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.TOP);
        this.setBackground(Color.BLACK);
    }
}
