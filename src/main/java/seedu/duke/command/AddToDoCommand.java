package seedu.duke.command;

import java.io.IOException;

import seedu.duke.data.exception.DukeEmptyException;
import seedu.duke.data.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.data.task.ToDo;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class AddToDoCommand extends Command{
    public static final String INPUT_WORD = "todo";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "Got it. I've added this task:\n\t  ";
    private ToDo todo;

    /** 
     * Constructs a new AddToDoCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public AddToDoCommand(boolean isExit, String input){
        super(isExit, input);
    }

    /** 
     * Creates a new ToDo task and checks whether if the description is empty before creating.
     * @param   taskList            The array of tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI) to allow interaction with the user.
     * @param   storage             The storage to allow storing and reading of tasks from a file.
     * @throws  DukeEmptyException  If the user inputs an empty description.
     * @throws  IOException         If an input or output exception occurs.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, IOException, DukeException{
        if((input.substring(4).trim()).isEmpty()){
            throw new DukeEmptyException("todo");
        }
        todo = new ToDo(input.substring(5));
        taskList.addToTaskList(todo);
        ui.showOutputToUser(MESSAGE_ADD_TODO_SUCCESS + todo.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
        storage.saveToFile();
    }
}