public class DoneCommand extends Command{

    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{

        tasks.markAsDone(Integer.parseInt(fullCommand.split(" ")[1]));
        ui.changed();
    }
}