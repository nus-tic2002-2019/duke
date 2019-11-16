import jdk.jfr.Event;

import java.time.LocalDate;
import java.time.LocalTime;
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
        int dividerPosition2 = taskItem.indexOf(" /at ");
        String taskDes = taskItem.substring(6, dividerPosition2);
        String taskDateTime = taskItem.substring(dividerPosition2+5);

        tasks.addTask(eventTimeSetter(taskDes, taskDateTime));
        storage.save(tasks);
    }

    public static Events eventTimeSetter(String taskDes, String taskDateTime) throws DukeException{
        try{
            if (!taskDateTime.contains(" ")) {
                LocalDate d1 = Parser.convertStringToDate(taskDateTime);
                return new Events(taskDes, d1);
            } else {
                int dividerPosition3 = taskDateTime.indexOf(" ");
                String taskDate = taskDateTime.split(" ")[0];
                String taskTime = taskDateTime.substring(dividerPosition3 + 1);
                LocalDate d1 = Parser.convertStringToDate(taskDate);
                LocalTime t1 = Parser.convertStringToTime(taskTime, d1);
                return new Events(taskDes, d1, t1);

            }
        }catch (DateTimeParseException e){
            throw new DukeException("Please set date as YYYY-MM-DD");
        }
    }




}
