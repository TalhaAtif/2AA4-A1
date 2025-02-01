package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {

    private int y;
    private int x;

    private Direction startDir;

    private Direction dir;


    private void turn(char rotate) {
        if (rotate == 'L') {
            this.dir = dir.turnLeft();
        } else if (rotate == 'R') {
            this.dir = dir.turnRight();
        }
    }

    private void move(Maze maze) {
        if (this.dir == Direction.NORTH) {
            if (!maze.isWall(this.x, this.y - 1)) {
                this.y -= 1;
            }

        } else if (this.dir == Direction.SOUTH) {
            if (!maze.isWall(this.x, this.y + 1)) {
                this.y += 1;
            }

        } else if (this.dir == Direction.WEST) {
            if (!maze.isWall(this.x - 1, this.y)) {
                this.x -= 1;
            }

        } else if (this.dir == Direction.EAST) {
            if (!maze.isWall(this.x + 1, this.y)) {
                this.x += 1;
            }
        }
    }

    private char get_icon(Direction dir) {
        if (dir == Direction.NORTH) {
            return 'U';
        } else if (dir == Direction.EAST) {
            return 'R';
        } else if (dir == Direction.SOUTH) {
            return 'D';
        } else if (dir == Direction.WEST) {
            return 'L';
        }
        return '-';  // Default 
    }

    public void runPath(String userPath, int startX, int startY, Maze maze) {
        this.startX = startX;

        this.x = startX;
        this.y = startY;

        if (this.startX == 0) {
            this.dir = Direction.EAST;
        } else {
            this.dir = Direction.WEST;
        }

        maze.debug_path(this.x, this.y, get_icon(this.dir));
        for (int i = 0; i < userPath.length(); i++) {
            if (userPath.charAt(i) == 'F') {
                this.move(maze);
            } else {
                turn(userPath.charAt(i));
            }
            maze.debug_path(this.x, this.y, get_icon(this.dir));
        }
        if (maze.isExit(this.x, this.startX)) {
            System.out.println("MAZE PATH SUCCESSFUL");
            System.exit(0);
        } else {
            System.out.println("PATH INCORRECT");
        }
    }
}
