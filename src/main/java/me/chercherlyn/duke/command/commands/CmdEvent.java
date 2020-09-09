package me.chercherlyn.duke.command.commands;

import java.util.Arrays;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.task.tasks.Event;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to add "event" task
 */
public class CmdEvent extends Cmd {
    
    public CmdEvent(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        // get "/at" index in args array
        int timeIndex = -1;
        for (int i = 0; i < args.length; i++) {
            if ("/at".equals(args[i])) {
                timeIndex = i;
                break;
            }
        }
    
        // check : no time specified
        if (timeIndex == -1 || args.length - 1 <= timeIndex)
            throw new DukeException("Correct /at not specified for event.");
    
        // check : no description specified
        if (timeIndex == 0)
            throw new DukeException("The description of a event cannot be empty.");
    
        // for description we join args starting from 0 up to index before "/at"
        // for time we join args starting after "/at" up to end of args
        String description = String.join(" ", Arrays.copyOfRange(args, 0, timeIndex));
        String time = String.join(" ", Arrays.copyOfRange(args, timeIndex + 1, args.length));
    
        Task task = new Event(description, time);
        tasks.add(task);
        storage.saveTasks(tasks);
    
        ui.printFancy("" +
                        "Got it. I've added this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }
}
