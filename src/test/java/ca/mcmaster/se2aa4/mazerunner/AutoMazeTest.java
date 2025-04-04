package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AutoMazeTest {

    private Logger logger;

    @BeforeEach
    public void setUp() throws IOException {
        this.logger = LogManager.getLogger();
    }

    @Test
    public void mazeDirectTest() {
        AutoMaze maze = new AutoMaze(logger);
        String result = maze.perform("examples/direct.maz.txt");
        assertEquals("F R 2F L 3F R F L F R F L 2F ", result);
    }

    @Test
    public void mazeSmallTest() {
        AutoMaze maze = new AutoMaze(logger);
        String result = maze.perform("examples/small.maz.txt");
        assertEquals("F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F ", result);
    }

    @Test
    public void mazeMediumTest() {
        AutoMaze maze = new AutoMaze(logger);
        String result = maze.perform("examples/medium.maz.txt");
        assertEquals("F R 6F 2L 8F R 2F R 2F 2L 2F R 2F R 4F R 2F L 4F L 2F 2L 2F R 4F R 2F L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F 2L 2F R 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 10F R 2F R 8F 2L 8F L 2F R 4F R 2F R 2F 2L 2F R 2F R 14F 2L 12F R 2F R 6F 2L 4F R 2F R 6F R 2F L 6F 2L 6F R 2F R 8F 2L 12F R 2F R 10F 2L 6F R 2F R 4F 2L 4F L 2F R 4F L 2F R 2F L 2F R 2F L 2F R 2F L 4F R 2F R 2F 2L 4F R 2F R 6F R 2F 2L 2F R 2F R 4F 2L 2F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F R 6F L 2F R 8F 2L 8F R 2F R 10F R 4F R 2F 2L 2F R 2F 2L 2F R 2F R 2F L 4F R 2F 2L 4F 2L 2F R 4F R 2F R 2F 2L 4F R 2F R 6F 2L 6F R 4F R 2F R 2F L 2F 2L 2F R 4F R 2F R 2F 2L 2F R 2F R 4F 2L 4F L 4F R 2F R 4F 2L 2F R 2F 2L 2F R 2F R 2F 2L 6F R 2F R 8F R 6F R 2F 2L 2F L 2F R F ", result);
    }

    @Test
    public void mazeRectangleTest() {
        AutoMaze maze = new AutoMaze(logger);
        String result = maze.perform("examples/rectangle.maz.txt");
        assertEquals("F R 7F L 4F 2L 4F R 2F R 2F 2L 2F R 16F R 2F R 14F 2L 4F R 2F R 6F 2L 4F R 2F R 6F 2L 2F R 2F R 2F L 2F 2L 2F R 2F L 2F R 4F R 2F R 2F 2L 2F R 2F R 4F 2L 4F R 2F R 6F 2L 6F R 2F R 6F 2L 4F R 2F R 4F 2L 2F R 2F R 2F L 2F 2L 2F R 2F L 2F R 2F L 2F R 2F R 4F R 2F L 2F R 2F 2L 2F R 2F R 2F 2L 2F L 4F R 2F L 8F R 2F 2L 2F R 6F R 2F R 2F 2L 4F R 6F R 2F R 4F 2L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 4F L 4F R 2F R 6F R 4F 2L 4F R 2F R 6F L 2F 2L 2F R 2F R 4F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 4F R 2F R 4F 2L 4F R 2F R 10F R 2F 2L 6F 2L 4F R 8F R 2F R 6F 2L 4F R 2F R 4F 2L 2F R 2F R 4F L 6F 2L 6F R 2F R 4F 2L 4F R 2F R 4F 2L 6F R 2F R 4F 2L 6F R 2F R 2F 2L 4F R 2F R 2F L 2F R 2F R 2F L 2F R 2F 2L 2F L 2F R 2F L 2F L 2F R 2F R 2F 2L 2F R 6F R 4F R 2F R 2F L 2F 2L 2F R 2F L 2F L 2F R 2F 2L 2F R 2F R 6F R 4F R 2F R 2F 2L 2F L 4F R 2F R 4F L 2F R 2F R 4F 2L 2F R 4F 2L 4F R 2F R 6F R 4F 2L 4F R 2F R 4F L 6F R 2F L 2F R 4F 2L 4F R 2F R 6F 2L 2F R 2F R 6F 2L 2F R 2F R 4F 2L 2F R 2F R 2F 2L 2F L 2F R 2F L 2F R 4F R 2F R 2F 2L 2F R 2F R 4F 2L 4F R 2F R 6F 2L 6F R 2F R 8F R 2F 2L 2F L 6F R 2F R 6F L 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F L 2F R 2F R 2F 2L 2F R 2F R 4F R 4F 2L 3F R F ", result);
    }
}
