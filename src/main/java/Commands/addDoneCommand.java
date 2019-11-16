package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class addDoneCommand extends Command{

    public static final String COMMAND = "done";


    /**
     * Constructor for Done Command
     * @param idx
     */
    public addDoneCommand(int idx){
        super(idx);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws StringIndexOutOfBoundsException {
        ui.newDone();
        tasklist.getTask(idx).markAsDone();
        System.out.println("       " + tasklist.getTask(idx).getDescription());
        ui.Line();
    }
}
