/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.commands.Command;
/**
 *
 * @author lug3g
 */
public class ExitCommand extends Command {
    public ExitCommand(String commandName){
        super(commandName);
    }
    
    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
