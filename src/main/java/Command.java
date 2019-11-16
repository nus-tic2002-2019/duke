/*
 *  Command.java
 *  Defines the abstract Command class that all commands have the following method.
 */
public abstract class Command {
    /*
     * This method 'Executes' the command.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    /*
     * Returns to or not to exit program.
     * @return Command 'Duke' to or not to exit
     */
    public abstract boolean isExit();
}