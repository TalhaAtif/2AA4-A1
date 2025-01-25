package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Logger logger2 = LogManager.getLogger("TraceLogger");

    public static void main(String[] args) {
        System.out.println("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file ");
            if ((args[0]).equals("-i")) {
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger2.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger2.trace("PASS ");
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
    //
}
