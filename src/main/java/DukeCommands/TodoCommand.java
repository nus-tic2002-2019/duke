package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.*;
import java.util.ArrayList;
import newDuke.DukeExceptions.Exception;

/**
 * A Command to create an instance of a Todo Task. This will add the Todo Task to the TaskList and the Storage file.
 */

public class TodoCommand implements Command {

    private String todoName;
    public TodoCommand(String todoName) {
        this.todoName = todoName;
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */

    public String execute(TaskList taskList, Storage storage) {
		try {
			try {
				ArrayList<Task> list = taskList.getTaskList();
				Task todo = new Todo(todoName);
				taskList.addTaskList(todo);
				storage.writeToFile(list);
				return UI.newTask(list);
			} catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
				if ( todoName==null || todoName.trim().length() == 0){
					throw new Exception(Exception.Code.EMPTY_TODO_DESCRIPTION);
				}
			} catch (Exception e){
				return e.errorDescription();
			}
		}
	}
}
