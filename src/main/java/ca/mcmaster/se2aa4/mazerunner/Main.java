package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Starting Maze Runner...");

        if ((args.length != 2) && (args.length != 4)) {
            System.out.println("Input must be [java -jar <program location> -i <\"file path\">] with optional [ -p <\"path\">]");
        }
        else {
        MazeRunner tryMaze = new MazeRunner(args, logger);
        tryMaze.run();
        }
    }
}
