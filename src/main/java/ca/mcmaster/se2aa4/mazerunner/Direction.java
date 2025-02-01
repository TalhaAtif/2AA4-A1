package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH(0,1, 'U'),
    EAST(1,0, 'R'),
    SOUTH(0,-1, 'D'),
    WEST(-1,0,'L');

    final int changeX;
    final int changeY;
    final char icon;

    Direction(int changeX, int changeY, char icon) {
        this.changeX = changeX;
        this.changeY = changeY;
        this.icon = icon;
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

}
