package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand class is an extension of Command class that
 * processes and executes commands that delete existing tasks
 * from Duke's array list.
 * @author Guan Hao
 */
public class ListCommand extends Command {
    public ListCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * ListCommand will only return false as listing Tasks does not terminate the
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
        if ( getCommand().equals("list") ){
            try {
                Ui.printTasks(tasks);
            } catch (DukeException e) {
                Ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }
        }
        
        if ( getCommand().equals("listsameday")){
            try {
                String date = ui.getLine().replaceFirst("listsameday ", "");
                Ui.printSameDayTasks(tasks,date);
            } catch (DukeException e) {
                Ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }           
        }
        
        if ( getCommand().equals("find")){
             try {
                String keywords = ui.getLine().replaceFirst("find ", "");
                Ui.find(tasks,keywords);
            } catch (DukeException e) {
                Ui.response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }  
        }
    }
}
