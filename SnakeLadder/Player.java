package SnakeLadder;

public class Player {
    String name;
    int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.name;
    }
}