package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class AutoMaze extends Maze {

    // Creates maze
    AutoMaze(Logger logger) {
        super(logger);
    }

    // Uses right hand rule to solve and print maze path
    @Override
    public String runPath() {
        // Creates only one bot facing east
        Explorer bot = new Explorer(0, find_enterance(Direction.EAST), Direction.EAST);

        // Until the bot reaches an exit
        while (!isExit(bot.getX(), Direction.EAST)) {
            // Sets direction to the right of bot, and checks for a wall ahead or on the right
            Direction right = bot.getDir().turnRight();
            boolean wallAhead = isWall(bot.getX() + bot.getDir().changeX, bot.getY() + bot.getDir().changeY);
            boolean wallRight = isWall(bot.getX() + right.changeX, bot.getY() + right.changeY);

            // If there is no wall on the right side,
            // turn right and go forward
            if (!wallRight) {
                doCommand(new RightCommand(bot));
                doCommand(new ForwardCommand(bot));
            // If there is a wall on the right and no wall ahead, go forward
            } else if (!wallAhead) {
                doCommand(new ForwardCommand(bot));
            } else {
                // Otherwise keep turning left until there is no wall ahead
                while (isWall(bot.getX() + bot.getDir().changeX, bot.getY() + bot.getDir().changeY)) {
                    doCommand(new LeftCommand(bot));
                }
            }
        }
        // Returns final factorized path
        return bot.printPath();
    }

    // Undoes the last command, if available
    public void undo() {
        if (this.history.isEmpty()) return;

        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}    
