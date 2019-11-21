package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import Ui.Ui;

public class addDeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private String description;


    /**
     * Constructor for Delete Command
     * @param idx
     * @param des
     */
    public addDeleteCommand(int idx, String des){
        super(idx);
        this.description = des;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        String taskB4Del = description;
        tasklist.deleteTask(idx);
        ui.Line();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskB4Del);
        System.out.println("     Now you have " + tasklist.getSize() + " tasks in the list.");
        ui.Line();
    }
}
