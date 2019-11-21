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
    private String by;


    /**
     * Constructor for Events Command
     * @param description
     * @param by
     */
    public addEventCommand(String description,String by){
        super(description);
        //this.dte = dte;
        this.by = by;
    }



    /**
     * To Show new Event.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        Task newTask = new Events(description, by);
        tasklist.addTask(newTask);
        ui.newTask(newTask, tasklist);
    }
}
