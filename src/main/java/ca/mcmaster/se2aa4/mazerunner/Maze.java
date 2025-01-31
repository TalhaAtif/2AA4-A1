package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.Logger;

public class Maze {

    enum Maze_type {
        WALL, PATH
    }

    private boolean[][] maze_board;
    private Logger logger;

    public Maze(String file, Logger logger) {
        this.maze_board = getSize(file);
        this.logger = logger;
        create_maze(file, logger);
        print_maze();
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

    public void create_maze(String file, Logger logger) {

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

    private void print_maze() {
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
