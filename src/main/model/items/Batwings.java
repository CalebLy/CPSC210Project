package model.items;

import model.GameObject;
import model.characters.Creature;
import model.eventlogger.Event;
import model.eventlogger.EventLog;
import model.rooms.Cave;

public class Batwings extends GameObject {
    
    private String description;
    private int amount;
    
    
    // Effects: Constructs an item with a static name of "Batwings". Coordinates (0,0) represent 
    //          a meaningless position in the world as they will only be displayed in the inventory
    public Batwings() {
        super("Batwings",0,0);
        this.amount = 0;
        this.description = "\nThe witch indicates that she's unsure how many bat wings she may need."
                           + "\nShe recommends to get 10 or more." 
                           + "\nUse a batwing for a special effect!";
    }


    // Requires: A maximum of 5 Batwings can be used -> maximum occupancy <= 10; batSpawnRate <= 2500ms;
    //           attackCooldownTime <= 1500ms
    // Modifies: this
    // Effects: If there exists a Batwing in the user's inventory spawn rate of bats will increase by 500ms, 
    //          maximum occupancy of bats will increase by 1, attackCooldown will decrease by 500ms
    //          and number of Batwings will decrease by one, then it will return true.
    //          If there doesn't exist a Batwing, it will return false.
    public boolean useBatwing(Creature creature, Cave cave) {
        if (amount > 0) {
            if (creature.getAttackCooldownTime() > 0) {
                creature.setAttackCooldownTime(creature.getAttackCooldownTime() - 500);
            }
            cave.stopSpawningBats();
            if (cave.getBatSpawnRate() > 0) {
                cave.spawnBats(cave.getMaxBats() + 1, cave.getBatSpawnRate() - 500);
            } else {
                cave.spawnBats(cave.getMaxBats() + 1, cave.getBatSpawnRate());
            }  
            
            EventLog.getInstance().logEvent(new Event("A Batwing has been used"));
            amount--;
            return true;
        } else {
            return false;
        }
    }

    public void addBatwing() {
        this.amount++;
    }

    public void removeObject() {
        this.amount--;
        EventLog.getInstance().logEvent(new Event("A Batwing has been dropped"));
    }


    // Getter methods
    public String getDescription() { 
        return description;
    }

    public int getAmount() {
        return amount;
    }

    // Setter methods
    public void setBatwing(int amount) {
        this.amount = amount;
    }
}
