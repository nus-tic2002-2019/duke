/*
 *  DoneCommand.java
 *  Defines the 'Done' action flow to mark tasks
 *  as completed or done.
 */

public class DoneCommand extends Command{
    private int index;

    /*
     * Constructs Done Command object with task index.
     * @param Parsed task index.
     */
    public DoneCommand(int index){
        this.index = index;
    }

    /*
     * This method 'Executes' the command and display message
     * after successfully marking task as completed.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //mark task as completed
        Task task = tasks.doneTask(index-1);
        //display successful message
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
    }

    /*
     * Returns not to exit program.
     * @return false Command 'Duke' not to exit
     * after done command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
