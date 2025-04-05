package ca.mcmaster.se2aa4.mazerunner;

public class ReadTurnCommand extends Command {

    private char rotation;

    public ReadTurnCommand(Explorer bot, char rotation) {
        super(bot);
        this.rotation = rotation;
    }

    @Override
    public void action() {
        saveState();
        if ( this.rotation == 'L') {
            bot.turn('L');
            bot.addMove('L');
        } else if (this.rotation == 'R') {
            bot.turn('R');
            bot.addMove('R');
        }
    }
}
