package model.rooms;

import model.GameObject;

public class Cave extends GameObject{
    private int width;
    private int height;

    // Effects: Creates a Cave named Bat Cave with dimensions (width-x) x (height-y)
    public Cave(int x, int y, int width, int height) {
        super("Bat Cave", x, y);
        this.width = width;
        this.height = height;
    }

    // Effects: Checks to see if GameObject's (xNew, yNew) are within the bounds of the cave
    public boolean isWithinBounds(int xNew, int yNew) {
        // stub
        return true;
    }
    

    // Getter methods
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Effects: Removes cave when user leaves.
    public void removeObject() {
        this.setIsActive(false);
    }
}
