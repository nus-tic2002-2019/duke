package me.chercherlyn.duke.command.commands;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to delete a task.
 */
public class CmdDelete extends Cmd {
    
    public CmdDelete(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        // check : index
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("The index of a task not specified.");
    
        // check : index not correct
        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
            if (index < 0 || index >= tasks.size())
                throw new DukeException("The index is out of tasks range!");
        } catch (NumberFormatException ex) {
            throw new DukeException("The index is not a number!");
        }
    
        Task task = tasks.get(index);
        tasks.remove(index);
        storage.saveTasks(tasks);
    
        ui.printFancy("" +
                        "Noted. I've removed this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }
}
