package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.DukeTasks.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Command to print a list of all the Tasks in the TaskList.
 */

public class ListCommand implements Command {

    public ListCommand() {

    }
	
	public boolean isTaskOverdue(Task t){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String oldDateFormatted = t.getDate();
		LocalDate taskDeadlineDate = LocalDate.parse(oldDateFormatted, formatter);
		if ( taskDeadlineDate.isBefore(java.time.LocalDate.now())){
			return true;
		} else {
			return false;
		}
	}
    /**
     * Prints into the console all the Tasks in the TaskList.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */

    public String execute(TaskList taskList, Storage storage) {
		boolean haveOverdueTasks = false;
		System.out.println("Here are the tasks in your list:");
		for (int a=1; a<taskList.getSize()+1;a++){
			if (isTaskOverdue(taskList.getTask(a-1))){
				System.out.println("	"+a+". "+taskList.getTask(a-1)+" !OVERDUE!" );
				haveOverdueTasks = true;
			} else {
				System.out.println("	"+a+". "+taskList.getTask(a-1) );
			}
		}
		if (haveOverdueTasks){
			System.out.println("Please type 'snooze' followed by the Task Number, to postpone overdue task(s) by a week");
		}
        return "\n";
	}
}
