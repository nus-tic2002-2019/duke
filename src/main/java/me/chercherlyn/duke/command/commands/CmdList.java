package me.chercherlyn.duke.command.commands;

import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to view list of tasks.
 */
public class CmdList extends Cmd {
    
    public CmdList(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        // if no tasks in the list, then output specified message
        if (tasks.size() == 0) {
            ui.printFancy("There are no tasks in the list! Add something!");
            return;
        }
    
        // build output list
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            builder
                    .append(count).append(". ")
                    .append(tasks.get(i)).append("\n");
        }
    
        // remove last line break
        builder.deleteCharAt(builder.length() - 1);
    
        // print it
        String output = builder.toString();
        ui.printFancy(output);
    }
}
