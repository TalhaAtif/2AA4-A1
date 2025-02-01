package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class UserMaze extends Maze{

    private String path;

    UserMaze(String file, Logger logger, String userPath) {
        super(file, logger);
        this.path = userPath.replace(" ","");
    }

    @Override
    public void runPath() {
        for (char move : this.path.toCharArray()) {
            
        }
    }

    
}
