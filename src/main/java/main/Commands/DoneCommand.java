package main.Commands;

import main.Commands.Command;
import main.UI;

import static main.Duke.Tasks;

public class DoneCommand extends Command<String> {

    public  DoneCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {

        int index = Integer.valueOf(input) -1;
        Tasks.get(index).Done();
        UI.completeTask(index);

    }
}
