package model.characters;


import model.GameObject;

public class Bats extends GameObject {


    protected int maxBats;
    protected double batSpawnRate;
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Constructs a character with a static name of "Bat", at coordinates (x,y).
    //          
    public Bats(int x, int y) {
        super("Bat", x, y);
    }


}
