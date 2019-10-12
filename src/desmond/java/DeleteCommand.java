public class DeleteCommand extends Command{

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{

        tasks.removeTask(Integer.parseInt(fullCommand.split(" ")[1]));
        ui.changed();
    }
}
