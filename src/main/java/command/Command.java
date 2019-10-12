package command;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;
import java.io.IOException;

/**
 * Represents the parent command that will is utilized by the respective commands.
 * */

public class Command {
    /**
     * Creation of checker variable on exit
     * and string for command line input from user.
     * */
    public boolean isExit;
    protected String fullCommand;

        public Command(String fullCommand){
            this.fullCommand = fullCommand;
        }

    public Command() {

    }
    /**
     * Parent function that will be utilized by child classes.
     * */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

}

