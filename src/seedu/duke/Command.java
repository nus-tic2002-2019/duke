import java.io.IOException;

public class Command{
    public boolean isExit;
    protected String input;
    
    public Command(boolean isExit, String input){
        this.isExit = isExit;
        this.input = input;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, DukeEmptyException, IOException{
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}