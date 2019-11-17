package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

/**
 * Command to delete a task
 */
public class DeleteCommand extends Command {
    /**
     * Constructs the delete command
     * @param taskDes will store the command that the user input
     */
    public DeleteCommand(String taskDes){
        super(taskDes);
    }

    /**
     * Execute the removal of Task in the task list
     * @param tasks The task list will store the task
     * @param ui The Ui class which will help to display to the user
     * @param storage The Storage which will save the list of task to
     * @throws DukeException Any expected error
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(7);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Delete command can't be empty");
        }
        try {
            int taskIndex = Integer.parseInt(taskItem.substring(7))-1;
            String deletedTask = tasks.getTask(taskIndex).toString();
            tasks.remove(taskIndex);
            ui.deleteMsg(deletedTask, tasks.getSize());
            storage.save(tasks);
        }
        catch(NumberFormatException e){
            throw new DukeException("Please key in task number");
        }
    }


}
