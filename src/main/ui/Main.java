package ui;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GameState;


public class Main {
    public static void main(String[] args) throws Exception {
       // UI TESTING AREA // 
        MyFrame myFrame = new MyFrame();
        JLabel testLabel = new MyLabel();

        myFrame.add(testLabel);
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
