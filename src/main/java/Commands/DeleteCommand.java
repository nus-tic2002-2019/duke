package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

public class DeleteCommand extends Command {

    public DeleteCommand(String taskDes){
        super(taskDes);
    }

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
