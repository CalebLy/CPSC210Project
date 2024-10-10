package model.characters;

import model.GameObject;
import model.items.Batwings;
import ui.inventory.Inventory;

// This class represents the user's initial character.
// This class will contain all the methods needed to give the user functionality
public class Creature extends GameObject {

    protected Inventory inventory;
    protected boolean attackCooldown;
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates the Creature character with the name "????" as the creature never refers to himself with a title,
    //          at coordinate (x,y).
    public Creature(int x, int y) {
        super("????", x, y);
        inventory = new Inventory();
    }

    // Modifies: this
    // Effects: Attacks the bat, if canAttack is true, and sets the specific bat isActive = false.
    //          Has a cooldown of 4 seconds.
    public boolean attack(Bats bats) {
        // stub
        return false;
    }

    // Effects: Checks if isInRange and attackCooldown = true
    public boolean canAttack(Bats bats) {
        // stub
        return false;
    }
    

    // Effects: Checks if gameObject is in range for the user to interact with it.
    public boolean isInRange(GameObject gameObject) {
        // stub
        return false;
    }

    // Requires: must stay within bounds
    // Modifies: this
    // Effects: Moves the character in the direction of 'direction' a distance of 'distance'
    public boolean move(String direction, int distance) {
        // stub
        return false;
    }

    public void removeObject() {
        this.setIsActive(false);
    }


}
