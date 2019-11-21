package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.priority.Priority;

/**
 * SetCommand class is an extension of Command class that
 * processes and executes commands that delete existing tasks
 * from Duke's array list.
 * @author Guan Hao
 */
public class SetCommand extends Command {
    public SetCommand(String commandName){
        super(commandName);
    }
    /**
     * Override isExit method in Command class
     * Method to determine if user had choose to command to exit the system
     * SetCommand will only return false as set Tasks does not terminate the
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
        String words[] = ui.getLine().replaceFirst("setpriority ", "").split(" ");
        int x = Integer.parseInt(words[0]);
        String p = words[1];
        tasks.getTask(x-1).setPriority(Priority.valueOf(p.toUpperCase()));
        Ui.response("I have set the priority for " + tasks.getTask(x-1).getDescription() + " to " + p);
        storage.save(tasks);
    }
}
