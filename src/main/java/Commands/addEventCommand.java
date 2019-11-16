package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Events;
import Tasks.Task;
import Ui.Ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addEventCommand extends Command {
    public static final String COMMAND = "event";

    private Date dte;

    public addEventCommand(String description, Date dte){
        super(description);
        this.dte = dte;
    }



    /**
     * To Show new Event.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        Task newTask = new Events(description, dte);
        tasklist.addTask(newTask);
        ui.newTask(newTask, tasklist);
    }
}
