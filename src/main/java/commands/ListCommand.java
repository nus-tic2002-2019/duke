/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 *
 * @author lug3g
 */
public class ListCommand extends Command {
    public ListCommand(String commandName){
        super(commandName);
    }
    
    public boolean isExit(){
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if ( getCommand().equals("list") ){
            try {
                ui.printTasks(tasks);
            } catch (DukeException e) {
                ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }
        }
        
        if ( getCommand().equals("listsameday")){
            try {
                String date = ui.getLine().replaceFirst("listsameday ", "");
                ui.printSameDayTasks(tasks,date);
            } catch (DukeException e) {
                ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }           
        }
        
        if ( getCommand().equals("find")){
             try {
                String keywords = ui.getLine().replaceFirst("find ", "");
                ui.find(tasks,keywords);
            } catch (DukeException e) {
                ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }  
        }
    }
}
