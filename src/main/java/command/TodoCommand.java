package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

public class TodoCommand extends Command {

    public TodoCommand(String fullCommand){
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if(fullCommand.substring(5).isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for TODO");
        }
        tasks.addTask(new ToDo(fullCommand.substring(5)));
        ui.changed();
    }
}
