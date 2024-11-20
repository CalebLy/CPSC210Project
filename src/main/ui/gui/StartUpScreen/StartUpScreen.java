package ui.gui.StartUpScreen;
import javax.swing.*;

import ui.GameLoop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;  

public class StartUpScreen extends JPanel implements MouseListener, ActionListener {

    private GameLoop gameLoop;
    private ImageIcon startUpScreenImage;

    public StartUpScreen(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        this.addMouseListener(this);
        this.setBounds(0, 0, 1376, 768);
        this.setFocusable(true);

        startUpScreenImage = new ImageIcon("src\\main\\ui\\gui\\StartUpScreen\\StartUpScreen Cropped Resized.png");
        repaint();

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    // Effects: Draws the creature
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(startUpScreenImage.getImage(), 0, 0, null);
    }

}