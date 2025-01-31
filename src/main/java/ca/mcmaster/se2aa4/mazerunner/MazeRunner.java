package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private Maze maze;
    private Explorer bot;

    MazeRunner(String file, Logger logger) {
        this.bot = new Explorer();
        this.maze = new Maze(file, logger);
    }
}
