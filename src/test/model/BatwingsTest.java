package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.characters.Creature;
import model.items.Batwings;
import model.rooms.Cave;



public class BatwingsTest {
    
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
    void testBatwingConstructor() {
        assertEquals("Batwings", batwingTest.getName());
        assertEquals(0, batwingTest.getAmount());
        assertEquals("\nThe witch indicates that she's unsure how many bat wings she may need."
                    + "\nShe recommends to get 10 or more.\nUse a batwing for a special effect!", 
                    batwingTest.getDescription());
    }

    @Test
    @SuppressWarnings("methodlength")
    void testUseBatwing() {
        creatureTest.getInventory().getBatwings().addBatwing(); 
        assertEquals(1, creatureTest.getInventory().getBatwings().getAmount());
        creatureTest.getInventory().getBatwings().useBatwing(creatureTest, caveTest);
        assertEquals(0, creatureTest.getInventory().getBatwings().getAmount());
        assertEquals(3500, creatureTest.getAttackCooldownTime());
        assertEquals(6, caveTest.getMaxBats());
        assertEquals(4500, caveTest.getBatSpawnRate());
        creatureTest.getInventory().getBatwings().useBatwing(creatureTest, caveTest);
        assertEquals(0, creatureTest.getInventory().getBatwings().getAmount());
        assertEquals(3500, creatureTest.getAttackCooldownTime());
        assertEquals(6, caveTest.getMaxBats());
        assertEquals(4500, caveTest.getBatSpawnRate());


        for (int i = 0; i < 4; i++) {
            creatureTest.getInventory().getBatwings().addBatwing();
            creatureTest.getInventory().getBatwings().useBatwing(creatureTest, caveTest);
        }

        assertEquals(0, creatureTest.getInventory().getBatwings().getAmount());
        assertEquals(1500, creatureTest.getAttackCooldownTime());
        assertEquals(10, caveTest.getMaxBats());
        assertEquals(2500, caveTest.getBatSpawnRate());


        creatureTest.getInventory().getBatwings().addBatwing();
        creatureTest.getInventory().getBatwings().useBatwing(creatureTest, caveTest);

        assertEquals(1, creatureTest.getInventory().getBatwings().getAmount());
        assertEquals(1500, creatureTest.getAttackCooldownTime());
        assertEquals(10, caveTest.getMaxBats());
        assertEquals(2500, caveTest.getBatSpawnRate());
    }
}
