public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.deleteTask(index);
        //display successful message and task count
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getTaskSize() + " tasks in the list.");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
