package duke;

public class FindCommand extends Command {
    String find;

    public FindCommand(String find) {
        this.find = find;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        ui.showFind(tasks.findTask(find));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}