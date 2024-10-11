package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.characters.Bats;
import model.characters.Creature;





public class CreatureTest {
    
    private Creature creatureTest;
    private Bats batTest;
    private Bats batTest2;
    private Bats batTest3;
    private Bats batTest4;


    @BeforeEach
    void runBefore() {
        creatureTest = new Creature(0, 0);
        batTest = new Bats(0,0);
        batTest2 = new Bats(0,0);
        batTest3 = new Bats(0,0);
        batTest4 = new Bats (0,0);

        
    }

    @Test 
    void testCreatureConstructor() {
        assertEquals("????", creatureTest.getName());
        assertEquals(0, creatureTest.getX());
        assertEquals(0, creatureTest.getY());
        assertEquals(4, creatureTest.getAttackCooldownTime());
        assertTrue(creatureTest.getAttackCooldown());
        assertEquals(0, creatureTest.getInventory().getBatwings().getAmount());
    }

    @Test 
    void testMove() {
        assertEquals(creatureTest.getY(), creatureTest.move("up", 5));
        assertEquals(creatureTest.getX(), creatureTest.move("left", 5));
        assertEquals(creatureTest.getY(), creatureTest.move("down",5));
        assertEquals(creatureTest.getX(), creatureTest.move("right", 5));
        assertEquals(0, creatureTest.getX());
        assertEquals(0, creatureTest.getY());
    }

    @Test
    void testIsInRange() {
        batTest.setPosition(5,5);
        creatureTest.setPosition(4,4);
        assertTrue(creatureTest.isInRange(batTest));
        creatureTest.setPosition(4,3);
        assertFalse(creatureTest.isInRange(batTest));
        batTest.setPosition(4,3);
        assertTrue(creatureTest.isInRange(batTest));

        batTest.setIsActive(false);
        assertFalse(creatureTest.isInRange(batTest));
    }

    @Test
    void testCanAttack() {
        batTest.setPosition(5,5);
        creatureTest.setAttackCooldown(false);
        assertFalse(creatureTest.canAttack(batTest)); 

        creatureTest.setAttackCooldown(true);
        assertFalse(creatureTest.canAttack(batTest));

        creatureTest.setPosition(5,4);
        assertTrue(creatureTest.canAttack(batTest));

        batTest2.setPosition(6,6 );
        assertFalse(creatureTest.canAttack(batTest2));

        creatureTest.setPosition(5,6);
        assertTrue(creatureTest.canAttack(batTest2));

        batTest.setIsActive(false);
        assertFalse(creatureTest.canAttack(batTest));
    }

    @Test
    void testAttack() {
        batTest.setPosition(5,5);
        batTest2.setPosition(5,5);
        creatureTest.setPosition(5,4);
        assertTrue(creatureTest.attack(batTest));
        assertFalse(batTest.getIsActive());
        assertFalse(creatureTest.canAttack(batTest));

        assertFalse(creatureTest.attack(batTest2));
        try {
            Thread.sleep(4100);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertTrue(creatureTest.attack(batTest2));
        assertFalse(batTest2.getIsActive());
    }

    @Test
    void testStartAttackCooldown() {
        batTest.setPosition(5,5);
        batTest2.setPosition(5,5);
        batTest3.setPosition(8,8);
        batTest4.setPosition(8,8);
        creatureTest.setPosition(5,5);

        assertTrue(creatureTest.attack(batTest));
        assertFalse(creatureTest.canAttack(batTest2));
        try {
            Thread.sleep(4100);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertTrue(creatureTest.canAttack(batTest2));
        assertTrue(creatureTest.attack(batTest2));

        creatureTest.setPosition(8,8);
        creatureTest.setAttackCooldownTime(1000);
        assertFalse(creatureTest.canAttack(batTest3));
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertFalse(creatureTest.canAttack(batTest3));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertTrue(creatureTest.canAttack(batTest3));
        assertTrue(creatureTest.attack(batTest3));

        creatureTest.setAttackCooldownTime(-2000);
        assertFalse(creatureTest.canAttack(batTest4));
        try {
            Thread.sleep(4100);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertFalse(creatureTest.canAttack(batTest4));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted" + e.getMessage());
        }
        assertTrue(creatureTest.attack(batTest4));
    }
}
