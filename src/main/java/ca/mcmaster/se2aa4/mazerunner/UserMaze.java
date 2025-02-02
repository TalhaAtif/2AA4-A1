package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Logger;

public class UserMaze extends Maze {

    private String path;

    UserMaze(String file, Logger logger, String userPath) {
        super(file, logger);
        this.path = pathString(userPath.replace(" ", ""));
    }

    private String pathString(String path) {
        StringBuilder updatePath = new StringBuilder();
        int i = 0;

        while (i < path.length()) {
            char currentMove = path.charAt(i);

            if (Character.isDigit(currentMove)) {
                StringBuilder number = new StringBuilder();

                while (i < path.length() && Character.isDigit(path.charAt(i))) {
                    number.append(path.charAt(i));
                    i++;
                }

                int amt = Integer.parseInt(number.toString());
                char nextMove = path.charAt(i);

                for (int j = 0; j < amt; j++) {
                    updatePath.append(nextMove);
                }
                i++;
            } else {
                updatePath.append(currentMove);
                i++;
            }
        }
        return updatePath.toString();
    }

    @Override
    public void runPath() {
        Explorer botEast = new Explorer(0, find_enterance(Direction.EAST), Direction.EAST);
        Explorer botWest = new Explorer(this.maze_board[0].length - 1, find_enterance(Direction.WEST), Direction.WEST);

        boolean eastResult = pathFrom(Direction.EAST, botEast);

        boolean westResult = pathFrom(Direction.WEST, botWest);

        if (eastResult || westResult) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }

    private boolean pathFrom(Direction dir, Explorer bot) {
        Direction startDir = dir;
        Direction currDir = startDir;

        //debug_path(bot.getX(), bot.getY(), bot.getDir().icon);
        for (char move : this.path.toCharArray()) {
            bot.addMove(move);
            if (move == 'F') {
                if (!isWall(bot.getX() + currDir.changeX, bot.getY() + currDir.changeY)) {
                    bot.changeBy(currDir.changeX, currDir.changeY);
                }
            } else {
                bot.turn(move);
                currDir = bot.getDir();
            }
            //debug_path(bot.getX(), bot.getY(), bot.getDir().icon);
        }
        bot.printPath();
        if (isExit(bot.getX(), startDir)) {
            return true;
        }
        return false;
    }
}
