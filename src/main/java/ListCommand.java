public class ListCommand extends Command{

    public ListCommand() {

    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }

}
