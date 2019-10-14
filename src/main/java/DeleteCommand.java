public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        // TODO Auto-generated method stub
        if(index < tasks.getSize()) {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showDelete(task, tasks.getSize());

            try {
                storage.save(tasks);
            } catch(DukeException e) {
                throw e;
            }
        } else
            throw new DukeException("☹ OOPS!!! The description of a delete not found.");
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return false;
    }

}
