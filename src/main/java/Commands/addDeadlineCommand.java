package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Deadlines;
import Tasks.Task;
import Ui.Ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addDeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    private Date dte;

    public addDeadlineCommand(String description, Date dte){
        super(description);
        this.dte = dte;

    }



    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        Task newTask = new Deadlines(description, dte);
        tasklist.addTask(newTask);
        ui.newTask(newTask, tasklist);
    }
}
