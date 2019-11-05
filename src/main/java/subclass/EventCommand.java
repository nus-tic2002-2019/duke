/**
 * Records event type tasks, records string after "event" command as task description, and day + time after "/at"
 * Displays error when no input is entered after "event" command.
 * @param input is entered by user after "event" command
 * @param at is date-time/place entered by user after "/at" keyword in command
 */



package subclass;

import java.io.IOException;
import java.text.ParseException;

public class EventCommand extends Command {
    public static final String INPUT = "event";

    public EventCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            String input_items = Parser.parseTask(input);
            String at = Parser.parseEvent_at(input);
            String input_string = Parser.parseTask_description(input_items);
            Task tmp = Task.add_task(new Event(input_string, at));
            Ui.showLine();
            Ui.displayGotIt();
            System.out.println("\t\t" + tmp);
            System.out.println("\t\tNow you have " + Task.word_count + " tasks in list.");
            Ui.showLine();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.displayDeadlineEventError();
            Ui.showLine();
        }

    }
}
