public class DoneCommand extends Command{
    private int index;

    public DoneCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.doneTask(index);
        //display successful message
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
