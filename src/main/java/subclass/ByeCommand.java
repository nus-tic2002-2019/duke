package subclass;

import java.io.IOException;

public class ByeCommand extends Command {

    public static final String INPUT = "bye";

    public ByeCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException {
        ui.showLine();
        ui.goodBye();
        ui.showLine();
    }
}
