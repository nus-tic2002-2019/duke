/*
 *  ExitCommand.java
 *  Defines the 'Exit' action flow to exit program.
 */

public class ExitCommand extends Command{
    /*
     *  Constructs Exit Command object.
     */
    public ExitCommand(){

    }

    /*
     * This method 'Executes' the command and
     *  display 'Goodbye' message.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //save and store all tasks.
        storage.save(tasks.getTaskList());
        //display goodbye message
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /*
     * Returns to exit program.
     * @return false Command 'Duke' not to exit
     * after exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
