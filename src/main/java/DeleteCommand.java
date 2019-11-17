/*
 *  DeleteCommand.java
 *  Defines the 'Delete' action flow to delete tasks.
 */

public class DeleteCommand extends Command{
    private int index;

    /*
     * Constructs Delete Command object with parsed task.
     * @param Parsed task.
     */
    public DeleteCommand(int index){
        this.index = index;
    }

    /*
     * This method 'Executes' the command and display message
     * after successfully deleting task.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //delete task from task list
        Task task = tasks.deleteTask(index-1);
        //display successful message and task count
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getTaskSize() + " tasks in the list.");

    }

    /*
     * Returns not to exit program.
     * @return false Command 'Duke' not to exit
     * after delete command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
