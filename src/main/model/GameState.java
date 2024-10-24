package model;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import model.characters.*;
import model.items.*;
import model.rooms.*;
import ui.GameLoop;

public class GameState {
    private Creature creature;
    private Cave cave;
    private GameLoop gameLoop;
    boolean gameIsRunning;

    public GameState() {

    }

    public void startGame() {
        boolean gameIsRunning = true;
        Creature creature = new Creature(0, 0);
        Cave cave = new Cave(30,30);
        GameLoop gameLoop = new GameLoop();

        creature.startAttackCooldown(creature.getAttackCooldownTime(), cave);
        cave.spawnBats(cave.getMaxBats(), cave.getBatSpawnRate());

        while (gameLoop.getGameIsRunning()) {
            gameLoop.defaultLoopOptions(creature, cave);
        }
    }

    // Effects: Stops all threads/loops. Used to prepare to end the game.
    public void endGameNoSave(Creature creature, Cave cave, Scanner scanner) {
        scanner.close();
        creature.setAbilityToAttack(false);
        cave.stopSpawningBats();
        System.out.println("Game has ended!");
        gameIsRunning = false;
    }

    // Effects: Stops all threads/loops. Used to prepare to end the game. Saves game.
    public void endGameWithSave(Creature creature, Cave cave, Scanner scanner, String fileName) {
        scanner.close();
        creature.setAbilityToAttack(false);
        cave.stopSpawningBats();
        System.out.println("Game has ended!");
        // TODO
        try() {
            // TODO
            System.out.println("Game successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving game");
        }
        gameIsRunning = false;
    }

    
    // Data persistence
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cave", cave.toJson());
        json.put("creature", creature.toJson());
        return json;
    }

}
