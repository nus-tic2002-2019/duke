package command;

import exception.EmptyException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;
import task.Event;

/**
 * Adds an event task to the task list.
 */
public class AddEventCommand extends Command {
    private String input;

    /**
     * @param input A String inputted by the user.
     */
    public AddEventCommand(String input) {
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
        assert input.contains("/at") : "wrong format '/at' needed";
        assert !input.contains("/by") : "wrong format '/by' not allowed";

        input = input.toLowerCase();
        if (input.contains("event")){
            input = input.replace("event", "");
        }
        else{
            input = input.replaceFirst("e", "");
        }
        int position = input.indexOf("/at");

        if (input.substring(0, position - 1).equals("") || input.substring(0, position - 2).equals(" ")) {
            throw new EmptyException("an event");
        } else {
            String date = input.substring(position + 4);
            input = input.substring(0, position - 1);
            Event event = new Event(input, date);
            tasks.addTask(event);
            ui.showAdded();
            ui.printTaskNum(tasks, event);
        }
    }

}


