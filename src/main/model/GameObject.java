package model;

// Base class for all objects: Characters, Items, Rooms
public class GameObject {

    // Fields
    private String name; // Name of object
    private int x; // x coordinate of object
    private int y; // y coordinate of object
    private boolean isActive; // Is object on the screen
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates a Game Object with "name", set at coordinates (x,y), and set to not active.
    public GameObject(String name, int x, int y) {
        // stub
    }


    // Requires: coordinate (x,y) must be within the bounds
    // Modifies: this
    // Effects: Sets the position of this(object) to (x,y)
    public void setPosition(int x, int y) {
        // stub
    }




    // Modifies: this
    // Effects: Visually removes a Game Object
    public void removeObject() {
        this.isActive = false;
    }

    // Getter Methods
    public String getName() { 
        return name;
    }

    public int getX() { 
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getIsActive() {
        return isActive;
    }

}
