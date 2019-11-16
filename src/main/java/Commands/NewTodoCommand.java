package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

public class NewTodoCommand extends Command {

    public NewTodoCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(5);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Todo command can't be empty");
        }
        tasks.addTask(new ToDos(taskItem.substring(5)));
        storage.save(tasks);
    }

}
