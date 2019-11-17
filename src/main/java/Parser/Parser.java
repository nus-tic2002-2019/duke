package Parser;

import Commands.*;
import Exception.DukeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 *
 */
public class Parser {
    /**
     *
     * @param fullCommand
     * @return
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String keyCommand = fullCommand.split(" ")[0].toLowerCase();
        switch(keyCommand){
            case "exit":
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(fullCommand);
            case "todo":
                return new NewTodoCommand(fullCommand);
            case "event":
                return new NewEventCommand(fullCommand);
            case "deadline":
                return new NewDeadlinesCommand(fullCommand);
            case "done":
                return new DoneCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "schedule":
                return new ScheduleCommand(fullCommand);
            case "hi":
            case "help":
            case "clearlist":
                return new OtherCommand(keyCommand);
            default:
                throw new DukeException("Unknown command! Please type 'help' for list of command!");
        }
    }

    /**
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static LocalDate convertStringToDate(String text) throws DukeException {

        LocalDate d1;
        if(!text.contains("-")){
            return checkForOtherWording(text);
        }
        try {
            d1 = LocalDate.parse(text);
        }
        catch (DateTimeParseException e){
            throw new DukeException("Please set date as YYYY-MM-DD");
        }
        return d1;
    }

    /**
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static LocalDate checkForOtherWording(String text) throws DukeException {
        text = text.toLowerCase();
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfToday = today.getDayOfWeek();
        DayOfWeek commandDay = getCommandDay(text);
        int dayOfTodayIntValue = dayOfToday.getValue();
        int commandDayIntValue = commandDay.getValue();
        int date_diff = (commandDayIntValue + 7 - dayOfTodayIntValue) % 7;
        LocalDate d1 = today.plus(date_diff, ChronoUnit.DAYS);
        return d1;

    }

    /**
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static DayOfWeek getCommandDay(String text) throws DukeException {
        switch(text){
            case "mon":
            case "monday":
                return DayOfWeek.MONDAY;
            case "tues":
            case "tuesday":
                return DayOfWeek.TUESDAY;
            case "wed":
            case "wednesday":
                return DayOfWeek.WEDNESDAY;
            case "thurs":
            case "thursday":
                return DayOfWeek.THURSDAY;
            case "fri":
            case "friday":
                return DayOfWeek.FRIDAY;
            case "sat":
            case "saturday":
                return DayOfWeek.SATURDAY;
            case "sun":
            case "sunday":
                return DayOfWeek.SUNDAY;
            default:
                throw new DukeException("Please state date");
        }
    }

    /**
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static LocalTime convertStringToTime(String text) throws DukeException {

        LocalTime t1;
        if(!text.contains(":")){
            text = convertTimeTextToTimeStyle(text);
        }
        try {
            t1 = LocalTime.parse(text);
        }
        catch (DateTimeParseException e){
            throw new DukeException("Please set time as hhmm or hh:mm e.g.1301 or 13:01");
        }
        return t1;
    }

    /**
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static String convertTimeTextToTimeStyle(String text) throws DukeException {
        if (!text.substring(4).isEmpty()){
            throw new DukeException("Invalid time! Please keep it to 4 digit");
        }
        String hour = text.substring(0,2);
        String min = text.substring(2,4);
        String newTime = hour + ":" + min;
        return newTime;
    }

}
