import java.io.IOException;
import java.util.Arrays;

public class DeleteCommand extends Command{
    public static final String INPUT_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n\t ";
    private int index;

    public DeleteCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        //if((input.substring(6).trim()).isEmpty())
        if(input.split(" ")[1].trim().isEmpty()){
           throw new DukeException("The selector of a delete cannot be empty.");
        }
        try{
            index = prepareIndex(input);
            ui.showOutputToUser(MESSAGE_DELETE_SUCCESS + taskList.getTask(index).toString());
            taskList.deleteFromTaskList(index);
            storage.saveToFile();
        }
        catch (NumberFormatException e) {
            throw new DukeException("The task selected must be a numerical value.");
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("The tasks list cannot be empty.");
        }
    }
    private int prepareIndex(String args) {
        return Integer.parseInt(input.split(" ")[1])-1;
    }
}