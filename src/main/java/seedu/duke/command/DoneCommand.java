package seedu.duke.command;

import java.io.IOException;

import seedu.duke.data.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class DoneCommand extends Command{
    
    public static final String INPUT_WORD = "done";
    public static final String MESSAGE_DELETE_SUCCESS = "Nice! I've marked this task as done:\n\t  ";
    private int index;

    public DoneCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if(input.split(" ")[1].trim().isEmpty()){
            throw new DukeException("The selector of a done cannot be empty.");
        }
        try{
        index = prepareIndex(input);
        (taskList.getTask(index)).setDone();
        ui.showOutputToUser(MESSAGE_DELETE_SUCCESS + taskList.getTask(index).toString());
        storage.saveToFile();
        }
        catch (NumberFormatException e){
            throw new DukeException("The task selected must be a numerical value.");
        }
        catch (IndexOutOfBoundsException e){
            throw new DukeException("The tasks list cannot be empty.");
        }
    }

    private int prepareIndex(String args){
        return Integer.parseInt(input.split(" ")[1])-1;
    }
}