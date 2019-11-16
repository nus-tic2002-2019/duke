/*
 *  AddCommand.java
 *  Defines the 'Add' action flow to insert tasks.
 */

public class AddCommand extends Command {
    protected Task task;

    /*
     * Constructs Add Command object with parsed task.
     * @param Parsed task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /*
     * This method 'Executes' the command and display message
     * after successfully inserting task.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //add task into task list
        tasks.addTask(task);
        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getTaskSize() + " tasks in the list.");

    }

    /*
     * Returns not to exit program.
     * @return false Command 'Duke' not to exit
     * after add command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
