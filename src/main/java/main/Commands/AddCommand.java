package main.Commands;

import main.Task;
import main.UI;

import static main.Duke.Tasks;

public class AddCommand extends Command {

    public  AddCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        Task t = new Task(input);
        Tasks.add(t);
        UI.addedCommand((input));

    }

}
