package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GameState;
import model.characters.Creature;
import model.rooms.Cave;
import ui.gui.Bats.InTheRangeOfBatLabel;
import ui.gui.StartUpScreen.StartUpScreen;

// Constantly updates and checks for any changes that the user makes or actions the user takes
public class GameLoop {

    private static final String JSON_STORE = "./data/gamestate.json";
    private boolean gameIsRunning = true;
    Scanner scanner = new Scanner(System.in);
    MyFrame myFrame = new MyFrame();
    InTheRangeOfBatLabel inTheRangeOfBatLabel = new InTheRangeOfBatLabel();
    

    // Effects: Loads previously played file, or a fresh file.
    public boolean initialStartUp(GameState gs) {

        StartUpScreen startUpScreen = new StartUpScreen(gs);
        do {
            myFrame.startUpScreenSetUp(startUpScreen);
        } while (!startUpScreen.getResult());

        return startUpScreen.getResult();
        
        // System.out.println("\nType 'l' to load a saved file" 
        //                 + "\nType 'f' to start a fresh save game.");
        // String userChoice = scanner.nextLine();
        // switch (userChoice.toLowerCase()) {
        //     case "l":
        //         try {
        //             gs.loadGameState();
        //             System.out.println("\nLoaded your game from " + JSON_STORE);
        //         } catch (IOException e) {
        //             System.out.println("\nUnable to read from file: " + JSON_STORE);
        //         }
        //         return true;
        //     case "f":
        //         gs.loadDefaultGameState();
        //         return true;
        //     default: 
        //         System.out.println("Invalid input");
        //         return false;
        // }
    }
    

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
    public void defaultLoopOptions(Creature creature, Cave cave, GameState gs) {
        

        myFrame.creatureSetUp(creature, cave);
        defaultLoopPrintStatement(creature, cave); 
        
        if (creature.isInRange(cave) != -1) {
            myFrame.inTheRangeOfBatLabelSetUp(inTheRangeOfBatLabel);
        } else {
            myFrame.inTheRangeOfBatLabelSetOff(inTheRangeOfBatLabel);
        }

        System.out.println("Type 'm' to open the Move menu" 
                        + "\nType 'i' to open the inventory"
                        + "\nType 'h' to try to hit a bat"
                        + "\nType 's' to save game"
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
                System.out.println("\nGame has ended!");
                gs.endGame(creature, cave);
                gameIsRunning = false;
                break;
            case "s":
                try {
                    gs.saveGame(creature, cave);
                    System.out.println("\nYour game has been successfully saved to " + JSON_STORE);
                } catch (FileNotFoundException f) {
                    System.out.println("\nUnable to write to file: " + JSON_STORE);
                }
    
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
            default:
                System.out.println("Invalid input");
                break;

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
                if (!creature.move(direction, distance, cave)) {
                    System.out.println("That is out of bounds! Moving has failed.");
                }
                break;
            default:
                System.out.println("Invalid input");
                break;
        }   
    }


    public boolean getGameIsRunning() {
        return this.gameIsRunning;
    }

    public void setGameIsRunning(boolean activity) {
        gameIsRunning = activity;
    }

}
