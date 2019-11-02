package Command;

import Parser.Parser;
import Storage.Storage;
import Tasklist.TaskList;
import Ui.Ui;

import java.io.IOException;
import Exception.DukeException;

public class DoneCommand extends Command{
    public DoneCommand(String commandline){

        super(commandline);
    }

    //public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException{

        try {
            tasks.markDone(Parser.parseIntegerParameter(commandline),true);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
//        catch (NumberFormatException e){
//            Ui.showError("Done command : " + e.getMessage());
//        }
    }
}
