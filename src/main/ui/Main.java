package ui;


import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GameState;
import model.characters.Bats;
import model.characters.Creature;
import model.rooms.Cave;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            GameState gs = new GameState();
            gs.startGame();
        } catch (FileNotFoundException f) {
            System.err.println("Error while starting game: file not found");
        }
        

    }
}
