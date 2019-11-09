package main.Commands;

import main.DukeException;
import main.TaskLists.Task;
import main.UI;

import static main.Duke.Tasks;

public class DeleteCommand extends Command<Integer> {


    public  DeleteCommand(Integer input) throws DukeException {
        this.execute(input);
    }

    @Override
    public void execute(Integer input) throws DukeException {
        Task t = Tasks.get(input - 1);
        Tasks.remove(input - 1);
        UI.deletedCommand(t);

    }


}
