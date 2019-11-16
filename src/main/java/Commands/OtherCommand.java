package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;

public class OtherCommand extends Command {

    public OtherCommand(String taskDes){
        super(taskDes);
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch(taskItem){
            case "hi":
                ui.displayMsg("Hello to you too! :)");
                break;
            case "help":
                ui.displayListOfHelpFunction();
                break;
            case "clearlist":
                tasks.clearAll();
                ui.displayMsg("List of Tasks has all been clear, you have an empty list now.");
                break;
            default:
                throw new DukeException("Shouldn't have error here");
        }

    }


}
