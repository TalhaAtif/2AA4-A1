package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Starting Maze Runner...");
        try {
            logger.info(" Trying to read maze file...");
            if ((args[0]).equals("-i")) {

            }

            MazeRunner mazeRunner = new MazeRunner(args[1], logger);

        } catch (Exception e) {
            logger.error("Error.", e);
        }

        logger.info("** End of MazeRunner");
    }
}

class MazeRunner {

    private Maze maze;
    private Explorer bot;

    MazeRunner(String file, Logger logger) {
        this.bot = new Explorer();
        this.maze = new Maze(file, logger);
    }

}

class Explorer {

    enum Direction {
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

    private int y;
    private int x;
    private int startX;
    private Direction dir;

    private void turn(char rotate) {
        if (rotate == 'L') {
            this.dir = dir.prev();
        } else if (rotate == 'R') {
            this.dir = dir.next();
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

        System.out.println("\nRUNNING PATH FROM " + startX + " " + startY + "\n");
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

class Maze {

    enum Maze_type {
        WALL, PATH
    }

    private boolean[][] maze_board;
    private Logger logger;

    public Maze(String file, Logger logger) {
        this.maze_board = getSize(file);
        this.logger = logger;
        create_board(file, logger);
        debug_maze();
    }

    private boolean[][] getSize(String file) {
        int rows = 0;
        int cols = 0;
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = r.readLine()) != null) {
                cols = Math.max(line.length(), cols);
                rows++;
            }

            r.close();
            return new boolean[rows][cols];

        } catch (Exception e) {
            System.out.println("Error reading maze from file.");
        }
        return new boolean[0][0];
    }

    public void create_board(String file, Logger logger) {
        
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String line;

            for (int row = 0; row < this.maze_board.length; row++) {
                line = r.readLine();
                for (int col = 0; col < this.maze_board[0].length; col++) {
                    if ((col < line.length()) && (line.charAt(col) == '#')) {
                        set_piece(col, row, true);
                    } else {
                        set_piece(col, row, false);
                    }
                }
            }

            r.close();
        } catch (Exception e) {
            logger.error("Error while reading the maze file", e);
        }
    }

    private void debug_maze() {
        for (int i = 0; i < this.maze_board.length; i++) {
            for (int j = 0; j < this.maze_board[0].length; j++) {
                System.out.print(maze_board[i][j] ? "[#]" : "[ ]");
            }
            System.out.println();
        }
    }

    public void debug_path(int x, int y, char bot) {

        char curr = ' ';

        for (int i = 0; i < this.maze_board.length; i++) {
            for (int j = 0; j < this.maze_board[0].length; j++) {
                if (this.maze_board[i][j]) {
                    curr = '#';
                } else {
                    if (!this.maze_board[i][j]) {
                        curr = ' ';
                    }
                    if ((i == y) && (j == x)) {
                        curr = bot;
                    }
                }
                System.out.print(curr);
            }
            System.out.println();
        }
        System.out.println();

    }

    public boolean isWall(int x, int y) {
        if (x >= 0 && x < this.maze_board.length) {
            if (y >= 0 && y < this.maze_board[0].length) {
                return (this.maze_board[y][x]);
            }
            return true;
        } else {
            return true;
        }
    }

    public boolean isExit(int x, int startX) {
        if (startX == 0) {
            if (x == this.maze_board.length - 1) {
                return true;
            }
        } else {
            if (x == 0) {
                return true;
            }
        }
        return false;
    }

    public void tryPaths(Explorer bot, String userPath) {
        for (int i = 0; i < this.maze_board.length; i++) {
            if (!maze_board[i][0]) {
                bot.runPath(userPath, 0, i, this);
            }
            if (!maze_board[i][this.maze_board[0].length - 1]) {
                bot.runPath(userPath, this.maze_board[0].length - 1, i, this);
            }
        }
    }

    private void set_piece(int x, int y, boolean wall) {
        this.maze_board[y][x] = wall;
    }

}
