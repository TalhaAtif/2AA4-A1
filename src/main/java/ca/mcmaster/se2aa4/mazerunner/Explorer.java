package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {

    private List<Character> moves;

    private Direction startDir;

    private Direction dir;
    private int x;
    private int y;

    Explorer(int x, int y, Direction startD) {
        this.startDir = startD;
        this.dir = startD;
        this.y = y;
        this.x = x;
        this.moves = new ArrayList<>();
    }

    public void turn(char rotate) {
        if (rotate == 'L') {
            this.dir = dir.turnLeft();
        } else if (rotate == 'R') {
            this.dir = dir.turnRight();
        }
    }

    public Direction getDir() {
        return this.dir;
    }

    public void addMove(char currentMove) {
        this.moves.add(currentMove);
    }

    public Direction getStart() {
        return this.startDir;
    }

    public void printPath() {
        this.moves.add('E');

        int numOfReps = 1;
        char lastLetter = this.moves.get(0);

        for (int i = 1; i < this.moves.size(); i++) {
            char move = this.moves.get(i);

            if (move == lastLetter) {
                numOfReps++;
            } else {
                if (numOfReps > 1) {
                    System.out.print(numOfReps);
                }
                System.out.print(lastLetter + " ");
                
                numOfReps = 1;
                lastLetter = move;
            }
        }
    }

    public void changeBy(int xNew, int yNew) {
        this.x+= xNew;
        this.y+= yNew;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
