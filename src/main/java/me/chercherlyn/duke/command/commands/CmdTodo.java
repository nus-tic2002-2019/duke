package me.chercherlyn.duke.command.commands;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.task.tasks.Todo;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to add "todo" task
 */
public class CmdTodo extends Cmd {
    
    public CmdTodo(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        // check : no description specified
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("The description of a todo cannot be empty.");
    
        String description = String.join(" ", args);
        Task task = new Todo(description);
        tasks.add(task);
        storage.saveTasks(tasks);
    
        ui.printFancy("" +
                        "Got it. I've added this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }
}
