package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserMazeTest {

    private final Logger logger;

    public UserMazeTest() {
        this.logger = LogManager.getLogger();
    }

    @Test
    public void testStraightMaze() {
        UserMaze maze = new UserMaze("examples/straight.maz.txt", this.logger, "FFFF");
        String result = maze.runPath();
        assertEquals("correct path", result); 
    }

    @Test
    public void testTinyMazeRight() {
        UserMaze maze = new UserMaze("examples/tiny.maz.txt", this.logger, "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F");
        String result = maze.runPath();
        assertEquals("correct path", result); 
    }

    
    @Test
    public void testTinyMazeWrong() {
        UserMaze maze = new UserMaze("examples/tiny.maz.txt", this.logger, "5F 2L 2F R 2F R 2F 2L 2F R 2F R 2F");
        String result = maze.runPath();
        assertEquals("incorrect path", result); 
    }
}
