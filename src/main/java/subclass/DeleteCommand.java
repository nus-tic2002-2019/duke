package subclass;

public class DeleteCommand extends Command {
    public static final String INPUT = "delete";
    //private int task_no;

    DeleteCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            String input_items = Parser.parseTask(input);
            int task_option = Integer.parseInt(input_items);
            Task.removeTask(task_option);
        } catch (IndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.displayError_noItem();
            Ui.showLine();
        }

    }
}
