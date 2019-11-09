package main.Commands;


import main.Commands.Command;
import main.UI;

public class ListCommand extends Command {

    public  ListCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        UI.listTasks();
    }
}
