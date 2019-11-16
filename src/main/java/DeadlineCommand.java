import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * to convert time string to Date when user key in the time
     * @param deadline
     * @return
     * @throws ParseException
     * @throws DukeException
     */

    public static Date convertDateTime(String deadline) throws ParseException, DukeException {
        SimpleDateFormat format = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
        Date date;
            try {
                date = format.parse(deadline);
                return date;
            } catch (ParseException e) {
                System.out.println("Unable to parse using " + format);
                System.out.println("Please use format \"dd-MM-yyyy HH:mm:ss\" ");
                System.out.println("Default date will be inserted instead.");
            }
        return new Date();
    }
    /**
     * save the key in task to array list and to file
     */

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws  DukeException, IOException {
        try {
            if (input.split(" ")[3] == "☹ OOPS!!! The date of a deadline cannot be empty.") {
                throw new DukeException(input);
            }
            deadline = new Deadline(input.split(" ")[1],convertDateTime(input.split(" ")[3]));
            TaskList.addList(deadline);
            ui.showOutputToUser("Got it. I've added this task:\n\t  " + deadline.toString() + "\n\t Now you have " + TaskList.getSize() + " tasks in the list.");
            storage.saveToFile();
        } catch (IndexOutOfBoundsException | DukeException | ParseException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
} 