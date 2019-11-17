package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.Utility;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    protected String type;
    protected String desc;
    protected LocalDate date;

    /**
     * Mainly for Deadline and Event tasks.
     *
     * @param type task type.
     * @param desc task description.
     * @param date occurence/due date depending on task type.
     */
    public AddCommand(String type, String desc, LocalDate date) {
        this.type = type;
        this.desc = desc;
        this.date = date;
    }

    /**
     * Mainly for ToDo tasks.
     *
     * @param type task type.
     * @param desc task description.
     */
    public AddCommand(String type, String desc) {
        this.type = type;
        this.desc= desc;
    }

    /**
     * Executes the command and add a new task to the task list.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws IOException if there are errors appending the date to the storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (this.type == "todo") {
            tasks.add(new ToDo(this.desc));
        } else if (this.type == "event") {
            tasks.add(new Event(this.desc, this.date));
        } else if (this.type == "deadline") {
            tasks.add(new Deadline(this.desc, this.date));
        }
        int index = tasks.size()-1;
        Task task = tasks.get(index);
        storage.append(index + ";" + Utility.constructInput(task));
        ui.print("New task added: \n\t" + task.getStatusIconAndDesc() + "\n" + (index+1) + " tasks in your list");
    }

}
