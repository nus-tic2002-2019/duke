package Commands;

import Exception.DukeException;
import Tasks.*;
import UI.*;
import Storage.*;
import Parser.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;



public class NewDeadlinesCommand extends Command {
    public NewDeadlinesCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(9);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Deadline command can't be empty");
        }
        if (!taskItem.contains(" /by ")) {
            throw new DukeException("Please state /by yyyy-mm-dd");
        }

        int dividerPosition2 = taskItem.indexOf(" /by ");
        String taskDes = taskItem.substring(9, dividerPosition2);
        String taskDateTime = taskItem.substring(dividerPosition2 + 5);
        tasks.addTask(deadlineTimeSetter(taskDes, taskDateTime));
        storage.save(tasks);
    }
    public static Deadlines deadlineTimeSetter(String taskDes, String taskDateTime) throws DukeException {
        try{
            if (!taskDateTime.contains(" ")) {
                LocalDate d1 = Parser.convertStringToDate(taskDateTime);
                return new Deadlines(taskDes, d1);
            } else {
                int dividerPosition3 = taskDateTime.indexOf(" ");
                String taskDate = taskDateTime.split(" ")[0];
                String taskTime = taskDateTime.substring(dividerPosition3 + 1);
                LocalDate d1 = Parser.convertStringToDate(taskDate);
                LocalTime t1 = Parser.convertStringToTime(taskTime);
                return new Deadlines(taskDes, d1, t1);

            }
        }catch (DateTimeParseException e){
            throw new DukeException("Please set date as YYYY-MM-DD");
        }
    }
}
