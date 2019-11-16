import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException{
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
            case "hi":
            case "help":
            case "clearlist":
                return new OtherCommand(keyCommand);
            default:
                throw new DukeException("Unknown command! Please type 'help' for list of command!");
        }
    }


    public static LocalDate convertStringToDate(String text) throws DukeException {
        LocalDate today = LocalDate.now();
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

        if(today.isAfter(d1)) {
            throw new DukeException("Date should be a future day.");
        }
        return d1;
    }


    public static LocalDate checkForOtherWording(String text) throws DukeException{
        text = text.toLowerCase();
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfToday = today.getDayOfWeek();
        DayOfWeek commandDay = getCommandDay(text);
        int dayOfWeekIntValue = dayOfToday.getValue();
        int commandDayIntValue = commandDay.getValue();
        int date_diff = (commandDayIntValue - dayOfWeekIntValue)%7;
        LocalDate d1 = today.plus(date_diff, ChronoUnit.DAYS);
        return d1;

    }

    public static DayOfWeek getCommandDay(String text) throws DukeException{
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

    public static LocalTime convertStringToTime(String text, LocalDate taskDate) throws DukeException{
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        LocalTime t1;
        if(!text.contains(":")){
            text = convertTimeTextToTimeStyle(text);
        }
        try {
            t1 = LocalTime.parse(text);
        }
        catch (DateTimeParseException e){
            throw new DukeException("Please set time as 1301 or 13:01");
        }

        if(today.isEqual(taskDate) && now.isAfter(t1)) {
            throw new DukeException("Date and Time should be a future Date and Time.");
        }
        return t1;
    }

    public static String convertTimeTextToTimeStyle(String text) throws DukeException{
        if (!text.substring(4).isEmpty()){
            throw new DukeException("Invalid time! Please keep it to 4 digit");
        }
        String hour = text.substring(0,2);
        String min = text.substring(2,4);
        String newTime = hour + ":" + min;
        return newTime;
    }

}
