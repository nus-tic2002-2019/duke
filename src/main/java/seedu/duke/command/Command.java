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
    
    /** 
     * Constructs a new Command with the specified isExit and input.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public Command(boolean isExit, String input){
        this.isExit = isExit;
        this.input = input;
    }

    /** 
     * Executes the command inputted by the user and returns the result.
     * @param   taskList            The array of tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI) to allow interaction with the user.
     * @param   storage             The storage to allow storing and reading of tasks from a file.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, DukeEmptyException, IOException{
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}