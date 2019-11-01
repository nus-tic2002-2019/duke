package command;

import exception.DukeException;
import storage.Storage;
import task.Originator;
import task.TaskList;
import ui.Ui;

import java.util.List;

public class UndoCommand extends Command {

    public UndoCommand(String fullCommand, List<Originator.Memento> savedStates, Originator originator) {
        originator.restoreFromMemento(savedStates.get(1));
    }

    /**
     * Utilizing parent class function to search for matching word requested by user against the task list and returning them.
     * */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }
}
