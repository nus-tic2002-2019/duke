package main.commands;

import main.DukeException;
import main.UI;

import static main.Duke.Tasks;
import java.io.IOException;

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
