package command;

import exception.DukeException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converts the dates' format from 'yyyy-MM--dd' to 'dd MMMMM yyyy'.
 */
public class DateCommand extends Command {
    protected static Ui ui = new Ui();
    private String input;

    public DateCommand(String input) {
        this.input = input;
    }

    /**
     * @param tasks   The tasks stored in an ArrayList.
     * @param ui      The User Interface (UI).
     * @param storage The storage to allow reading and storing of tasks from and to a txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert !input.toLowerCase().replace("date", "").equals(" ") : "no date inserted";
        assert !input.toLowerCase().replace("date", "").equals("") : "no date inserted";

        if (tasks.sizeOfTask() == 0)
            throw new DukeException("☹ The list is empty.\n");
        else if (input.length() <= 5)
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.\n");
        else {
            String date = input.substring(5);
            int count = 0;
            for (int i = 0; i < tasks.sizeOfTask(); i++) {
                if (tasks.returnTask(i).toString().contains(LocalDate.parse(date).format(DateTimeFormatter.ofPattern("d MMM yyyy")))) {
                    System.out.println(tasks.returnTask(i).toString());
                    count++;
                }
            }
            if (count == 0) {
                throw new DukeException("☹ Item not found.");
            }
            ui.printEmptyLine();
        }
    }

}
