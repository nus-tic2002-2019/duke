package seedu.duke.command;

import java.io.IOException;

import seedu.duke.data.exception.DukeEmptyException;
import seedu.duke.data.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class Command{
    public boolean isExit;
    protected String input;
    
    public Command(boolean isExit, String input){
        this.isExit = isExit;
        this.input = input;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, DukeEmptyException, IOException{
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}