package ui;


import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GameState;


public class Main {
    public static void main(String[] args) throws Exception {
        
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
