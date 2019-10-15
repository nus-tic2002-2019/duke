package subclass;

import java.io.IOException;
import java.text.ParseException;

public class DeadlineCommand extends Command {
    public static final String INPUT = "deadline";
    //private Deadline deadline;

    public DeadlineCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            String input_items = Parser.parseTask(input);
            String by = Parser.parseDeadline_by(input);
            String input_string = Parser.parseTask_description(input_items);
            Task tmp = Task.add_task(new Deadline(input_string, by));
            Ui.showLine();
            Ui.displayGotIt();
            System.out.println("\t\t" + tmp);
            System.out.println("\t\tNow you have " + Task.word_count + " tasks in list.");
            Ui.showLine();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.displayDeadlineEventError();
            Ui.showLine();
        } catch (ParseException e) {
            Ui.showLine();
            Ui.displayError();
            Ui.displayError_dateFormat();
            Ui.showLine();

        }

    }
}
