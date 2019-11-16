/*
 *  UpdateCommand.java
 *  Defines the 'update' action flow to update
 *  Date and Time of Event or Deadline Object.
 */

public class UpdateCommand extends Command{
    private int index;
    private String datetime;

    /*
     * Constructs Update Command object with task index.
     * @param index Parsed task index.
     * @param datetime Updated data and time
     */
    public UpdateCommand(int index,String datetime){
        this.index = index;
        this.datetime = datetime;
    }

    /*
     * This method 'Executes' the command and display message
     * after successfully updated date and time.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //mark task as completed
        Task task = tasks.updateTask(index-1,datetime);
        //display successful message
        System.out.println("\tNice! I've updated this task:");
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
