package main.commands;

import main.DukeException;
import main.Storage;
import main.UI;

import java.io.IOException;

public class ArchiveCommand extends Command {

    public ArchiveCommand () throws IOException, DukeException {
        execute(null);
    }

    @Override
    public void execute(Object ignored) throws DukeException, IOException {
        Storage.archive();

    }
}
