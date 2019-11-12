import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public static LocalDate convert_string_to_dateTime(String text) throws DukeException {
        LocalDate today = LocalDate.now();
        LocalDate d1;
        if(text.contains("-")){

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

    public static LocalDate check_for_other_wording(String text){
        text = text.toLowerCase();
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        DayOfWeek commandDay;
        int dayOfWeekIntValue = dayOfWeek.getValue();
        LocalDate d1;
        switch(text){
            case "mon":
            case "monday":
                commandDay = DayOfWeek.MONDAY;
                break;
            case "tues":
            case "tuesday":
                commandDay = DayOfWeek.TUESDAY;
                break;
            case "wed":
            case "wednesday":
                commandDay = DayOfWeek.WEDNESDAY;
                break;
            case "thurs":
            case "thursday":
                commandDay = DayOfWeek.THURSDAY;
                break;
            case "fri":
            case "friday":
                commandDay = DayOfWeek.FRIDAY;
                break;
            case "sat":
            case "saturday":
                commandDay = DayOfWeek.SATURDAY;
                break;
            case "sun":
            case "sunday":
                commandDay = DayOfWeek.SUNDAY;
                break;
            default:
        }
        return d1;

    }



}
