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
 * DoneCommand class is an extension of Command class that
 * processes and executes commands that delete existing tasks
 * from Duke's array list.
 * @author Guan Hao
 */
public class DoneCommand extends Command {
    /**
     * Constructor method for DoneCommand class
     * @param commandName user's command into the system
     * Inherits commandName from superclass Command
     */
    public DoneCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * DoneCommand will only return false as completing/un=completing Tasks 
     * does not terminate the program.
     * @return 
     * returns
     * true when exiting program
     * false when processing other commands
     */
    @Override
    public boolean isExit(){
        return false;
    }
    /**     
     * Override execute method in Command class
     * Execute user's command after processing 
     * and understanding user's command 
     * @param tasks Task list in duke
     * @param ui User interaction class
     * @param storage Storage interaction, able to save and load the data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int x = Integer.parseInt(ui.getLine().replaceFirst(getCommand()+" ",""));
            if (getCommand().equals("done")){
                tasks.getTask(x-1).markAsDone();
            }
            if (getCommand().equals("undone")){
                tasks.getTask(x-1).markAsUndone();
            }
            Ui.response("Nice! I've marked this task as done:\n\t\t" + tasks.getTask(x-1).printTask());
        } catch (IndexOutOfBoundsException e) {
            int x = Integer.parseInt(ui.getLine().replaceFirst(getCommand()+" ",""));
            Ui.response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + tasks.getSize() + ")" );
        } catch (NumberFormatException e){
            Ui.response("☹ OOPS!!! You have to key in the task number to complete or uncomplete this task.");
        }
        storage.save(tasks);
    }
    
}
