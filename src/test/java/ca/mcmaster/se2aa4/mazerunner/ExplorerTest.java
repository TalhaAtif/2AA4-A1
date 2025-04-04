package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExplorerTest {

    private Explorer atZero;
    private Explorer atThree;
    private Explorer turnTest;

    @BeforeEach
    public void createExplorers() {
        atZero = new Explorer(0, 0, Direction.NORTH);
        atThree = new Explorer(3, 3, Direction.SOUTH);
        turnTest = new Explorer(0, 0, Direction.EAST);
    }

    @Test
    public void basicDirectionTest() {
        assertEquals(Direction.NORTH, atZero.getDir());
        assertEquals(Direction.SOUTH, atThree.getDir());
    }

    @Test
    public void leftTurns() {
        turnTest.turn('L');
        assertEquals(Direction.NORTH, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.WEST, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.SOUTH, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.EAST, turnTest.getDir());
    }

    @Test
    public void rightTurns() {
        turnTest.turn('R');
        assertEquals(Direction.SOUTH, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.WEST, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.NORTH, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.EAST, turnTest.getDir());
    }

    @Test
    public void crazyTurns() {
        turnTest.turn('L');
        assertEquals(Direction.NORTH, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.EAST, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.SOUTH, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.EAST, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.NORTH, turnTest.getDir());
        turnTest.turn('L');
        assertEquals(Direction.WEST, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.NORTH, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.EAST, turnTest.getDir());
        turnTest.turn('R');
        assertEquals(Direction.SOUTH, turnTest.getDir());
    }

    @Test
    public void moveForward() {
        atThree.move();
        assertEquals(3, atThree.getX());
        assertEquals(4, atThree.getY());

        atThree.move();
        assertEquals(3, atThree.getX());
        assertEquals(5, atThree.getY());

        atThree.move();
        assertEquals(3, atThree.getX());
        assertEquals(6, atThree.getY());

        atThree.move();
        assertEquals(3, atThree.getX());
        assertEquals(7, atThree.getY());
    }

    @Test
    public void turnThenForward() {
        atZero.turn('R');
        assertEquals(Direction.EAST, atZero.getDir());
        for (int i = 0; i < 4; i++) {
            atZero.move();
        }
        assertEquals(4, atZero.getX());
        assertEquals(0, atZero.getY());

        atZero.turn('R');
        assertEquals(Direction.SOUTH, atZero.getDir());
        for (int i = 0; i < 4; i++) {
            atZero.move();
        }
        assertEquals(4, atZero.getX());
        assertEquals(4, atZero.getY());

        atZero.turn('R');
        assertEquals(Direction.WEST, atZero.getDir());
        for (int i = 0; i < 4; i++) {
            atZero.move();
        }
        assertEquals(0, atZero.getX());
        assertEquals(4, atZero.getY());

        atZero.turn('R');
        assertEquals(Direction.NORTH, atZero.getDir());
        for (int i = 0; i < 4; i++) {
            atZero.move();
        }
        assertEquals(0, atZero.getX());
        assertEquals(0, atZero.getY());
    }

    @Test
    public void simplePathFactor() {
        atZero.addMove('F');
        atZero.addMove('F');
        atZero.addMove('F');
        atZero.addMove('F');
        atZero.addMove('L');
        atZero.addMove('L');
        assertEquals("4F 2L ", atZero.printPath());
    }

    @Test
    public void advancedPathFactor() {
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('R');
        atThree.addMove('F');
        atThree.addMove('L');
        atThree.addMove('L');
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('F');
        atThree.addMove('R');
        atThree.addMove('R');
        atThree.addMove('F');
        atThree.addMove('F');
        assertEquals("3F R F 2L 5F 2R 2F ", atThree.printPath());
    }

}
