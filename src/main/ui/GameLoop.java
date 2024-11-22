package ui;

import model.GameState;
import model.characters.Creature;
import model.rooms.Cave;
import ui.gui.MyFrame;
import ui.gui.escapemenu.EscapeMenu;
import ui.gui.inventory.InventoryGUI;
import ui.gui.startupscreen.StartUpScreen;

// Constantly updates and checks for any changes that the user makes or actions the user takes
public class GameLoop {

    MyFrame myFrame = new MyFrame();
    EscapeMenu escapeMenu;
    InventoryGUI inventoryGUI;
    
    // Effects: Loads previously played file, or a fresh file.
    public boolean initialStartUp(GameState gs) {

        StartUpScreen startUpScreen = new StartUpScreen(gs);
        do {
            myFrame.startUpScreenSetUp(startUpScreen);
        } while (!startUpScreen.getResult());

        return startUpScreen.getResult();
        
    }
    
    // Effects: controls what happens when the user tries to do any of the default options
    public void defaultLoopOptions(Creature creature, Cave cave, GameState gs) {
        escapeMenu = new EscapeMenu(gs, this, myFrame);
        inventoryGUI = new InventoryGUI();
        myFrame.caveSetUp();
        myFrame.batsSetUp(creature, cave);
        myFrame.escapeMenuSetUp(escapeMenu);
        myFrame.inventorySetUp(inventoryGUI, creature, cave);
        myFrame.creatureSetUp(creature, cave);
    }
}
