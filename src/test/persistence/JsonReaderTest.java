package persistence;

import model.Category;
import model.GameState;
import model.Thingy;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

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
            GameState game = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numThingies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameState() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());
            checkThingy("needle", Category.STITCHING, thingies.get(0));
            checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}