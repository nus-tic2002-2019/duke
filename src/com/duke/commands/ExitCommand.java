package com.duke.commands;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD="bye";


    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
