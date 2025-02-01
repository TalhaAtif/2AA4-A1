package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0);

    final int changeX;
    final int changeY;

    Direction(int changeX, int changeY) {
        this.changeX = changeX;
        this.changeY = changeY;
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

}
