package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {

    protected  Explorer bot;

    private int saveX;
    private int saveY;
    private Direction saveDir;

    Command (Explorer bot) {
        this.bot = bot;
    }

    public void saveState() {
        this.saveX = bot.getX();
        this.saveY = bot.getY();
        this.saveDir = bot.getDir();
    }

    public void undo() {
        bot.overrideState(this.saveX, this.saveY, this.saveDir);
    }

    public abstract void action();
}