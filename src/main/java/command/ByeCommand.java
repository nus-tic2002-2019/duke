package command;
import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents the bye command that will exit the user after updating the status.
 * */

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    /**
     * Utilizing parent class function to return new status of exit.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        isExit = true;
    }
}
