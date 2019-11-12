public class ExitCommand extends Command{
    public ExitCommand(){

    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
