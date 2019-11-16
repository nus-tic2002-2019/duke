import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleCommand extends Command {

    ScheduleCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(taskItem.substring(9).isEmpty()){
            throw new DukeException("Schedule command can't be empty");
        }
        LocalDate date = Parser.convertStringToDate(taskItem.substring(9));
        ArrayList<Task> scheduleWithTime = new ArrayList<>();
        ArrayList<Task> scheduleWithoutTime = new ArrayList<>();
        ArrayList<Task> deadlines = new ArrayList<>();



    }

}
