package ca.mcmaster.se2aa4.mazerunner;

public class LeftCommand extends Command {


    public LeftCommand(Explorer bot) {
        super(bot);
    }

    @Override
    public void action() {
        saveState();
        bot.turn('L');
        bot.addMove('L');
    }
}
