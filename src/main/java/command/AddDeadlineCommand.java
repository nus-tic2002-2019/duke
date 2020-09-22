package command;

import exception.EmptyException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;
import task.Deadline;

/**
 * Adds a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {

    private String input;

    /**
     * @param input A String inputted by the user.
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * @param tasks   The tasks stored in an ArrayList.
     * @param ui      The User Interface (UI).
     * @param storage The storage to allow reading and storing of tasks from and to a txt file.
     * @throws EmptyException If an empty description is inputted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyException {
        assert input.contains("/by") : "wrong format '/by' needed";
        assert !input.contains("/at") : "wrong format '/at' not allowed";

        input = input.toLowerCase();
        if (input.contains("deadline")) {
            input = input.replace("deadline", "");
        }
        else{
            input = input.replaceFirst("d", "");
        }
        int position = input.indexOf("/by");
        if (input.substring(0, position - 1).equals("") || input.substring(0, position - 1).equals(" ")) {
            throw new EmptyException("a deadline");
        } else {
            String date = input.substring(position + 4);
            input = input.substring(0, position - 1);
            Deadline deadline = new Deadline(input, date);
            tasks.addTask(deadline);
            ui.showAdded();
            ui.printTaskNum(tasks, deadline);
        }
    }

}
