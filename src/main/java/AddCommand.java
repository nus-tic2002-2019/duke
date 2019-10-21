public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        // TODO Auto-generated method stub
        tasks.addTask(task);
        ui.showAdd(task, tasks.getSize());

        try {
            storage.save(tasks);
        } catch(DukeException e) {
            throw e;
        }
    }

    @Override
    public boolean isExit() {
        //TODO Auto-generated method stub
        return false;
    }

}
