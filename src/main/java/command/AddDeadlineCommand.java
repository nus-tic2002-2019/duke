package command;

import task.Deadline;
import task.Task;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

//break this from AddCommand so as to increase coupling
public class AddDeadlineCommand extends Command{

    public static final String COMMAND_WORD = "deadline";

    private LocalDate date;

    /**
     * Constructor for description, date and priority
     *
     * @param description, date and priority
     */
    public AddDeadlineCommand(String description, LocalDate date, int priority){
        super(description, priority);
        this.date =  date;
    }

    // do you really need to throw errors? or just inherit directly from the abstract class
    /**
     * To add Event Task to the TaskList
     *
     * @param task
     * @param ui
     * @param storage
     */
    public void execute(TaskList task, Ui ui, Storage storage){
        Task insert = new Deadline(description, date, priority);
        task.addTask(insert);
        ui.showAdd(insert, task);
    }

    /**
     * Get date
     * @return LocalDate
     */
    public LocalDate getDate(){
        return this.date;
    }
}

