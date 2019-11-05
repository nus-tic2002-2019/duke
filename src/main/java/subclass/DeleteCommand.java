/**
 * Delete command class
 * @param input is input entered by user after "delete" command
 * @throws subclass.DukeException if "delete" command is entered without index
 * @throws java.lang.AssertionError when index entered after "delete" command is lesser than 0
 */


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
            assert task_option > 0;
            Task.removeTask(task_option);
        } catch (IndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.displayError_noItem();
            Ui.showLine();
        } catch (AssertionError e) {
            Ui.showLine();
            Ui.showError("Task Index must be larger than 0!");
            Ui.showLine();
        }

    }
}
