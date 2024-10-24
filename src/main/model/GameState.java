package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

import model.characters.*;
import model.rooms.*;
import ui.GameLoop;

import persistence.JsonReader;
import persistence.JsonWriter;

public class GameState {
    private static final String JSON_STORE = "./data/gamestate.json";
    private Creature creature;
    private Cave cave;
    private GameLoop gameLoop;
    boolean gameIsRunning;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public GameState(Creature creature, Cave cave) {
        this.creature = creature;
        this.cave = cave;
    }

    public GameState() {}

    public void startGame() throws FileNotFoundException  {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        boolean gameIsRunning = true;
        GameLoop gameLoop = new GameLoop();

        creature.startAttackCooldown(creature.getAttackCooldownTime(), cave);
        cave.spawnBats(cave.getMaxBats(), cave.getBatSpawnRate());

        while (gameLoop.getGameIsRunning()) {
            gameLoop.defaultLoopOptions(creature, cave);
        }
    }

    // Effects: Stops all threads/loops. Used to prepare to end the game.
    public void endGame(Creature creature, Cave cave, Scanner scanner) {
        scanner.close();
        creature.setAbilityToAttack(false);
        cave.stopSpawningBats();
        System.out.println("Game has ended!");
        gameIsRunning = false;
    }


    // Effects: Stops all threads/loops. Used to prepare to end the game. Saves game.
    public void saveGame(Creature creature, Cave cave, Scanner scanner) {
        try {
            jsonWriter.open(); 
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Your game has been successfully saved to " + JSON_STORE);
        } catch (FileNotFoundException f) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Effects: Loads the gamestate of the file in JSON_Store
    public GameState loadGameState() {
        try {
           GameState loadedGameState = jsonReader.read(); 
           System.out.println("Loaded your game from " + JSON_STORE);
           return loadedGameState;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return null;
        }
    }
    
    // Data persistence
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cave", cave.toJson());
        json.put("creature", creature.toJson());
        return json;
    }



}
