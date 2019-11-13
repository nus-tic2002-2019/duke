package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ViewAllCommand extends Command {
    protected boolean viewAll = true;
    protected static final String LIST_HEADER = "Yessir! Here is the list!\n";

    public ViewAllCommand() {
    }

    public ViewAllCommand(boolean status) {
        this.viewAll = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = "";
        if (tasks.isEmpty()) {
            input = "No duke.task~ ^o^";
        } else if (viewAll) {
            input = getAll(tasks);
        } else if (!viewAll) {
            input = getPending(tasks);
        }
        ui.print(input);
    }

    private String getAll(TaskList tasks) {
        String input =  LIST_HEADER;
        for (int i = 0; i < tasks.size(); ++i) {
            input += "\t" + (i + 1) + ". " + tasks.get(i).getStatusIconAndDesc() + "\n";
        }
        return input;
    }

    private String getPending(TaskList tasks) {
        String input = LIST_HEADER;
        for (int i = 0; i < tasks.size(); ++i) {
            if (!tasks.get(i).getIsDone()) {
                input += "\t" + (i+1) + ". "+ tasks.get(i).getStatusIconAndDesc() + "\n";
            }
        }
        return input;
    }
}
