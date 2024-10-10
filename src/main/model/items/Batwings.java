package model.items;
import model.GameObject;

public class Batwings extends GameObject{
    
    private String description;
    private int amount;
    
    
    // Effects: Constructs an item with a static name of "Batwings". Coordinates (0,0) represent 
    //          a meaningless position in the world as they will only be displayed in the inventory
    public Batwings() {
        super("Batwings",0,0);
        this.amount = 0;
        this.description = "The witch indicates that she's unsure how many bat wings she may need." + 
                           "\nShe recommends to get 10 or more." + 
                           "\nUse a batwing for a special effect!";
    }


    // Modifies: this
    // Effects: If there exists a Batwing in the user's inventory spawn rate of bats will increase, 
    //          maximum occupancy of bats will increase, and it will return true.
    //          If there doesn't exist a Batwing, it will return false.
    public boolean useBatwing() {
        // stub
        return false;
    }


    public void addBatwing() {
        this.amount++;
    }

    public void removeObject() {
        this.amount--;
    }

    // Getter methods
    public String getDescription() { 
        return description;
    }

    public int getAmount() {
        return amount;
    }
}
