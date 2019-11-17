package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.Messages;

public class ViewAllCommand extends Command {
    protected boolean viewAll = true;


    public ViewAllCommand() {
    }

    public ViewAllCommand(boolean status) {
        this.viewAll = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = Messages.LIST_HEADER;;
        if (tasks.isEmpty()) {
            input = Messages.LIST_EMPTY;
        } else if (viewAll) {
            input += getAll(tasks);
        } else if (!viewAll) {
            input += getPending(tasks);
        }
        ui.print(input);
    }

    private String getAll(TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            input += "\t" + (i + 1) + ". " + tasks.get(i).getStatusIconAndDesc() + "\n";
        }
        return input;
    }

    private String getPending(TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            if (!tasks.get(i).getIsDone()) {
                input += "\t" + (i+1) + ". "+ tasks.get(i).getStatusIconAndDesc() + "\n";
            }
        }
        return input;
    }
}
