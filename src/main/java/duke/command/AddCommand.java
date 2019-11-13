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

public class AddCommand extends Command {
    protected String type;
    protected String desc;
    protected String date;

    public AddCommand(String type, String desc, String date) {
        this.type = type;
        this.desc = desc;
        this.date = date;
    }

    public AddCommand(String type, String desc) {
        this.type = type;
        this.desc= desc;
    }

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
