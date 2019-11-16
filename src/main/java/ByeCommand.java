public class ByeCommand extends Command{
    public static final String MESSAGE_BYE_ACKNOWLEDGEMENT = "Bye. Hope to see you again soon!";

    public ByeCommand(String input){
        super(input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        ui.goodbyeMessage();
        System.exit(0);
    }
} 