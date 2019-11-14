package main.commands;

import main.UI;
import main.parsers.ParserText;

public class ByeCommand extends Command<String> {

    public ByeCommand(String input) {
        this.execute(input);
    }

    @Override
    public void execute(String input) {
        UI.bye();
        ParserText.isTrue = false;
    }


}
