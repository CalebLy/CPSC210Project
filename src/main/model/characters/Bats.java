package model.characters;

import model.GameObject;

public class Bats extends GameObject{

    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Constructs a character with a static name of "Bat", at coordinates (x,y).
    public Bats(int x, int y){
        super("Bat", x, y);
    }


    // Requires: maxBats, batSpawnRate >= 0
    // Effects: Spawns Bat characters in random (x,y) coordinates in the room.
    //          Spawns up to maxBats bats, every batSpawnRate seconds.
    public void spawnBats(int maxBats, int batSpawnRate) {
        // stub
    }

}
