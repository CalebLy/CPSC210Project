package model.characters;

import model.GameObject;
import model.items.Batwings;
import ui.inventory.Inventory;

import java.util.Timer;
import java.util.TimerTask;

// This class represents the user's initial character.
// This class will contain all the methods needed to give the user functionality
public class Creature extends GameObject {

    protected Inventory inventory;
    protected int attackCooldownTime;
    protected boolean attackCooldown;
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates the Creature character with the name "????" as the creature never refers to himself with a title,
    //          at coordinate (x,y).
    public Creature(int x, int y) {
        super("????", x, y);
        attackCooldownTime = 4; 
        attackCooldown = true;
        inventory = new Inventory();
    }

    // Modifies: this
    // Effects: Attacks the bat, if canAttack is true, and removeObject()'s' that specific bat 
    //          Has a cooldown that varies with batwing usage. Initially = 4 seconds.
    public boolean attack(Bats bats) {
        // stub
        return false;
    }

    // Effects: Checks if isInRange and attackOffCooldown = true
    public boolean canAttack(Bats bats) {
        // stub
        return false;
    }
    
    // Requires: this and gameObject must be x <= 1 and y <= 1 away from each other
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


    // Requires: cooldownInMS >= 0
    // Modifies: this
    // Effects: Determines when attackCooldown is set to true after attacking
    private void startAttackCooldown() {
        // stub
    }



    // Getter methods
    public int getAttackCooldownTime() {
        return this.attackCooldownTime;
    }

    public boolean getAttackCooldown() {
        return this.attackCooldown;
    }

    public Inventory getInventory() {
        return inventory;
    }

    // Setter methods
    public int setAttackCooldownTime(int timeSubtracted) {
        return this.attackCooldownTime -= timeSubtracted;
    }

    public void setAttackCooldown(boolean cooldownUp) {
        this.attackCooldown = cooldownUp;
    }

    
    public void removeObject() {
        this.setIsActive(false);
    }
    



}
