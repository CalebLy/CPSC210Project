package model.characters;

import model.GameObject;
import model.items.Inventory;
import model.rooms.Cave;

import org.json.JSONObject;




// This class represents the user's initial character.
// This class will contain all the methods needed to give the user functionality
public class Creature extends GameObject {

    protected Inventory inventory;
    protected long attackCooldownTime;
    protected boolean attackCooldown;
    protected boolean abilityToAttack;
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates the Creature character with name "????" as the creature never refers to himself with a title,
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
            int differenceX = Math.abs(cave.getBats().get(i).getPosX() - this.getPosX());
            int differenceY = Math.abs(cave.getBats().get(i).getPosY() - this.getPosY());
            if (differenceX <= (cave.getWidth() / 20) && differenceY <= (cave.getHeight() / 20)) {
                return i;
            } 
        }
        return -1;
    }

    // Requires: must stay within bounds
    // Modifies: this
    // Effects: Moves the character in the direction of 'direction' a distance of 'distance'
    @SuppressWarnings("methodlength")
    public boolean move(String direction, int distance, Cave cave) {
        int originalX = posX;
        int originalY = posY;
        switch (direction.toLowerCase()) {
            case "up":
                posY += distance;
                break;
            case "down":
                posY -= distance;
                break;
            case "left":
                posX -= distance;
                break;
            case "right":
                posX += distance;
                break;
            default:
                return false;
        }
        if (cave.isWithinBounds(posX, posY)) {
            this.setPosition(posX, posY);
            return true;
        } else {
            posX = originalX;
            posY = originalY;
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

            while (cave.getIsActive() && abilityToAttack == true) {
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
    public long setAttackCooldownTime(long newAttackCooldownTime) {
        return (attackCooldownTime = newAttackCooldownTime);
    }

    public void setAttackCooldown(boolean cooldownUp) {
        this.attackCooldown = cooldownUp;
    }

    public void setAbilityToAttack(boolean activity) {
        this.abilityToAttack = activity;
    }

    
    // Data persistence
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("attackCooldownTime", attackCooldownTime);
        json.put("attackCooldown", attackCooldown);
        json.put("abilityToAttack", abilityToAttack);
        json.put("x", getPosX());
        json.put("y", getPosY());
        json.put("batwings", inventory.getBatwings().getAmount());
        return json;
    }



}
