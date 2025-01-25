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

    private int start_x;
    private int start_y;
    private int y;
    private int x;
    private Direction dir;

    public Explorer(int start_x, int start_y) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.y = this.start_y;
        this.x = this.start_x;
    }

    public void turn(Direction direction) {
        this.dir = direction;
    }

    public void move(int spaces) {
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
