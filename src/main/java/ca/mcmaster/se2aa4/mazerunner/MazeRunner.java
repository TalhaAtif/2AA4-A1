package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private Boolean userPath = false;
    private Logger logger;
    private String filePath = " ";
    private String[] args;

    MazeRunner(String[] args, Logger logger) {
        if (args.length == 4) {
            this.userPath = true;
        }
        this.filePath = args[1];
        this.logger = logger;
        this.args = args;
    }

    public void run() {
        Maze maze;

        if (this.userPath) {
            maze = new UserMaze(this.filePath,this.logger,this.args[3]);
        }
        else {
            maze = new AutoMaze(this.filePath,this.logger);
        }

        maze.runPath();
    }

}
