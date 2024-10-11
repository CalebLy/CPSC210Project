package ui.inventory;

import model.items.Batwings;

public class Inventory{
    protected Batwings batwings;
    
    // Effects: intanstializes an inventory
    public Inventory(){
        batwings = new Batwings();
    }

    // Effects: Displays inventory. Gives user option to view amount of Batwings,
    //          use Batwings, drop Batwings, or view description of Batwings.
    public void displayInventory() {
        // stub
    }


    // Getter methods
    public Batwings getBatwings() {
        return batwings;
    }






}
