abstract public class Command {

    abstract public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException;
    abstract public boolean isExit();

}
