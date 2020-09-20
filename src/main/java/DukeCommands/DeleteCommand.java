package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.Task;
import java.util.ArrayList;
import newDuke.DukeExceptions.Exception;

/**
 * A Command to remove a Task from the TaskList and Storage.
 */
public class DeleteCommand implements Command {
    /**
     * Indicates which element of the TaskList needs to be deleted.
     */
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes a specific Task from the TaskList and Storage as indicated by the taskNum attribute.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) {

        try {
            ArrayList<Task> list = taskList.getTaskList();
            Task task = list.remove(taskNum - 1);
            int listSize =  list.size();
            storage.writeToFile(list);
            return UI.removedTask(task, listSize);
		} catch (NumberFormatException e){
			return new Exception(Exception.Code.EMPTY_DELETE_DESCRIPTION).toString();
		} catch (IndexOutOfBoundsException e){
			return new Exception(Exception.Code.INVALID_TASK_NUMBER).toString();
		}
    }
}
