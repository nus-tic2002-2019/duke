package main.java.duke.commands;

/**
 * Exit the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD="bye";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": Exit the program and save the changes to the txt file.";

    @Override
    public void execute(){
        System.out.print("See you next time.\n");
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
