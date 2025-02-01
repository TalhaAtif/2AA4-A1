package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private Maze maze;
    private Explorer bot;
    private Boolean userPath;

    MazeRunner(String file, Logger logger, Boolean userPath) {
        this.userPath = userPath;
    }
}
