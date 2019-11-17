package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeTasks.Task;
import newDuke.DukeTasks.Event;
import newDuke.DukeExceptions.Exception;


/**
 * A Command to create an instance of a Event Task. This will add the Event Task to the TaskList and the Storage file.
 */

public class EventCommand implements Command {
	private String eventDetailsWhole;
    private String eventName;
    private String venue;
	
    public EventCommand(String eventDetailsWhole) {
        this.eventDetailsWhole = eventDetailsWhole;
    }
	
    /**
     * Executes the Event command which creates a Event Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a
     * new Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to
     * print a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */

    public String execute(TaskList taskList, Storage storage) {
		try {
			try {
				String[] eventDetails = eventDetailsWhole.split(" /at ");
				eventName = eventDetails[0].substring(6);
				venue = eventDetails[1];
				Task event = new Event(eventName, venue);
				taskList.addTaskList(event);
				storage.writeToFile(taskList.getTaskList());
				return UI.newTask(taskList.getTaskList());
			} catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
				if ( eventName == null || eventName.trim().length() == 0){
					throw new Exception(Exception.Code.EMPTY_EVENT_DESCRIPTION);
				} else {
					throw new Exception(Exception.Code.MISSING_EVENT_VENUE);
				}
			}
		} catch (Exception e){
			return e.errorDescription();
		}
	}
}
