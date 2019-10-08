package subclass;

public class ListCommand extends Command {

    public static final String INPUT = "list";

    public ListCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Ui.showLine();
        System.out.println(Task.getList());
        Ui.showLine();
    }

}
