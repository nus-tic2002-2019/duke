/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *
 * @author lug3g
 */
public class DoneCommand extends Command {
    public DoneCommand(String commandName){
        super(commandName);
    }
    
    public boolean isExit(){
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int x = Integer.parseInt(ui.getLine().replaceFirst("done ",""));
        try {
            tasks.getTask(x-1).markAsDone();
            ui.response("Nice! I've marked this task as done:\n\t\t" + tasks.getTask(x-1).printTask());
        } catch (IndexOutOfBoundsException e) {
            ui.response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + tasks.getSize() + ")" );
        }
    }
}
