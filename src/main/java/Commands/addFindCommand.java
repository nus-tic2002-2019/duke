package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import Ui.Ui;

public class addFindCommand extends Command {
    public static final String COMMAND = "find";


    /**
     * Constructor for Find Command
     */
    public addFindCommand(String description){
        super(description);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        String details = "";
        ui.Line();
        for(int i = 0; i < tasklist.getSize(); i++){

            details = tasklist.getTask(i).toString();
            if(details.contains(description)){

                System.out.println("       " + Integer.toString(i) + ": " + details.trim());

            }

        }
        ui.Line();
    }
}
