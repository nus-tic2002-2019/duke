import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class NewEventCommand extends Command {

    NewEventCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(6).isEmpty()){
            throw new DukeException("Event task can't be empty");
        }
        if(!taskItem.contains(" /at ")){
            throw new DukeException("Please state /at yyyy-mm-dd");
        }
        try {
            int dividerPosition2 = taskItem.indexOf(" /at ");
            String taskDes = taskItem.substring(6, dividerPosition2);
            String taskTime = taskItem.substring(dividerPosition2 + 5);
            LocalDate d1 = LocalDate.parse(taskTime);
            tasks.addTask(new Events(taskDes, d1));
            storage.save(tasks);
        }
        catch (DateTimeParseException e){
            throw new DukeException("Please set date as YYYY-MM-DD");
        }
    }




}
