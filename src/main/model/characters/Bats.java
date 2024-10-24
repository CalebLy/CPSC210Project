package model.characters;

import persistence.Writable;
import org.json.JSONObject;

import model.GameObject;

public class Bats extends GameObject implements Writable {


    protected int maxBats;
    protected double batSpawnRate;
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Constructs a character with a static name of "Bat", at coordinates (x,y).
    //          
    public Bats(int x, int y) {
        super("Bat", x, y);
    }

  
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("x", getPosX());
        json.put("y", getPosY());
        return json;
    }

}
