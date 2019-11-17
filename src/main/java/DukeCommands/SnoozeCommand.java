package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.Task;
import newDuke.DukeTasks.Deadline;
import newDuke.DukeExceptions.Exception;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * A Command to change the date of a Deadline Task. This will update the Deadline Task to the TaskList and the Storage
 * file.
 */
public class SnoozeCommand implements Command {
	private int taskNum;
	private String oldDateFormatted;
    private String newDateFormatted;

    public SnoozeCommand(int taskNum) {
        this.taskNum = taskNum-1;
    }

    /**
     * Executes the Snooze command which auto adds 1 week to the old deadline.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
	 * @return a newSnooze message into the console.
     */
    public String execute(TaskList taskList, Storage storage) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
		Task t = taskList.getTask(taskNum);
        oldDateFormatted = t.getDate();
		LocalDate oldLocalDate = LocalDate.parse(oldDateFormatted, formatter);
		LocalDate newLocalDate = oldLocalDate.plusDays(7);
		newDateFormatted = newLocalDate.format(formatter);
        System.out.println(newDateFormatted);

		return UI.snooze(t);
		
    }
}