package model.items;


public class Inventory {
    protected Batwings batwings;
    protected boolean inventoryOpen;
    
    
    // Effects: intanstializes an inventory
    public Inventory() {
        batwings = new Batwings();
    }

    // Getter methods
    public Batwings getBatwings() {
        return batwings;
    }

    public boolean getInventoryOpen() {
        return inventoryOpen;
    }

    // Setter methods
    public void setInventoryOpen() {
        inventoryOpen = true;
    }
}
