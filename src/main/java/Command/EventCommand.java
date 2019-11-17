package Command;

import Ui.Ui;
import Tasklist.*;
import Storage.Storage;
import Parser.Parser;

import java.io.IOException;
import Exception.DukeException;

public class EventCommand extends Command {

    public EventCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.addTask(new Events(Parser.parseEvent(commandline)[0], Parser.parseEvent(commandline)[1]), true);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
    public void readfileformat(TaskList tasks) throws DukeException, IOException {
        try {
            tasks.addTask(new Events(Parser.parseEventFile(commandline)[2], Parser.parseEventFile(commandline)[3]), false);
            if(Parser.parseEventFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size(),false);
            }
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }

}
