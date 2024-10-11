package model.characters;

import java.util.ArrayList;

import model.GameObject;

public class Bats extends GameObject{


    protected int maxBats;
    protected double batSpawnRate;
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Constructs a character with a static name of "Bat", at coordinates (x,y).
    //          Sets a maxBats of 5, and a batSpawnRate every 5 seconds
    public Bats(int x, int y){
        super("Bat", x, y);
    }


}
