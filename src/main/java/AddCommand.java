public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(task);
        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getTaskSize() + " tasks in the list.");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
