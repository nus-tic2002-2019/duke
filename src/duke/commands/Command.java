package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * processes and executes commands that add new tasks
 * to Duke's array list.
 */
public abstract class Command {
    private String commandName;
    
    /**
     * Constructor method for Command class
     * @param commandName user's command into the system
     * Takes in the commandName to process and understand what the user's command do 
     * Able to take in todo, event, delete, exit, list, priority and deadline commands.
     */
    public Command(String commandName){
        this.commandName = commandName;
    }
    /**
     * Returns the name of the command in this class.
     * @return commandName in this class
     */
    public String getCommand(){
        return this.commandName;
    }
    /**
     * Returns if the command input by the user is an error
     * @return 
     */
    public boolean isError(){
        return this.commandName.equals("Error");
    }
    /**
     * Abstract method for class extension to override
     * executes based on the command input given.
     * @param tasks Task list in duke
     * @param ui User interaction class
     * @param storage Storage interaction, able to save and load the data.
     */
    public abstract void execute(TaskList tasks,Ui ui,Storage storage);
    /**
     * Abstract method for class extension to override
     * Checks if program is terminated on the user's command
     * @return 
     * returns
     * true when exiting program
     * false when processing other commands
     */
    public abstract boolean isExit();
}
