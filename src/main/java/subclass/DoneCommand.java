package subclass;

public class DoneCommand extends Command {

    public static final String INPUT = "done";
    private int task_no;

    public DoneCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String input_items = Parser.parseTask(input);
        task_no = Integer.parseInt(input_items);
        Task tmp = Task.markDone(task_no);
        Ui.showLine();
        Ui.markAsDone();
        System.out.println("\t\t" + tmp);
        Ui.showLine();
    }
}
