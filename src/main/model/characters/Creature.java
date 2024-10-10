package model.characters;

import model.GameObject;
import model.inventory.Inventory;
import model.items.Batwings;

// This class represents the user's initial character.
// This class will contain all the methods needed to give the user functionality
public class Creature extends GameObject {
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates the Creature character with the name "????" as the creature never refers to himself with a title,
    //          at coordinate (x,y).
    public Creature(int x, int y) {
        super("????", x, y);
    }
}
