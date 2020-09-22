package command;

import exception.DukeException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;
import task.Task;

/**
 * Deletes a task identified using it's index from the task list.
 */
public class DeleteCommand extends Command {
    protected static Ui ui = new Ui();
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * @param tasks   The tasks stored in an ArrayList.
     * @param ui      The User Interface (UI).
     * @param storage The storage to allow reading and storing of tasks from and to a txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        input = input.toLowerCase();
        int num = 0;
        if (input.matches(".*\\d.*"))
            num = Integer.parseInt(input.replaceAll("[\\D]", ""));
        if (num > 0 && num <= tasks.sizeOfTask()) {
            Task echo = tasks.returnTask(num - 1);
            tasks.deleteTask(num - 1);
            ui.printDeleteCommand(echo, tasks.sizeOfTask());
        } else {
            throw new DukeException("☹ Item not found.\n");
        }
        ui.printEmptyLine();
    }

}
