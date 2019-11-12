import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command{
    public ListCommand(){

    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() <= 0) {
            throw new DukeException("Error for command execute!");
        }
        ui.showAllTask(tasks);

    }

}
