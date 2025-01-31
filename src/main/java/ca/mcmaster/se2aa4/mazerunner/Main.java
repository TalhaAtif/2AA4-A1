package ca.mcmaster.se2aa4.mazerunner;

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

