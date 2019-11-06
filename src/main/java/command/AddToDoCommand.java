package command;

import task.Task;
import task.Todo;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

//break this from AddCommand so as to increase coupling
public class AddToDoCommand extends Command{

    public static final String COMMAND_WORD = "todo";

    /**
     * Constructor for description and priority
     *
     * @param description and priority
     */
    public AddToDoCommand(String description, int priority) {
        super(description, priority);
    }

    // do you really need to throw errors? or just inherit directly from the abstract class
    /**
     * To add todo Task to the TaskList
     *
     * @param task
     * @param ui
     * @param storage
     */
    public void execute(TaskList task, Ui ui, Storage storage){
                Task insert = new Todo(description, priority);
                task.addTask(insert);
                ui.showAdd(insert, task);
        }
}

