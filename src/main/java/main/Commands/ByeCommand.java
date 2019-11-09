package main.Commands;

import main.Parser;
import main.UI;

public class ByeCommand extends Command<String> {

    public  ByeCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        UI.bye();
        Parser.isTrue = false;
    }


}
