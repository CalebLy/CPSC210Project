package persistence;

import model.GameState;
import model.characters.*;
import model.rooms.*;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private Creature creature;
    private Cave cave;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        creature = parseCreature(jsonObject.getJSONObject("creature"));
        cave = parseCave(jsonObject.getJSONObject("cave"));
        GameState gs = new GameState(creature, cave);
        return gs;
    }

    // Modifies: creature
    // Effects: parses all data related to creature from JSON object and changes creature variables accordingly
    private Creature parseCreature(JSONObject jsonObject) {
        int posX = jsonObject.getInt("x");
        int posY = jsonObject.getInt("y");
        creature = new Creature(posX, posY);
        creature.setAttackCooldownTime(jsonObject.getLong("attackCooldownTime"));
        creature.setAttackCooldown(jsonObject.getBoolean("attackCooldown"));
        creature.setAbilityToAttack(jsonObject.getBoolean("abilityToAttack"));
        creature.getInventory().getBatwings().setBatwing(jsonObject.getInt("batwings"));
        return creature;
    }

    // Modifies: cave
    // Effects: parses all data related to cave from JSON object and changes cave variables accordingly
    private Cave parseCave(JSONObject jsonObject) {
        int width = jsonObject.getInt("width");
        int height = jsonObject.getInt("height");
        cave = new Cave(width, height);
        addBats(cave, jsonObject);
        cave.setMaxBats(jsonObject.getInt("maxBats"));
        cave.setBatSpawnRate(jsonObject.getLong("batSpawnRate"));
        return cave;
    }

    private void addBats(Cave cave, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Bats");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject batJson = jsonArray.getJSONObject(i); 
            int x = batJson.getInt("x");
            int y = batJson.getInt("y");
            Bats bat = new Bats(x, y); 
            cave.getBats().add(bat);
        }
    }


}
