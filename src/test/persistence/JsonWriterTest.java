package persistence;

import model.characters.*;


import model.GameState;

import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            new GameState();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterDefaultGameState() {
        try {
            GameState gs = new GameState();
            gs = gs.loadDefaultGameState();
            JsonWriter writer = new JsonWriter("./data/testReaderDefaultGameState.json");
            writer.open();
            writer.write(gs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderDefaultGameState.json");
            gs = reader.read();
            assertEquals(200, gs.getCreature().getPosX());
            assertEquals(400, gs.getCreature().getPosY());
            assertEquals(768, gs.getCave().getHeight());
            assertEquals(1376, gs.getCave().getWidth());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    @SuppressWarnings("methodlength")
    void testWriterGeneralWorkroom() {
        try {
            GameState gs = new GameState();
            gs = gs.loadDefaultGameState();
            gs.getCreature().setPosition(0,6);
            gs.getCave().setBatSpawnRate(4500);
            gs.getCave().setMaxBats(6);
            Bats bat1 = new Bats(14,9);
            Bats bat2 = new Bats(27,18);

            gs.getCave().getBats().add(bat1);
            gs.getCave().getBats().add(bat2);


            JsonWriter writer = new JsonWriter("./data/testReaderGeneralGameState.json");
            writer.open();
            writer.write(gs);
            writer.close();

            new JsonReader("./data/testReaderGeneralGameState.json");
            assertEquals(0, gs.getCreature().getPosX());
            assertEquals(6, gs.getCreature().getPosY());
            assertEquals(768, gs.getCave().getHeight());
            assertEquals(1376, gs.getCave().getWidth());

            assertEquals(4500, gs.getCave().getBatSpawnRate());
            assertEquals(6, gs.getCave().getMaxBats());
            
            assertEquals(14, gs.getCave().getBat(0).getPosX());
            assertEquals(9, gs.getCave().getBat(0).getPosY());

            assertEquals(27, gs.getCave().getBat(1).getPosX());
            assertEquals(18, gs.getCave().getBat(1).getPosY());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}