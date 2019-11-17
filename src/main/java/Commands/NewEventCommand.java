package Commands;

import Exception.DukeException;
import Parser.Parser;
import Storage.Storage;
import Tasks.Events;
import Tasks.TaskList;
import Tasks.TaskType;
import UI.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 *
 */
public class NewEventCommand extends Command {
    /**
     *
     * @param taskDes
     */
    public NewEventCommand(String taskDes){
        super(taskDes);
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(6);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Event command can't be empty");
        }
        if(!taskItem.contains(" /at ")){
            throw new DukeException("Please state /at yyyy-mm-dd");
        }
        int dividerPosition2 = taskItem.indexOf(" /at ");
        String taskDes = taskItem.substring(6, dividerPosition2);
        String taskDateTime = taskItem.substring(dividerPosition2+5);
        Events event = eventTimeSetter(taskDes, taskDateTime);
        assert event.getTaskType() == TaskType.EVENTS;
        tasks.addTask(event);

        storage.save(tasks);
    }

    /**
     *
     * @param taskDes
     * @param taskDateTime
     * @return
     * @throws DukeException
     */
    public static Events eventTimeSetter(String taskDes, String taskDateTime) throws DukeException {
        try{
            if (!taskDateTime.contains(" ")) {
                LocalDate d1 = Parser.convertStringToDate(taskDateTime);
                return new Events(taskDes, d1);
            } else {
                int dividerPosition3 = taskDateTime.indexOf(" ");
                String taskDate = taskDateTime.split(" ")[0];
                String taskTime = taskDateTime.substring(dividerPosition3 + 1);
                LocalDate d1 = Parser.convertStringToDate(taskDate);
                LocalTime t1 = Parser.convertStringToTime(taskTime);
                return new Events(taskDes, d1, t1);

            }
        }catch (DateTimeParseException e){
            throw new DukeException("Please set date as YYYY-MM-DD");
        }
    }




}
