package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction next() {
        Direction[] vals = Direction.values();
        int i = this.ordinal();
        int next = (i + 1) % vals.length;
        return vals[next];
    }

    public Direction prev() {
        Direction[] vals = Direction.values();
        int i = this.ordinal();
        int prev = (i - 1 + vals.length) % vals.length;
        return vals[prev];
    }
}
