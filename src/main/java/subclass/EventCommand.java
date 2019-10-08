package subclass;

import java.io.IOException;
import java.text.ParseException;

public class EventCommand extends Command {
    public static final String INPUT = "event";
    //private Event event;

    public EventCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException, ParseException {
        String input_items = Parser.parseTask(input);
        String at = Parser.parseEvent_at(input);
        String input_string = Parser.parseTask_description(input_items);
        Task tmp = Task.add_task(new Event(input_string, at));
        Ui.showLine();
        Ui.displayGotIt();
        System.out.println("\t\t" + tmp);
        System.out.println("\t\tNow you have " + Task.word_count + " tasks in list.");
        Ui.showLine();
    }
}
