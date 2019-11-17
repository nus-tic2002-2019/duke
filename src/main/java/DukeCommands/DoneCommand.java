package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.Task;
import java.util.ArrayList;
import newDuke.DukeExceptions.Exception;

/**
 * A Command indicate that a Task from the TaskList and Storage has been completed.
 */

public class DoneCommand implements Command {
    /**
     * Indicates which element of the TaskList has been completed.
     */
    private String[] doneDetails;
	private int taskNum;
    public DoneCommand(String [] doneDetails) {
        this.doneDetails = doneDetails;
    }

    /**
     * Marks a specific Task from the TaskList and Storage as indicated by the taskNum attribute as completed.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */

    public String execute(TaskList taskList, Storage storage) {
		
        try{
			try {
			taskNum = Integer.valueOf(doneDetails[1]) - 1;
			ArrayList<Task> list = taskList.getTaskList();
			Task currTask = list.get(taskNum);
			currTask.setStatusIconTrue();
			storage.writeToFile(list);
			return UI.done(currTask);
			} catch (ArrayIndexOutOfBoundsException nfe){
				throw new Exception(Exception.Code.EMPTY_DONE_DESCRIPTION);
			} catch (IndexOutOfBoundsException ioobe){
				throw new Exception(Exception.Code.INVALID_TASK_NUMBER);
			}
		} catch (Exception e){
			return e.errorDescription();
		}
    }
}
