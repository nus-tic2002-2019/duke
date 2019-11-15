package DukeCommands;

import main.Storage;
import main.TaskList;
import DukeTasks.*;
import java.util.ArrayList;

/**
 * A Command to print a list of all the Tasks in the TaskList.
 */

public class ListCommand implements Command {

    public ListCommand() {

    }
    /**
     * Prints into the console all the Tasks in the TaskList.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */

    public String execute(TaskList taskList, Storage storage) {
		System.out.println("Here are the tasks in your list:");
		for (int a=1; a<taskList.getSize()+1;a++){
		System.out.println("	"+a+". "+taskList.getTask(a-1) );
		}
        return "\n";
    }
}
