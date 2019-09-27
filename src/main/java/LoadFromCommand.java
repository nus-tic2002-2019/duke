
public class LoadFromCommand extends Command{
    public LoadFromCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.setPath(storage, GetPathCommand.getPath(fullCommand));
        try {
            new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
        }
    }
}
