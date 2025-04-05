package ca.mcmaster.se2aa4.mazerunner;

public class RightCommand extends Command {

    private Explorer bot;

    public RightCommand(Explorer bot) {
        super(bot);
        this.bot = bot;
    }

    @Override
    public void action() {
        saveState();
        bot.turn('R');
        bot.addMove('R');
    }
}
