package ui;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GameState;
import model.characters.Creature;
import model.rooms.Cave;
import ui.gui.Creature.CreatureGUI;
import ui.gui.Bats.BatsGUI;


public class Main {
    public static void main(String[] args) throws Exception {
       // UI TESTING AREA // 
        MyFrame myFrame = new MyFrame();
        BatsGUI testBat = new BatsGUI();

        myFrame.add(testBat);
        myFrame.repaint();

   

      
        // UI TESTING AREA // 

        Scanner scanner = new Scanner(System.in);

        try {
            GameState gs = new GameState();
            gs.startGame();
        } catch (FileNotFoundException f) {
            System.err.println("Error while starting game: file not found");
        } finally {
            scanner.close();
        }
        

    }
}
