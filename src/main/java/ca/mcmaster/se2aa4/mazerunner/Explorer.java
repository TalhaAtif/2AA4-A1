package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;

public class Explorer {

    private int y;
    private int x;

    private List<Character> moves;

    private Direction startDir;

    private Direction dir;

    Explorer(int x, int y, Direction startD) {
        this.x = x;
        this.y= y;
        this.startDir = startD;
        this.moves = new ArrayList<>();
    }

    public void turn(char rotate) {
        if (rotate == 'L') {
            this.dir = dir.turnLeft();
        } else if (rotate == 'R') {
            this.dir = dir.turnRight();
        }
    }

    public void addMove(char currentMove) {
        this.moves.add(currentMove);
    }


    
}
