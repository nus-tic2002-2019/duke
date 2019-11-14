package main.commands;

import main.DukeException;
import main.UI;

import java.io.IOException;

import static main.Duke.Tasks;

public class ClearCommand extends Command {

    public ClearCommand() throws IOException, DukeException {
        execute(Tasks);
    }

    @Override
    public void execute(Object input) throws DukeException, IOException {
        Tasks.clear();
        UI.clear();
    }
}
