/*
 *  ListCommand.java
 *  Defines the 'List' action flow to list tasks.
 */
public class ListCommand extends Command{
    /*
     *  Constructs List Command object.
     */
    public ListCommand(){

    }

    /*
     * This method 'Executes' the command and
     *  display all tasks in the task list.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println("\tHere are the tasks in your list:");
        //print all tasks in task list
        for (int i = 1; i < tasks.getTaskSize() + 1; i++) {
            Task task = tasks.getTasklist().get(i - 1);
            System.out.println("\t"+String.format(i + "." + task, task.getStatusIcon()));
        }
    }

    /*
     * Returns not to exit program.
     * @return false Command 'Duke' not to exit
     * after list command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
