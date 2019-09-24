public class ByeCommand extends Command{
    public static final String INPUT_WORD = "bye";
    public static final String MESSAGE_BYE_ACKNOWLEDGEMENT = "Bye. Hope to see you again soon!";

    public ByeCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        ui.showGoodbyeMessage();
    }
}