package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

/**
 * Command set a specific task as done
 */
public class DoneCommand extends Command {
    /**
     * Constructs the Done command
     * @param taskDes the command the user input
     */
    public DoneCommand(String taskDes){
        super(taskDes);
    }

    /**
     * Set the Task isDone boolean to True
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(5);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Done command can't be empty");
        }
        try {
            int taskIndex = Integer.parseInt(taskItem.substring(5)) - 1;
            Task doneTask = tasks.getTask(taskIndex);
            if(doneTask.getIsDone()){
                throw new DukeException("Tasks.Task is already done");
            }
            doneTask.editDone(true);
            Ui.doneMsg(doneTask.toString(), doneTask.getTaskIndex()+1);
        }
        catch(NumberFormatException e){
            throw new DukeException("Please key in task number");
        }

        storage.save(tasks);
    }

}
