package Command;

import Ui.Ui;
import Tasklist.*;
import Storage.Storage;
import Parser.Parser;

import Exception.DukeException;
import java.io.IOException;

public class TodoCommand extends Command{
    public TodoCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try{
            tasks.addTask(new Todo(Parser.parseTodo(commandline)),true);
        }
        catch (DukeException e)
        {
            Ui.showError(e.getMessage());
        }
    }

    public void readfileformat(TaskList tasks) throws DukeException, IOException {
        try{
            tasks.addTask(new Todo(Parser.parseTodoFile(commandline)[2]),false);
            if(Parser.parseTodoFile(commandline)[1].equals("1")){
                tasks.markDone(tasks.size(),false);
            }
        }
        catch (DukeException e)
        {
            Ui.showError(e.getMessage());
        }
    }
}
