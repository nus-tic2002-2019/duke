package Command;

import Storage.Storage;
import Tasklist.TaskList;
import Ui.Ui;
import Exception.DukeException;
import java.io.IOException;

public class Command {
    protected static String commandline;

    public boolean isExit;

    public Command(String commandline){
        this.commandline = commandline;
    }
    public Command(){

    }
    public boolean isExit(){
        return this.isExit;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        throw new UnsupportedOperationException("");
    }

    public void readfileformat(TaskList tasks) throws DukeException, IOException {
        throw new UnsupportedOperationException("");
    }

}
