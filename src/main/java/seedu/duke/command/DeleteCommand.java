package seedu.duke.command;

import java.io.IOException;

import seedu.duke.exception.DukeEmptyException;
import seedu.duke.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class DeleteCommand extends Command{
    public static final String INPUT_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n\t  ";
    private int index;

    /** 
     * Constructs a new DeleteCommand and initialise with the specified isExit boolean value and input by the user.
     * @param   isExit  A boolean value whether if the exit condition is true.
     * @param   input   A String inputted by the user.
     */
    public DeleteCommand(boolean isExit, String input){
        super(isExit, input);
    }

    /** 
     * Deletes a task from the task list and checks whether if the input or list is empty before deleting the task.
     * @param   taskList            The array of tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI) to allow interaction with the user.
     * @param   storage             The storage to allow storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeEmptyException, DukeException, IOException{
        if(input.split(" ")[1].trim().isEmpty()){
            throw new DukeException("The selector of a delete cannot be empty.");
        }
        try{
        index = prepareIndex(input);
        ui.setOutput(MESSAGE_DELETE_SUCCESS + taskList.getTask(index).toString());
        taskList.deleteFromTaskList(index);
        storage.saveToFile();
        } catch (NumberFormatException e){
            throw new DukeException("The task selected must be a numerical value.");
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("The tasks list cannot be empty.");
        }
    }
    
    /** 
     * Converts the given String into an Integer.
     * @param   input   The input of the user.
     * @return  int     The number selected by the user in Integer format.
     */
    private int prepareIndex(String input){
        return Integer.parseInt(input.split(" ")[1])-1;
    }
}