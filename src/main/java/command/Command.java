package command;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;
import java.io.IOException;

public class Command {

    public boolean isExit;
    protected String fullCommand;

        public Command(String fullCommand){
            this.fullCommand = fullCommand;
        }

    public Command() {

    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

}

