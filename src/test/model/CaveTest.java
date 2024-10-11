package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.characters.Bats;
import model.characters.Creature;
import model.items.Batwings;
import model.rooms.Cave;





public class CaveTest {
    
    private Creature creatureTest;
    private Cave caveTest;
    private Batwings batwingTest;


    @BeforeEach
    void runBefore() {
        creatureTest = new Creature(0, 0);
        caveTest = new Cave(20,20);
        batwingTest = new Batwings();
    }

    @Test
    void testCaveConstructor() {
        assertEquals("Bat", caveTest.getName());
        assertEquals(20, caveTest.getWidth());
        assertEquals(20, caveTest.getHeight());
        assertEquals(5, caveTest.getMaxBats());
        assertEquals(5.0, caveTest.getBatSpawnRate());
        assertNotNull(caveTest.getBats());
    }

    @Test
    void testIsWithinBounds() {
        assertTrue(caveTest.isWithinBounds(creatureTest.getX(), creatureTest.getY()));
        creatureTest.setPosition(30, 0);
        assertFalse(caveTest.isWithinBounds(creatureTest.getX(), creatureTest.getY()));
        creatureTest.setPosition(30,30);
        assertFalse(caveTest.isWithinBounds(creatureTest.getX(), creatureTest.getY()));
        creatureTest.setPosition(20,20);
        assertTrue(caveTest.isWithinBounds(creatureTest.getX(), creatureTest.getY()));
    }

    @Test
    void testSpawnBat() {
        assertEquals(0, caveTest.getBats().size());
        caveTest.spawnBat();
        assertEquals(1, caveTest.getBats().size());
        caveTest.spawnBat();
        caveTest.spawnBat();
        caveTest.spawnBat();
        caveTest.spawnBat();
        assertEquals(5, caveTest.getBats().size());
        caveTest.spawnBat();
        assertEquals(5, caveTest.getBats().size());
    }

    @Test
    void testSpawnBats() {
        caveTest.spawnBats(caveTest.getMaxBats(), caveTest.getBatSpawnRate());

        try {
            Thread.sleep(caveTest.getBatSpawnRate()+300);
            assertEquals(1, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
    
        try {
            Thread.sleep(caveTest.getBatSpawnRate()-500);
            assertEquals(1, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }

        try {
            Thread.sleep(800);
            assertEquals(2, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }

        caveTest.stopSpawningBats();
        caveTest.spawnBats(5, 2000);

        try {
            Thread.sleep((caveTest.getBatSpawnRate() * 3) + 900);
            assertEquals(5, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }

        try {
            Thread.sleep(3000);
            assertEquals(5, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }

        caveTest.stopSpawningBats();
        caveTest.spawnBats(6,2000);

        try {
            Thread.sleep(2300);
            assertEquals(6, caveTest.getBats().size());
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
       



    }

    @Test
    void testHarvestBat() {
        caveTest.spawnBat(); 
        caveTest.harvestBat(0);
        assertEquals(0, caveTest.getBats().size());
        assertEquals(1, creatureTest.getInventory().getBatwings());
        assertNull(caveTest.getBat(0));
    }
}
