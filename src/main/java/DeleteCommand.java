import java.io.IOException;

public class DeleteCommand extends Command{

    public static final String DoneMessage= "Noted. I've removed this task:\n\t  ";
    private int index;

    public DeleteCommand(String input){
        super(input);
    }

    /**
     * Delete item from the array list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((input.substring(6).trim()).isEmpty()){
            throw new DukeException("The selector of a delete cannot be empty.");
        }
        try{
        index = prepareIndex(input);
        ui.showOutputToUser(DoneMessage + TaskList.getTask(index).toString());
        TaskList.deleteList(index);
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