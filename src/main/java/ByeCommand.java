public class ByeCommand extends Command {
    public ByeCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

    }

    public boolean isExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
        return true;
    }
}
