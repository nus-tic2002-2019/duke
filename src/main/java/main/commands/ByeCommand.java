package main.commands;

import main.parsers.ParserText;
import main.UI;

public class ByeCommand extends Command<String> {

    public  ByeCommand(String input){
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        UI.bye();
        ParserText.isTrue = false;
    }


}
