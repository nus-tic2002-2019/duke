package command;

import basic.Storage;
import basic.TaskList;
import basic.Ui;

/**
 * Terminates the program.
 */

public class ExitCommand extends Command{
    protected static Ui ui = new Ui();

    private static void exit() {
        ui.showBye();
        System.exit(0);
    }
    @Override
    public boolean isExit(){
        ExitCommand exitCommand = new ExitCommand();
        exit();
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

    }

}
