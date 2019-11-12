public class ByeCommand extends Command {
    public ByeCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
