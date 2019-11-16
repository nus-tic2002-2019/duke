import java.io.IOException;

public class Command{
    protected String input;

    public Command(String input){
        this.input = input;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
} 