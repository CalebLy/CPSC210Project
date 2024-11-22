package ui.gui.cave;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ui.Constants;

public class CaveGUI extends JLabel {

    public CaveGUI() {
        ImageIcon caveImage = new ImageIcon("src\\main\\ui\\gui\\Cave\\Cave Background Final.png");
        this.setIcon(caveImage);
        this.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
    }

}
