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

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

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
        boolean startUp;

        do {
            startUp = gameLoop.initialStartUp(this);
        } while (!startUp);

        creature.startAttackCooldown(creature.getAttackCooldownTime(), cave);
        cave.spawnBats(cave.getMaxBats(), cave.getBatSpawnRate());

        while (gameLoop.getGameIsRunning()) {
            gameLoop.defaultLoopOptions(creature, cave, this);
        }
    }

    // Effects: Stops all threads/loops. Used to prepare to end the game.
    public void endGame(Creature creature, Cave cave) {
        creature.setAbilityToAttack(false);
        cave.stopSpawningBats();
        gameIsRunning = false;
    }


    // Effects: Stops all threads/loops. Used to prepare to end the game. Saves game.
    public void saveGame(Creature creature, Cave cave) throws FileNotFoundException {
            jsonWriter.open(); 
            jsonWriter.write(this);
            jsonWriter.close();
    }
          

    // Effects: Loads the gamestate of the file in JSON_Store
    public void loadGameState() throws IOException {
        GameState loadedGameState = jsonReader.read(); 
        this.creature = loadedGameState.creature;
        this.cave = loadedGameState.cave;
    }

    public GameState loadDefaultGameState() {
        creature = new Creature(0,0);
        cave = new Cave(30, 30);
        GameState gs = new GameState(creature, cave);
        return gs;
    }
    
    // Data persistence
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cave", cave.toJson());
        json.put("creature", creature.toJson());
        return json;
    }



}
