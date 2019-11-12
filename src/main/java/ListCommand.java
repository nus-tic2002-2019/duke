public class ListCommand extends Command{
    public ListCommand(){

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i < tasks.getTaskSize() + 1; i++) {
            Task task = tasks.getTasklist().get(i - 1);
            System.out.println("\t"+String.format(i + "." + task, task.getStatusIcon()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
