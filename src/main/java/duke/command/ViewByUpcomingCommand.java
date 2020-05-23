package duke.command;

import duke.others.DukeException;
import duke.others.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.Period;

/**
 * Display list of upcoming tasks.
 */
public class ViewByUpcomingCommand extends Command {
    public ViewByUpcomingCommand() {
    }

    /**
     * Executes the duke.command and display a list of deadlines and events that are due today, tomorrow and overdue.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if there are no tasks in the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String input = getUpcoming(tasks);
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        ui.print(input);
    }

    private String getUpcoming(TaskList tasks) {
        LocalDate today = LocalDate.now();
        String dueToday = "";
        String dueTmr = "";
        String overdue = "";
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.getType() == "T" || task.getIsDone()) {
                continue;
            }
            int dateDiff = Period.between(today, task.getDate()).getDays();
            if (dateDiff == 0) {
                dueToday += "\t" + (i+1) + ". "+ task.getStatusIconAndDesc() + "\n";
            } else if (dateDiff == 1) {
                dueTmr += "\t" + (i+1) + ". "+ task.getStatusIconAndDesc() + "\n";
            } else if (dateDiff < 0 && task.getType() == "D") {
                overdue += "\t" + (i+1) + ". "+ task.getStatusIconAndDesc() + "\n";
            }
        }
        String input = "";
        if (dueToday.length() > 0) {
            input += "Today's deadline and events:\n" + dueToday;
        }
        if (dueTmr.length() > 0) {
            input += "\nTomorrow's deadline and events:\n" + dueTmr;
        }
        if (overdue.length() > 0) {
            input += "\nOverdue deadlines:\n" + overdue;
        }
        if (input.length() == 0) {
            input = "YAY!! Nothing too urgent at the moment!";
        }
        return input;
    }
}
