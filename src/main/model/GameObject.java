package model;

// Base class for all objects: Characters, Items, Rooms
public abstract class GameObject {

    // Fields
    private String name; // Name of object
    protected int posX; // x coordinate of object
    protected int posY; // y coordinate of object
    private boolean isActive; // Is object on the screen
    
    // Requires: coordinate (x,y) must be within the bounds
    // Effects: Creates a Game Object with "name", set at coordinates (x,y), and set to not active.
    public GameObject(String name, int x, int y) {
        this.name = name;
        this.posX = x;
        this.posY = y;
        this.setIsActive(true);
    }

    public GameObject() {
        this.setIsActive(true);
    }


    // Getter Methods
    public String getName() { 
        return name;
    }

    public int getPosX() { 
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean getIsActive() {
        return isActive;
    }

    // Setter methods
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setIsActive(boolean activity) {
        this.isActive = activity;
    }

}
