package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.Task;
import java.util.ArrayList;
import newDuke.DukeExceptions.Exception;

/**
 * A Command to find a Task from the TaskList and Storage.
 */

public class FindCommand implements Command {

    private String searchTerm;
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }
	
	/**
     * Executes the Find command which finds a Task.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
	 * @return all matching tasks found in the console.
     */
    @Override
    public String execute(TaskList tl, Storage st) {
        StringBuilder toReturn = new StringBuilder(UI.findStart());
        int taskcount = 1;
        for (Task task : tl.getTaskList()) {
            String[] taskNameComponents = task.description().split(" ");
            for (String nameComponent : taskNameComponents) {
                if (nameComponent.equalsIgnoreCase(searchTerm)) {
                    toReturn.append("     ");
                    toReturn.append(taskcount);
                    toReturn.append(".");
                    toReturn.append(task);
                    toReturn.append("\n");
                    taskcount++;
                    break;
                }
            }
        }
        return toReturn.toString();
    }
}
