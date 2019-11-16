import java.io.IOException;

public class DoneCommand extends Command{

    public static final String DoneMessage = "Nice! I've marked this task as done:\n\t  ";
    private int index;

    public DoneCommand(String input){
        super(input);
    }

    /**
     * to mark the task in the array list as done
     */

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((input.substring(4).trim()).isEmpty()){
            throw new DukeException("The selector of a done cannot be empty.");
        }
        try{
        index = prepareIndex(input);
        (TaskList.getTask(index)).markAsDone();
        ui.showOutputToUser(DoneMessage + TaskList.getTask(index).toString());
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