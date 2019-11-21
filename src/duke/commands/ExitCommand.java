package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ExitCommand class is an extension of Command class that
 * processes and executes commands that delete existing tasks
 * from Duke's array list.
 * @author Guan Hao
 */
public class ExitCommand extends Command {
    /**
     * Constructor method for ExitCommand class
     * @param commandName user's command into the system
     * Inherits commandName from superclass Command
     */
    public ExitCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * ExitCommand will only return true as exit Tasks is to terminate the
     * program.
     * @return 
     * returns
     * true when exiting program
     * false when processing other commands
     */
    @Override
    public boolean isExit(){
        return true;
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
        Ui.exit();
    }
}
