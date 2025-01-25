package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Logger logger2 = LogManager.getLogger("TraceLogger");

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file ");
            if ((args[0]).equals("-i")) {
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));

                String line = reader.readLine();
                //Get maze size
                int rows = 0;
                int columns = 0;
                if (line != null) {
                    columns = line.length();
                    rows++;

                    while ((line = reader.readLine()) != null) {
                        rows++;
                    }
                }
                Maze current_maze = new Maze(columns, rows);

                reader.close();
                reader = new BufferedReader(new FileReader(args[1]));

                current_maze.create_board(reader, logger);
                current_maze.run_path(args[3]);

            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}

class Explorer {
    
    enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    private int y;
    private int x;
    private Direction dir;

    private void turn(Direction direction) {
        this.dir = direction;
    }

    private void move(int spaces) {
        if (this.dir == Direction.NORTH) {
            this.y += 1;
        } else if (this.dir == Direction.SOUTH) {
            this.y -= 1;
        } else if (this.dir == Direction.WEST) {
            this.x -= 1;
        } else if (this.dir == Direction.EAST) {
            this.x += 1;
        }
    }

    private char get_icon(Direction dir) {
        return '-';
    }


    public void runPath(String userPath, int startX, int startY) {

    }
}

class Maze {

    //Will be used in final version
    enum Maze_type {
        WALL, PATH
    }

    private boolean[][] maze_board;

    public Maze(int width, int height) {
        maze_board = new boolean[width][height];
    }

    public void debug_maze() {
        for (int i = 0; i < this.maze_board.length; i++) {
            for (int j = 0; j < this.maze_board[0].length; j++) {
                System.out.print(maze_board[i][j] ? "#" : " ");
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
                }
                else {
                    if (!this.maze_board[i][j]) {
                        curr = ' ';
                    }
                    else if ((i == x) && (j == y)) {
                        curr = bot;
                    }
                }
                System.out.println(curr);
            }
            System.out.println();
        }

    }

    public boolean isWall(int x, int y) {
        return (this.maze_board[y][x]);
    }
    
    public void tryPaths(Explorer bot, String userPath) {
        for (int i = 0; i < this.maze_board.length; i++) {
            if (!maze_board[i][0]) {
                bot.runPath(userPath, i, 0);
            }
            if (!maze_board[i][this.maze_board.length-1]) {
                bot.runPath(userPath, i, this.maze_board.length-1);
            }
        }
    }

    private void set_piece(int x, int y, boolean wall) {
        maze_board[y][x] = wall;
    }

    public void create_board(BufferedReader maze_file, Logger logger) {
        try {
            String line;
            for (int row = 0; row < this.maze_board.length; row++) {
                line = maze_file.readLine();
                for (int col = 0; col < this.maze_board[0].length; col++) {
                    if (line.charAt(col) == '#') {
                        set_piece(col, row, true);
                    } else {
                        set_piece(col, row, false);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while reading the maze file", e);
        }
        debug_maze();
    }
}
