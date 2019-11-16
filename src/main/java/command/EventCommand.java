package command;

import task.Event;
import exception.DukeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the event command.
 * */

public class EventCommand extends Command {

    public EventCommand(String fullCommand){
        super(fullCommand);
    }

    /**
     * Format deadline date to dd-mm-yyyy hh:mm:ss.
     * */

    public static Date convertEvent(String deadline){
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
     * Utilizing parent class function to return new event.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for EVENT");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for EVENT");
        }
        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(6, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        tasks.addTask(new Event(itemName,convertEvent(itemName2)));
        ui.changed();
    }
}
