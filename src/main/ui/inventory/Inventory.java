package ui.inventory;

import java.util.Scanner;

import model.characters.Creature;
import model.items.Batwings;
import model.rooms.Cave;

public class Inventory {
    protected Batwings batwings;
    protected boolean inventoryOpen;
    
    
    // Effects: intanstializes an inventory
    public Inventory() {
        batwings = new Batwings();
    }

    // Effects: Displays inventory. Gives user option to view amount of Batwings,
    //          use Batwings, drop Batwings, or view description of Batwings.
    @SuppressWarnings("methodlength")
    public void displayInventory(Creature creature, Cave cave) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'a' to view amount of Batwings" 
                           + "\nType 'u' to use a Batwing" 
                           + "\nType 'd' to drop a Batwing" 
                           + "\nType 'v' to view description of Batwings" 
                           + "\nType 'i' to close the inventory");
        String userChoice = scanner.nextLine();
        switch (userChoice.toLowerCase()) {
            case "a":
                System.out.println("Batwings: " + this.getBatwings().getAmount());
                break;
            case "u":
                this.getBatwings().useBatwing(creature, cave);
                System.out.println("A Batwing has been used! Batwings left: " + this.getBatwings().getAmount());
                System.out.println("AttackCooldownTime is now: " + creature.getAttackCooldownTime()/1000 + " seconds" 
                                + "\nMaxBats is now: " + cave.getMaxBats() 
                                + "\nBatSpawnRate is now: " + cave.getBatSpawnRate()); 
                break;
            case "d":
                this.getBatwings().removeObject();
                System.out.println("A Batwing has been dropped! Batwings left: " + this.getBatwings().getAmount());
                break;
            case "v":
                System.out.println("" + this.getBatwings().getDescription());
                break;
            case "i":
                inventoryOpen = false;
                break;
        }
        scanner.close();
    }


    // Getter methods
    public Batwings getBatwings() {
        return batwings;
    }

    public boolean getInventoryOpen() {
        return inventoryOpen;
    }
}
