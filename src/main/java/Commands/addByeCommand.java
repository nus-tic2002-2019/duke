package Commands;
import Ui.*;
import Storage.*;
import TaskList.*;

public class addByeCommand extends Command{

    public static final String COMMAND = "bye";


    /**
     * Constructor for Bye Command
     * @param exit
     */
    public addByeCommand(boolean exit){
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showGoodBye();
    }
}
