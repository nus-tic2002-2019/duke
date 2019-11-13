import java.io.IOException;

public class DoneCommand extends Command {
    protected int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isEmpty()) {
            throw new DukeException("No outstanding tasks!");
        }
        else if (tasks.size() <= index) {
            throw new DukeException("Please enter a task number between 1 and " + tasks.size());
        }
        else if (tasks.get(index).getIsDone()) {
            throw new DukeException("Task is already done!!");
        }
        else {
            tasks.get(index).markAsDone();
            storage.updateLine(index, index + ";" + Utility.constructInput(tasks.get(index)));
            ui.print("NAISUUUUUUU!!! One task off the list!!\n\t" + tasks.get(index).getStatusIconAndDesc() + "\n");
        }
    }
}
