package ca.mcmaster.se2aa4.mazerunner;

public class ForwardCommand extends Command {


    public ForwardCommand(Explorer bot) {
        super(bot);
    }

    @Override
    public void action() {
        saveState();
        bot.move();
        bot.addMove('F');
    }
}
