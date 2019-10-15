package subclass;
import java.io.IOException;

public class TodoCommand extends Command {
    public static final String INPUT = "todo";
    //private Todo todo;

    public TodoCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException, todoException {
        try {
            String input_items = Parser.parseTask(input);
            if (input_items.equals("todo")) {
                throw new todoException();
            }
            Ui.showLine();
            Ui.displayGotIt();
            System.out.println("\t\t" + Task.add_task(new Todo(input_items)));
            System.out.println("\t\tNow you have " + Task.word_count + " tasks in list.");
            Ui.showLine();
        } catch (todoException e) {
            Ui.showLine();
            Ui.displayTodoError();
            Ui.showLine();
        }

    }
}
