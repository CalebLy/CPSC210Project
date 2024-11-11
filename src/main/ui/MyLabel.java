package ui;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MyLabel extends JLabel {
    MyLabel() {
        this.setText("Frankenstein");
        ImageIcon testImage = new ImageIcon("src\\main\\ui\\gui\\Creature\\CreatureFront.png");
        this.setIcon(testImage);
        this.setBounds(600, 0, 900, 700);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.TOP);

    }
}
