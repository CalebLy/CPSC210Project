package model.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;

import model.GameObject;
import model.characters.Bats;
import model.characters.Creature;
import model.eventlogger.Event;
import model.eventlogger.EventLog;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class Cave extends GameObject {
    private int width;
    private int height;
    private int maxBats;
    private long batSpawnRate;
    private boolean isSpawningBats;
    private List<Bats> bats;

    // Effects: Creates a Cave with dimensions (width-x) x (height-y)
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
        if (width < x || height < y || x < 0 || y < 0) {
            return false;
        } else {
            return true;
        }
    }
    
    // Requires: maxBats, batSpawnRate >= 0, isSpawningBats = true.
    // Effects: Spawns up to maxBats bats, every batSpawnRate miliseconds using spawnBat method.
    public void spawnBats(int maxBats, long batSpawnRate) {
        isSpawningBats = true;
        this.maxBats = maxBats;
        this.batSpawnRate = batSpawnRate;
        new Thread(() -> {
            while (isSpawningBats) { 

                // while (bats.size() == maxBats) {
                //     try {
                //         Thread.sleep(100); 
                //     } catch (InterruptedException e) {
                //         Thread.currentThread().interrupt(); 
                //     }
                // }

                try {
                    Thread.sleep(batSpawnRate);
                    spawnBat();
                    
                } catch (InterruptedException e) {
                    System.err.println("Bat spawn interrupted" + e.getMessage());
                }
            }
        }).start();
    }

    // Requires: total bats < maxBats
    // Modifies: this
    // Effects: Creates a bat object in the cave in a random location. Returns true if bat is spawned.
    //          Returns false otherwise.
    public boolean spawnBat() {
        if (bats.size() < maxBats) { 
            int randomX = ThreadLocalRandom.current().nextInt(0, width);
            int randomY = ThreadLocalRandom.current().nextInt(0, height);
            Bats bat = new Bats(randomX, randomY);
            bats.add(bat);
            return true;
        } else {
            return false;
        }
    }
    
    // Requires: Only called when attack(batIndex) = true
    // Modifies: this
    // Effects: Removes a Bat after it is killed by the user. Adds the batwing to user's inventory.
    public void harvestBat(Creature creature, int batKilledIndex) {
        bats.remove(batKilledIndex);
        creature.getInventory().getBatwings().addBatwing();
        EventLog.getInstance().logEvent(new Event("A Bat has been harvested. Batwing added to inventory."));
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

    public void setMaxBats(int maxBats) {
        this.maxBats = maxBats;
    }

    public void setBatSpawnRate(long batSpawnRate) {
        this.batSpawnRate = batSpawnRate;
    }



    // Data persistence
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Bats", batsToJson());
        json.put("maxBats", maxBats);
        json.put("batSpawnRate", batSpawnRate);
        json.put("width", width);
        json.put("height", height);
        return json;
    }

    // Effects: creates JSONArray with x and y position of each bat
    public JSONArray batsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Bats bat : bats) {
            jsonArray.put(bat.toJson());
        }
        return jsonArray;
    }

}
