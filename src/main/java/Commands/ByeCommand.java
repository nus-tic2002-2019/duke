package Commands;

import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;

public class ByeCommand extends Command {
    public ByeCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
