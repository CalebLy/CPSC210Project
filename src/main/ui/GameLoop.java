package ui;

import java.util.Scanner;

import model.characters.Creature;
import model.rooms.Cave;

// Constantly updates and checks for any changes that the user makes or actions the user takes
public class GameLoop {

    private boolean gameIsRunning = true;
    Scanner scanner = new Scanner(System.in);

    // Effect: Prints defaults statements after certain actions are taken by the user
    public void defaultLoopPrintStatement(Creature creature, Cave cave) {

        System.out.println("-------------------------------------------------------------");
        System.out.println("Current Position: (" + creature.getPosX() + ", " + creature.getPosY() + ")");

        for (int i = 0; i < cave.getBats().size(); i++) {
            System.out.println("Current position of Bat #" + (i + 1)
                             + " (" + cave.getBats().get(i).getPosX() + ", " + cave.getBats().get(i).getPosY() + ")");
        }

        System.out.println("Current cave's boundaries: (0,0) -> (" + cave.getWidth() + ", " + cave.getHeight() + ")");
        System.out.println("-------------------------------------------------------------");
    }

    // Effects: controls what happens when the user tries to do any of the default options
    @SuppressWarnings("methodlength")
    public void defaultLoopOptions(Creature creature, Cave cave) {
        defaultLoopPrintStatement(creature, cave);
        if (creature.isInRange(cave) != -1) {
            System.out.println("YOU ARE IN THE RANGE OF A BAT!");
        }
        System.out.println("Type 'm' to open the Move menu" 
                        + "\nType 'i' to open the inventory"
                        + "\nType 'h' to try to hit a bat"
                        + "\nType 'e' to end the game");
        String userChoice = scanner.nextLine();
        switch (userChoice.toLowerCase()) {
            case "m":
                printMoveMenu(creature, cave);
                break;
            case "i":
                creature.getInventory().setInventoryOpen();
                while (creature.getInventory().getInventoryOpen()) {
                    creature.getInventory().displayInventory(creature, cave);
                }
                break;
            case "e":
                endGame(creature, cave);
                break;
            case "h":
                if (creature.canAttack(cave)) {
                    creature.attack(cave);
                    System.out.println("\nThe bat has succesfully been eliminated and harvested."
                                    + "\nYou have obtained a Batwing!");
                    break;
                } else if (creature.isInRange(cave) == -1) {
                    System.out.println("\nOh no! Looks like you're not within 1 block of the bat!" 
                                    + " Bat elimination has failed.");
                    break;
                } else if (creature.getAttackCooldown() == false) {
                    System.out.println("\n\nOh no! Looks like you're currently fatigued!"
                                     + " Please wait until your weaponCooldown is finished: " 
                                     + creature.getAttackCooldownTime() + " miliseconds");
                    break;
                }

        }
    }
    


    // Effects: Presents and calls creature.move() using the user's input
    public void printMoveMenu(Creature creature, Cave cave) {
        System.out.println("\nType 'up' to move up" 
                        + "\nType 'left' to move left" 
                        + "\nType 'down' to move down" 
                        + "\nType 'right' to move right");
        String direction = scanner.nextLine();
        switch (direction.toLowerCase()) {
            case "up":
            case "left":
            case "down":
            case "right":
                System.out.println("\nChoose a distance to move in that direction. Stay within bounds!");
                int distance = scanner.nextInt();
                scanner.nextLine();
                creature.move(direction, distance, cave);
                break;
        }   
    }
    
    // Effects: Stops all threads/loops. Used to prepare to end the game.
    public void endGame(Creature creature, Cave cave) {
        scanner.close();
        creature.setAbilityToAttack(false);
        cave.stopSpawningBats();
        System.out.println("Game has ended!");
        gameIsRunning = false;
    }

    public boolean getGameIsRunning() {
        return this.gameIsRunning;
    }

}
