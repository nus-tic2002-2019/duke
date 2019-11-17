package command;

import exceptions.DukeEmptyException;
import exceptions.DukeException;
import task.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;

public class Command{
    public boolean isExit;
    protected String input;

    public Command(boolean isExit, String input){
        this.isExit = isExit;
        this.input = input;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}