package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.characters.Bats;
import model.characters.Creature;
import model.rooms.Cave;





public class CreatureTest {
    
    private Creature creatureTest;
    private Bats batTest;
    private Bats batTest2;
    private Bats batTest3;
    private Bats batTest4;
    private Cave caveTest;


    @BeforeEach
    void runBefore() {
        creatureTest = new Creature(0, 0);
        batTest = new Bats(0,0);
        batTest2 = new Bats(0,0);
        batTest3 = new Bats(0,0);
        batTest4 = new Bats (0,0);
        caveTest = new Cave(20,20);
        caveTest.getBats().add(batTest);
        caveTest.getBats().add(batTest2);
        caveTest.getBats().add(batTest3);
        caveTest.getBats().add(batTest4);

        
    }

    @Test 
    void testCreatureConstructor() {
        assertEquals("????", creatureTest.getName());
        assertEquals(0, creatureTest.getPosX());
        assertEquals(0, creatureTest.getPosY());
        assertEquals(4000, creatureTest.getAttackCooldownTime());
        assertTrue(creatureTest.getAttackCooldown());
        assertEquals(0, creatureTest.getInventory().getBatwings().getAmount());
    }

    @Test 
    void testMove() {
        creatureTest.move("Up", 5, caveTest);
        assertEquals(5, creatureTest.getPosY());

        creatureTest.move("right", 5, caveTest);
        assertEquals(5, creatureTest.getPosX());

        creatureTest.move("down",5, caveTest);
        assertEquals(0, creatureTest.getPosY());

        creatureTest.move("left", 5, caveTest);
        assertEquals(0, creatureTest.getPosX());

        assertFalse(creatureTest.move("left", 5, caveTest));
        assertEquals(0, creatureTest.getPosX());

        assertFalse(creatureTest.move("down", 5, caveTest));
        assertEquals(0, creatureTest.getPosY());

            assertFalse(creatureTest.move("777", 5, caveTest));

    }

    @Test
    void testIsInRange() {
        batTest.setPosition(5,5);
        creatureTest.setPosition(4,4);
        assertEquals(0, creatureTest.isInRange(caveTest));
        creatureTest.setPosition(4,3);
        assertEquals(-1, creatureTest.isInRange(caveTest));
        batTest.setPosition(4,3);
        assertEquals(0, creatureTest.isInRange(caveTest));
        batTest2.setPosition(5, 4);
        assertEquals(0, creatureTest.isInRange(caveTest));
        batTest.setPosition(1, 01);
        assertEquals(1, creatureTest.isInRange(caveTest));
    }

    @Test
    void testCanAttack() {
        batTest.setPosition(5,5);
        creatureTest.setAttackCooldown(false);
        assertFalse(creatureTest.canAttack(caveTest)); 

        creatureTest.setAttackCooldown(true);
        assertTrue(creatureTest.canAttack(caveTest));

        creatureTest.setPosition(5,4);
        assertTrue(creatureTest.canAttack(caveTest));

        batTest.setPosition(6, 6);
        batTest2.setPosition(6,5 );
        assertTrue(creatureTest.canAttack(caveTest));

        creatureTest.setPosition(7,7);
        assertTrue(creatureTest.canAttack(caveTest));

        caveTest.harvestBat(creatureTest, 0);
        assertFalse(creatureTest.canAttack(caveTest));
    }

    @Test
    void testAttack() {
        creatureTest.setPosition(5,4);
        caveTest.getBats().get(0).setPosition(5,5);
        caveTest.getBats().get(1).setPosition(5,5);
        assertTrue(creatureTest.attack(caveTest));
        assertFalse(creatureTest.attack(caveTest));

        creatureTest.setAttackCooldown(true);

        assertTrue(creatureTest.attack(caveTest));
        creatureTest.setAttackCooldown(true);
        assertFalse(creatureTest.attack(caveTest));
    }
  

    @Test
    void testStartAttackCooldown() {
    
        caveTest.getBats().get(0).setPosition(5,5);
        caveTest.getBats().get(1).setPosition(5,5);
        caveTest.getBats().get(2).setPosition(8,8);
        caveTest.getBats().get(3).setPosition(8,8);
        creatureTest.setPosition(5,5);

        assertTrue(creatureTest.attack(caveTest));
        assertFalse(creatureTest.canAttack(caveTest));

        creatureTest.startAttackCooldown(creatureTest.getAttackCooldownTime(), caveTest);   
        try {
            Thread.sleep(creatureTest.getAttackCooldownTime()+600);
            assertTrue(creatureTest.canAttack(caveTest));
            assertTrue(creatureTest.attack(caveTest));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }


        creatureTest.setPosition(8,8);
        creatureTest.setAbilityToAttack(false);
        creatureTest.setAttackCooldownTime(1000);
        creatureTest.startAttackCooldown(creatureTest.getAttackCooldownTime(), caveTest);  

        try {
            Thread.sleep(creatureTest.getAttackCooldownTime()-700);
            assertFalse(creatureTest.attack(caveTest));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        
        try {
            Thread.sleep(1300);
            assertTrue(creatureTest.attack(caveTest));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
    
        creatureTest.setAbilityToAttack(false);
        creatureTest.setAttackCooldownTime(-1000);
        creatureTest.startAttackCooldown(creatureTest.getAttackCooldownTime(), caveTest);  
        
        try {
            Thread.sleep(creatureTest.getAttackCooldownTime() - 2000);
            assertFalse(creatureTest.getAttackCooldown());
            assertFalse(creatureTest.attack(caveTest));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        try {
            Thread.sleep(2500);
            assertTrue(creatureTest.attack(caveTest));
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
    }
}
