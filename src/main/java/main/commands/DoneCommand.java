package main.commands;

import main.DukeException;
import main.UI;

import static main.Duke.Tasks;

public class DoneCommand extends Command<String> {

    public DoneCommand(String input) throws DukeException {
        this.execute(input);
    }

    @Override
    public void execute(String input) throws DukeException {

        try {
            int index = Integer.valueOf(input) - 1;
            Tasks.get(index).Done();
            UI.completeTask(index);
        } catch (IndexOutOfBoundsException e) {
            UI.dukePrint("\t☹ OOPS!!! I don't seem to have this Task logged");
        }


    }
}
