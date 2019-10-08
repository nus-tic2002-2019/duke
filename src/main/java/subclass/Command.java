package subclass;

import java.io.IOException;
import java.text.ParseException;

public class Command {
    public boolean isExit;
    public String input;

    public Command(boolean isExit, String input) {
        this.isExit = isExit;
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui) throws IOException, DukeException, ParseException {
        //empty;
    }
}
