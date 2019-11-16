/*
 *  ViewCommand.java
 *  Defines the 'View' action to view
 *  schedule for a specific date.
 */
public class ViewCommand extends Command{
    private String keyWord;
    /*
     *  Constructs View Command object.
     */
    public ViewCommand(String keyWord){
        this.keyWord = keyWord;
    }

    /*
     * This method 'Executes' the command and
     *  display searched tasks in the task list.
     * @param tasks Object of Task class.
     * @param ui Object of UI class - User Interface.
     * @param storage Object of Storage class.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println("\tHere are the matching tasks in your list:");
        //print all tasks in task list
        for (int i = 1; i < tasks.getTaskSize() + 1; i++) {
            Task task = tasks.getTaskList().get(i - 1);
            String dateTime = "";
            if(task.getTaskType()=='E'){
                Event event;
                event = (Event) tasks.getTaskList().get(i - 1);
                dateTime = event.getLast();
            }
            if(task.getTaskType()=='D'){
                Deadline deadline;
                deadline = (Deadline) tasks.getTaskList().get(i - 1);
                dateTime = deadline.getLast();
            }
            if(dateTime.contains(keyWord)){
                System.out.println("\t"+String.format(i + "." + task, task.getStatusIcon()));
            }
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
