package main.Commands;

import main.TaskLists.Task;
import main.UI;

import static main.Duke.Tasks;

public class AddCommand extends Command<Task> {

    public  AddCommand(Task input){
        this.execute(input);
    }

    @Override
    public void execute(Task input) {

        Tasks.add(input);
        UI.addedCommand(input);

    }

}
