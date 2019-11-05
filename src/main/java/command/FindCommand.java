package command;

import error.IllegalStringException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    /**
     * Constructor of List Command
     */
    public FindCommand(String description){
        super(description);
    }

    /**
     * To list all the Tasks that has the user-assigned keyword
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        String content = null;
        for(int i = 0; i < task.getSize(); i++){
            content = task.getTask(i).toString();
            if (content.indexOf(description)>= 0){
                ui.showToUser(Integer.toString(i) + ": " + content);
            }
        }
    }
}
