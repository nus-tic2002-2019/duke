package duke;

/**
 * Constructs a new duke.ExitCommand and initialise with the specified isExit boolean value and input by the user.
 * @param   isExit  A boolean value whether if the exit condition is true.
 * @param   input   A String inputted by the user.
 */

public class ExitCommand extends Command{

    public ExitCommand() {

    }

    /**
     * @param tasks
     * @param ui call duke.UI class to display the message to user
     * @param storage
     */
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.showEnd();
    }

    @Override
    public boolean isExit() {
        // TODO Auto-generated method stub
        return true;
    }

}