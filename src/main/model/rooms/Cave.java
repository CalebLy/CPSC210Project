package model.rooms;

import java.util.ArrayList;
import java.util.List;

import model.GameObject;
import model.characters.Bats;

public class Cave extends GameObject{
    private int width;
    private int height;
    private int maxBats;
    private long batSpawnRate;
    private boolean isSpawningBats;
    private List<Bats> bats;

    // Effects: Creates a Cave named Bat Cave with dimensions (width-x) x (height-y)
    public Cave(int width, int height) {
        this.width = width;
        this.height = height;
        this.maxBats = 5;
        this.batSpawnRate = 5000;
        this.isSpawningBats = false;
        this.bats = new ArrayList<>();
    }

    // Modifies: this
    // Effects: Checks position of GameObject
    //          Returns true if the new position is within the bounds. False otherwise.
    public boolean isWithinBounds(int x, int y) {
        // stub
        return true;
    }
    
    // Requires: maxBats, batSpawnRate >= 0, isSpawningBats = true.
    // Effects: Spawns up to maxBats bats, every batSpawnRate miliseconds using spawnBat method.
    public void spawnBats(int maxBats, long batSpawnRate) {
        // stub
    }

    // Requires: total bats <= maxBats
    // Modifies: this
    // Effects: Creates a bat object in the cave in a random location
    public void spawnBat() {
        // stub
    }

    
    // Requires: Only called when attack(batIndex) = true
    // Modifies: this
    // Effects: Removes a Bat after it is killed by the user. Adds the batwing to user's inventory.
    public void harvestBat(int batKilledIndex) {
        // stub
    }


    // Getter methods
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getMaxBats() {
        return this.maxBats;
    }

    public long getBatSpawnRate() {
        return this.batSpawnRate;
    }

    public List<Bats> getBats() {
        return bats;
    }

    public Bats getBat(int batIndex) {
        return bats.get(batIndex);
    }
    // Setter methods
    public void stopSpawningBats() {
        this.isSpawningBats = false;
    }



}
