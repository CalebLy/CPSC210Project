package persistence;

import model.characters.*;
import model.items.*;
import model.rooms.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkThingy(String name, Category category, Thingy thingy) {
        assertEquals(name, thingy.getName());
        assertEquals(category, thingy.getCategory());
    }
}