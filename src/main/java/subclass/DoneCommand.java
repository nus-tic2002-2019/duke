/**
 * Done command, marking tasks in list as done when task(s) are completed
 * Displays error if:
 *      no task index was entered
 *      task index entered was invalid
 */


package subclass;

public class DoneCommand extends Command {

    public static final String INPUT = "done";
    private int task_no;

    public DoneCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            String input_items = Parser.parseTask(input);
            task_no = Integer.parseInt(input_items);
            assert task_no > 0;
            Task tmp = Task.markDone(task_no);
            Ui.showLine();
            Ui.markAsDone();
            System.out.println("\t\t" + tmp);
            Ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.displayError_noItem();
            Ui.showLine();
        } catch (AssertionError e) {
            Ui.showLine();
            Ui.showError("Task index must be larger than 0!");
            Ui.showLine();
        }
    }
}
