package me.chercherlyn.duke.command.commands;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to mark task as done.
 */
public class CmdDone extends Cmd {
    
    public CmdDone(Ui ui, TaskList tasks, Storage storage) {
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
        task.setDone(true);
        storage.saveTasks(tasks);
        ui.printFancy("Nice! I've marked this task as done:\n  %s", task.toString());
    }
}
