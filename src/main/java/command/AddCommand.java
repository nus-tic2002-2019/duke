package command;

import task.*;
import error.*;
import tasklist.TaskList;
import ui.*;
import storage.*;
import java.time.LocalDate;


public class AddCommand extends Command{

    /**
     * Constructor for command and description
     * @param command
     * @param description
     */
    public AddCommand(String command, String description){
        super(command, description);
    }

    /**
     * Constructor for command, description and date
     * @param command
     * @param description
     * @param date
     */
    public AddCommand(String command, String description, LocalDate date){
        super(command, description, date);
    }

    // do you really need to throw errors? or just inherit directly from the abstract class

    /**
     * To add new task to the TaskList
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        Task insert = null;
        switch(command) {
            case "event":
                insert = new Event(description, date);
                task.addTask(insert);
                break;
            case "deadline":
                insert = new Deadline(description, date);
                task.addTask(insert);
                break;
            case "todo":
                insert = new Todo(description);
                task.addTask(insert);
                break;
        }
        ui.showAdd(insert, task);
        //should i decouple the save storage to Duke.main()
    }
}
