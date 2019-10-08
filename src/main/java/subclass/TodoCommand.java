package subclass;
import java.io.IOException;

public class TodoCommand extends Command {
    public static final String INPUT = "todo";
    //private Todo todo;

    public TodoCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException {
        /*todo = new Todo(input.substring(5));
        taskList.addTask(todo);*/
        String input_items = Parser.parseTask(input);
        Ui.showLine();
        Ui.displayGotIt();
        System.out.println("\t\t" + Task.add_task(new Todo(input_items)));
        System.out.println("\t\tNow you have " + Task.word_count + " tasks in list.");
        Ui.showLine();
    }
}
