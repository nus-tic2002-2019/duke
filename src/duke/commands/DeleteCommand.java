package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand class is an extension of Command class that
 * processes and executes commands that delete existing tasks
 * from Duke's array list.
 * @author Guan Hao
 */
public class DeleteCommand extends Command {
    /**
     * Constructor method for DeleteCommand class
     * @param commandName user's command into the system
     * Inherits commandName from superclass Command
     */
    public DeleteCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * DeleteCommand will only return false as deleting Tasks does not terminate the
     * program.
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
            int x = Integer.parseInt(ui.getLine().replaceFirst("delete ", ""));
            Ui.delResponse(tasks.getTask(x-1).printTask(),tasks.getSize()-1);
            tasks.getTaskList().remove(x-1);
        } catch (IndexOutOfBoundsException e){
            int x = Integer.parseInt(ui.getLine().replaceFirst(getCommand()+" ",""));
            Ui.response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + tasks.getSize() + ")" );
        } catch (NumberFormatException e){
            Ui.response("☹ OOPS!!! You have to key in the task number to delete this task.");
        }
        storage.save(tasks);
    }
}
