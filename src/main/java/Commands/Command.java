package Commands;

import Exception.DukeException;
import Tasks.TaskList;
import UI.Ui;
import Storage.Storage;

public abstract class Command {
    protected String taskItem;
    public Command(){

    }

    public Command(String taskItem){
        this.taskItem = taskItem;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Error for command execute!");
    }
    public boolean isExit(){

        return false;
    }
}
