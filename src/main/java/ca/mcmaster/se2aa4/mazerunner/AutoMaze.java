package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class AutoMaze extends Maze {

    AutoMaze(String file, Logger logger) {
        super(file, logger);
    }

    @Override
    public void runPath() {
        Explorer bot = new Explorer(0, find_enterance(Direction.EAST), Direction.EAST);

        //debug_path(bot.getX(), bot.getY(), bot.getDir().icon);

        while (!isExit(bot.getX(), Direction.EAST)) {
            Direction right = bot.getDir().turnRight();
            boolean wallAhead = isWall(bot.getX() + bot.getDir().changeX, bot.getY() + bot.getDir().changeY);
            boolean wallRight = isWall(bot.getX() + right.changeX, bot.getY() + right.changeY);

            if (!wallRight) {
                bot.turn('R');
                bot.addMove('R');
                bot.changeBy(bot.getDir().changeX, bot.getDir().changeY);
                bot.addMove('F');
            } else if (!wallAhead) {
                bot.changeBy(bot.getDir().changeX, bot.getDir().changeY);
                bot.addMove('F');
            } else {
                while (wallAhead) {
                    bot.turn('L');
                    bot.addMove('L');
                    wallAhead = isWall(bot.getX() + bot.getDir().changeX, bot.getY() + bot.getDir().changeY);
                }
            }
            //debug_path(bot.getX(), bot.getY(), bot.getDir().icon);
        }
        //Prints final factorized path
        bot.printPath();
    }

}
