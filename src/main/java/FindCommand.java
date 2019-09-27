public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if(fullCommand.substring(5).isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for Find");
        }
        tasks.findTask(fullCommand.substring(5));
    }
}
