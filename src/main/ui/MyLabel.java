package ui;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MyLabel extends JLabel {
    MyLabel() {
        this.setText("Frankenstein");
        ImageIcon testImage = new ImageIcon("src\\main\\ui\\gui\\Icon\\Final Cropped Logo.png");
        this.setIcon(testImage);
        this.setBounds(600, 170, 400, 400);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.TOP);

    }
}
