package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void push(Command action) {
        this.history.push(action);
    }

    public Command pop() {
        return this.history.pop();
    }

    public boolean isEmpty() {
        return this.history.isEmpty();
    }
}
