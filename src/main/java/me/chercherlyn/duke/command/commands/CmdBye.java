package me.chercherlyn.duke.command.commands;

import me.chercherlyn.duke.command.Cmd;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Command to close the program.
 */
public class CmdBye extends Cmd {
    
    public CmdBye(Ui ui, TaskList tasks, Storage storage) {
        super(ui, tasks, storage);
    }
    
    @Override
    public void execute(String[] args) {
        ui.printFancy("Bye. Hope to see you again soon!");
        System.exit(0); // exit the app
    }
}
