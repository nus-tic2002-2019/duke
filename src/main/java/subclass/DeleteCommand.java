package subclass;

public class DeleteCommand extends Command {
    public static final String INPUT = "delete";
    //private int task_no;

    public DeleteCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        /*String input_items = Parser.parseTask(input);
        task_no = Integer.parseInt(input_items);
        taskList.deleteTask(task_no);*/
        String input_items = Parser.parseTask(input);
        int task_option = Integer.parseInt(input_items);
        Task.removeTask(task_option);
    }
}
