package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements ActionListener, KeyListener {
    
    JButton testButton;

    MyFrame() {

        testButton = new JButton();

        // Adjusting properties of Button
        testButton.setFocusable(false);
        testButton.setBounds(200, 200, 350, 350);
        testButton.addActionListener(e -> {
            System.out.println("hi aurora");
            testButton.setEnabled(false);
        });

        // Adding Icon to Button
        ImageIcon testButtonIcon = new ImageIcon("src\\main\\ui\\gui\\Icon\\Cropped New Logo.png");
        testButton.setIcon(testButtonIcon);
        
        // Text on Button
        testButton.setText("TestButton");
        testButton.setFont(new Font("Times New Roman", Font.BOLD, 25));

        // Changing orientation of the text
        testButton.setHorizontalTextPosition(JButton.CENTER);
        testButton.setVerticalTextPosition(JButton.BOTTOM);
        testButton.setIconTextGap(10);
        
        this.add(testButton);

        // Frame Properties
        this.setTitle("Frankenstein Adaptation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1200,720);
        this.setVisible(true);


        // Application Icon
        ImageIcon logoImage = new ImageIcon("src\\main\\ui\\gui\\Icon\\Final Cropped Resized Logo.png");
        this.setIconImage(logoImage.getImage());
        this.getContentPane().setBackground(Color.black);

        this.addKeyListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override   
    // Effects: Invoked when a key is typed. Uses KeyChar, char output
    public void keyTyped(KeyEvent e) {

    }

    @Override
    // Effects: Invoked when a physical key is pressed down. Uses KeyCode, int output
    public void keyPressed(KeyEvent e) {
    }

    @Override
    // Effects: Called whenever a button is released.
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key char: " + e.getKeyChar());
        System.out.println("You released key code: " + e.getKeyCode());
    }
}
