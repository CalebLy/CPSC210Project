package model;

import org.json.JSONArray;
import org.json.JSONObject;

import model.characters.*;
import model.items.*;
import model.rooms.*;

public class GameState {
    

        public GameState(String name) {

        }

        // Data persistence
        @Override
        public JSONObject toJson() {
            JSONObject json = new JSONObject();
            return json;
            //stub
        }
    
}
