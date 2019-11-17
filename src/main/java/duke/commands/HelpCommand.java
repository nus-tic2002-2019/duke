package main.java.duke.commands;



public class HelpCommand extends Command {
    public static final String COMMAND_WORD="help";

    public static final String MESSAGE_USAGE = "||"+COMMAND_WORD + ": Shows program usage instructions.";

    @Override
    public void execute(){

        System.out.println(AddCommand.MESSAGE_USAGE
                + "\n" + ClearCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + DoneCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + ViewDoneCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE);
    }
}
