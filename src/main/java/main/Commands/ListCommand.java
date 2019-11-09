package main.Commands;

import main.UI;

public class ListCommand extends Command<String> {

    public  ListCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        UI.listTasks();
    }
}
