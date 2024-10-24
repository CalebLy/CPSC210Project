package persistence;

import model.characters.*;
import model.items.*;
import model.rooms.*;

import model.GameState;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameState gs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNoBatsGameState() {
        JsonReader reader = new JsonReader("./data/testReaderNoBatsGameState.json");
        try {
            GameState gs = reader.read();
            assertEquals("My work room", gs.getName());
            assertEquals(0, gs.numThingies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameState() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
        try {
            GameState gs = reader.read();

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}