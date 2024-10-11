package ui;


import java.util.Scanner;

import model.characters.Bats;
import model.characters.Creature;
import model.rooms.Cave;

public class Main {
    public static void main(String[] args) throws Exception {

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
}
