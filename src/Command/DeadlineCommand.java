package Command;

import Ui.Ui;
import Tasklist.*;
import Storage.Storage;
import Parser.Parser;

import Exception.DukeException;
import java.io.IOException;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.addTask(new Deadlines(Parser.parseDeadline(commandline)[0], Parser.parseDeadline(commandline)[1]), true);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
    public void readfileformat(TaskList tasks) throws DukeException, IOException {
        try {
            tasks.addTask(new Deadlines(Parser.parseDeadlineFile(commandline)[2], Parser.parseDeadlineFile(commandline)[3]), false);
            if(Parser.parseDeadlineFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size(),false);
            }
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
