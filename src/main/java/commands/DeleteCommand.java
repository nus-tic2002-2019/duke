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
public class DeleteCommand extends Command {
    public DeleteCommand(String commandName){
        super(commandName);
    }
    
    public boolean isExit(){
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int x = Integer.parseInt(ui.getLine().replaceFirst("delete ", ""));
        try {
            ui.delResponse(tasks.getTask(x-1).printTask(),tasks.getSize()-1);
            tasks.getTaskList().remove(x-1);
        } catch (IndexOutOfBoundsException e){
            ui.response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + tasks.getSize() + ")" );
        }
        storage.save(tasks);
    }
}
