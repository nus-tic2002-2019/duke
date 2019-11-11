package command;

import exception.EmptyException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;
import task.Todo;

/**
 * Adds a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    protected static Ui ui = new Ui();
    private String input;

    /**
     * @param input  A String inputted by the user.
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * @param   tasks               The tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI).
     * @param   storage             The storage to allow reading and storing of tasks from and to a txt file.
     * @throws  EmptyException      If an empty description is inputted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyException{
        assert !input.contains("/at") : "wrong format '/at' not allowed";
        assert !input.contains("/by") : "wrong format '/by' not allowed";

        input = input.toLowerCase();
        input = input.replace("todo", "");
        if (input.equals("") || input.equals(" ")) {
            throw new EmptyException("a todo");
        } else {
            Todo todo = new Todo(input);
            tasks.addTask(todo);
            ui.showAdded();
            ui.printTaskNum(tasks, todo);
        }
    }

}