package model.characters;

import model.GameObject;
import model.rooms.Cave;
import ui.inventory.Inventory;



// This class represents the user's initial character.
// This class will contain all the methods needed to give the user functionality
public class Creature extends GameObject {

    protected Inventory inventory;
    protected long attackCooldownTime;
    protected boolean attackCooldown;
    protected boolean abilityToAttack;
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates the Creature character with the name "????" as the creature never refers to himself with a title,
    //          at coordinate (x,y).
    public Creature(int x, int y) {
        super("????", x, y);
        attackCooldownTime = 4000; 
        attackCooldown = true;
        inventory = new Inventory();
    }

    // Modifies: this
    // Effects: Attacks the bat, if canAttack is true, and harvestBat that specific bat 
    //          Has a cooldown that varies with batwing usage. Initially = 4000ms.
    public boolean attack(Cave cave) {
        if (canAttack(cave)) {
            cave.harvestBat(this, isInRange(cave));
            setAttackCooldown(false);
            return true;
        } else {
            return false;
        }
    }

    // Effects: Checks if isInRange and attackOffCooldown = true
    public boolean canAttack(Cave cave) {

        if (attackCooldown == true && this.isInRange(cave) != -1) {
            return true;
        } else {
            return false;
        }
        
    }
    
    // Requires: 'this' and 'gameObject' must be x <= 1 and y <= 1 away from each other
    // Effects: Checks if gameObject is in range for the user to interact with it.
    //          Returns index of gameObject if isInRange. Returns -1 otherwise.
    public int isInRange(Cave cave) {
        for (int i = 0; i < cave.getBats().size(); i++) {
            int xDifference = Math.abs(cave.getBats().get(i).getX() - this.getX());
            int yDifference = Math.abs(cave.getBats().get(i).getY() - this.getY());
            if (xDifference <= 1 && yDifference <= 1) {
                return i;
            } 
        }
        return -1;
    }

    // Requires: must stay within bounds
    // Modifies: this
    // Effects: Moves the character in the direction of 'direction' a distance of 'distance'
    public boolean move(String direction, int distance, Cave cave) {
        int originalX = x;
        int originalY = y;

        switch (direction.toLowerCase()) {
            case "up":
                y += distance;
                break;
            case "down":
                y -= distance;
                break;
            case "left":
                x -= distance;
                break;
            case "right":
                x += distance;
                break;
            default:
                return false;
        }

        if (cave.isWithinBounds(x, y)) {
            this.setPosition(x, y);
            return true;
        } else {
            x = originalX;
            y = originalY;
            return false;
        }
    }
    


    // Requires: cooldownInMS >= 0
    // Modifies: this
    // Effects: Determines when attackCooldown is set to true after attacking
    public void startAttackCooldown(long attackCooldownTime, Cave cave) {
        setAbilityToAttack(true);
        attackCooldown = false;
        this.attackCooldownTime = attackCooldownTime;
        new Thread(() -> {

            while(cave.getIsActive() && abilityToAttack == true) {

                // while(!attackCooldown) {
                //     try {
                //         Thread.sleep(100); 
                //     } catch (InterruptedException e) {
                //         Thread.currentThread().interrupt(); 
                //     }
                // }

                try {
                    Thread.sleep(this.attackCooldownTime);
                    attackCooldown = true;
                } catch (InterruptedException e) {
                    System.err.println("AttackCooldownTimer interrupted" + e.getMessage());
                }
            }

        }).start();
    }



    // Getter methods
    public long getAttackCooldownTime() {
        return this.attackCooldownTime;
    }

    public boolean getAttackCooldown() {
        return this.attackCooldown;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public boolean getAbilityToAttack() {
        return this.abilityToAttack;
    }

    // Setter methods
    // Reduces attackCooldownTime by timeReduced miliseconds
    public long setAttackCooldownTime(long timeReduced) {
        return this.attackCooldownTime -= timeReduced;
    }

    public void setAttackCooldown(boolean cooldownUp) {
        this.attackCooldown = cooldownUp;
    }

    public void setAbilityToAttack(boolean activity) {
        this.abilityToAttack = activity;
    }

    
    



}
