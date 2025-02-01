package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private Boolean userPath = false;
    private Logger logger;

    MazeRunner(String[] args, Logger logger) {
        if (args.length == 3) {
            this.userPath = true;
        }
        this.logger = logger;
    }

    public void run() {

    }

}
