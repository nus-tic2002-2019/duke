public class ExitCommand extends Command{

    public ExitCommand() {

    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.showEnd();
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return true;
    }

}