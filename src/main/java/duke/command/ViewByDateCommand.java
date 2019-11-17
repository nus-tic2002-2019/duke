package duke.command;

import duke.others.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class ViewByDateCommand extends Command {
    protected LocalDate date;
    public ViewByDateCommand(LocalDate date) {
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input =  getListByDate(tasks, this.date);
        if (input.length() == 0) {
            input = Messages.LIST_EMPTY;
        }
        else {
            input = Messages.LIST_HEADER + input;
        }
        ui.print(input);
    }

    private String getListByDate(TaskList tasks, LocalDate date) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDate().equals(date)) {
                input += "\t" + (i+1) + ". "+ tasks.get(i).getStatusIconAndDesc() + "\n";
            }
        }
        return input;
    }
}
