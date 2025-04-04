package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {

    //List of moves completed by bot
    private List<Character> moves;

    //Direction the bot was facing when it started
    private Direction startDir;

    //current direction and location
    private Direction dir;
    private int x;
    private int y;

    //Sets the initial direction and location, creates array list for moves 
    Explorer(int x, int y, Direction startD) {
        this.startDir = startD;
        this.dir = startD;
        this.y = y;
        this.x = x;
        this.moves = new ArrayList<>();
    }

    //Turns bot and updates direction
    public void turn(char rotate) {
        if (rotate == 'L') {
            this.dir = dir.turnLeft();
        } else if (rotate == 'R') {
            this.dir = dir.turnRight();
        }
    }

    //Returns direction
    public Direction getDir() {
        return this.dir;
    }

    //Adds completed move to moves list
    public void addMove(char currentMove) {
        this.moves.add(currentMove);
    }

    //Gets starting direction
    public Direction getStart() {
        return this.startDir;
    }

    //Moves bot 1 spot ahead
    public void move() {
        this.x += dir.changeX;
        this.y += dir.changeY;
    }

    //Prints the path from the moves list in factored form
    public String printPath() {
        StringBuffer fullPath = new StringBuffer();
        this.moves.add('E');

        int numOfReps = 1;
        char lastLetter = this.moves.get(0);

        for (int i = 1; i < this.moves.size(); i++) {
            char move = this.moves.get(i);

            if (move == lastLetter) {
                numOfReps++;
            } else {
                if (numOfReps > 1) {
                    fullPath.append(numOfReps);
                }
                fullPath.append(lastLetter + " ");
                
                numOfReps = 1;
                lastLetter = move;
            }
        }

        return fullPath.toString();
    }

    //Returns x value of bot location
    public int getX() {
        return this.x;
    }

    //Returns y value of bot location
    public int getY() {
        return this.y;
    }
}
