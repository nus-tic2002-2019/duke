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
 * A Command to create an instance of a Deadline Task. This will add the Deadline Task to the TaskList and the Storage
 * file.
 */
public class DeadlineCommand implements Command {
    private String deadlineDetailsWhole;
    private String deadlineName;
    private String dateFormatted;

    public DeadlineCommand(String deadlineDetailsWhole) {
        this.deadlineDetailsWhole = deadlineDetailsWhole;
    }

    /**
     * Executes the Deadline command which creates a Deadline Task.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
	 * @return a newTask message into the console.
     */
    public String execute(TaskList taskList, Storage storage) {
		try {
			try {
				String[] deadlineDetails = deadlineDetailsWhole.split(" /by ");
				deadlineName = deadlineDetails[0].substring(9);
				LocalDate date;
				
				if (deadlineDetails[1].equals("today")){
					date = java.time.LocalDate.now();
				} else {
					date = LocalDate.parse(deadlineDetails[1]);
				}
				dateFormatted = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
				Task deadline = new Deadline(deadlineName, dateFormatted);
				taskList.addTaskList(deadline);
				storage.writeToFile(taskList.getTaskList());
				return UI.newTask(taskList.getTaskList());
			} catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
				if ( deadlineName == null || deadlineName.trim().length() == 0){
					throw new Exception(Exception.Code.EMPTY_DEADLINE_DESCRIPTION);
				} else {
					throw new Exception(Exception.Code.MISSING_DEADLINE_DATETIME);
				}
			} catch (DateTimeParseException e){
				throw new Exception(Exception.Code.WRONG_DEADLINE_FORMAT);
			}
		} catch (Exception e){
			return e.errorDescription();
		}
    }
}
