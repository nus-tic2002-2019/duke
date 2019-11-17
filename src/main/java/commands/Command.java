package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lug3g
 */
public abstract class Command {
    private String commandName;
    
    public Command(String commandName){
        this.commandName = commandName;
    }
    
    public String getCommand(){
        return this.commandName;
    }
    
    public boolean isError(){
        return this.commandName.equals("Error");
    }
    
    public abstract void execute(TaskList tasks,Ui ui,Storage storage);
    public abstract boolean isExit();
}
