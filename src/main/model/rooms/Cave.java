package model.rooms;

import model.GameObject;

public class Cave extends GameObject{
    private int width;
    private int height;

    public Cave(String name, int x, int y, int width, int height) {
        super(name, x, y);
        this.width = width;
        this.height = height;
    }

    // Effects: Checks to see if GameObject's (xNow, yNow) are within the bounds of the cave
    public boolean isWithinBounds(int xNow, int yNow) {
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
}
