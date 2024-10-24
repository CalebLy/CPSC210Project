package persistence;

import model.GameState;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;


// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameState gs = new GameState();
            gs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderDefaultGameState() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultGameState.json");
        try {
            GameState gs = new GameState();
            gs = reader.read();
            assertEquals(0, gs.getCreature().getPosX());
            assertEquals(0, gs.getCreature().getPosY());
            assertEquals(30, gs.getCave().getHeight());
            assertEquals(30, gs.getCave().getWidth());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameState() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
        try {
            GameState gs = reader.read();
            assertEquals(0, gs.getCreature().getPosX());
            assertEquals(6, gs.getCreature().getPosY());
            assertEquals(30, gs.getCave().getHeight());
            assertEquals(30, gs.getCave().getWidth());

            assertEquals(4500, gs.getCave().getBatSpawnRate());
            assertEquals(6, gs.getCave().getMaxBats());
            
            assertEquals(14, gs.getCave().getBat(0).getPosX());
            assertEquals(9, gs.getCave().getBat(0).getPosY());

            assertEquals(27, gs.getCave().getBat(1).getPosX());
            assertEquals(18, gs.getCave().getBat(1).getPosY());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}