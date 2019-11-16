package uiParser;
import java.time.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.text.DateFormatSymbols;
import java.util.*;
import exceptions.*;

public class Parser {
    private String actionType;
    private int taskNo;
    static ArrayList<String> taskCommands;
    private static Map<String, String> dateMap;

    public Parser() {
        taskCommands = new ArrayList<>();
        taskCommands.add("todo");
        taskCommands.add("event");
        taskCommands.add("deadline");
        taskCommands.add("done");
        taskCommands.add("delete");
        taskCommands.add("list");
        taskCommands.add("bye");
        taskCommands.add("find");
        dateMap = new HashMap();
        dateMap.put("Mon", "MONDAY");
        dateMap.put("Tue", "TUESDAY");
        dateMap.put("Wed", "WEDNESDAY");
        dateMap.put("Thu", "THURSDAY");
        dateMap.put("Fri", "FRIDAY");
        dateMap.put("Sat", "SATURDAY");
        dateMap.put("Sun", "SUNDAY");
    }

    public String[] parseUi(String userInput) {
        String[] d = userInput.split(" ");
        String[] d1 = {};
        String[] d2 = {};
        //action type in d[0]
        actionType = d[0].toLowerCase();

        try {
            validateCommand(d[0]);
        } catch (InvalidAction e) {
            System.out.println(e);
        }

        /*try {
            validateTask(d[1]);
        } catch (NotNull e) {
            System.out.println("Not Null Exception: " + e);
        }*/

        if (actionType.equals("bye") || actionType.equals("list")) {
            return d;
        }

        //task in d[1]
        d[1] = userInput.substring(actionType.length()+1);
        for (int i=2; i < d.length; i++) {
            d[i] = null;
        }

        if (actionType.equals("event")) {
            d1 = d[1].split("/at ");
            //d2 = d[2].split(" ");
        } else if (actionType.equals("deadline")) {
            d1 = d[1].split("/by ");
        }

        //d[1]=task, d[2]=dateTime
        if (actionType.equals("event") || actionType.equals("deadline")) {
            d[1] = d1[0];
            d[2] = d1[1];
        }
        return d;
    }

    public void validateCommand(String actionType) throws InvalidAction {
        if (!taskCommands.contains(actionType)) {
            throw new InvalidAction("Unrecognized command, I don't know what that means. =(");
        } else {
            return;
        }
    }

    public void validateTask(String task) throws NotNull {
        if (task.isEmpty()) {
            throw new NotNull("The description field for a task cannot be empty.");
        } else {
            return;
        }
    }

    public String[] parseDateTime(String datetime) {
        String[] d = datetime.split(" ");
        return d;
    }

    public LocalDate parseDate(String datetime) {
        String[] d = datetime.split(" ");
        String[] weekdays = new DateFormatSymbols().getShortWeekdays();
        List<String> listDays = Arrays.asList(weekdays);

        if (!listDays.contains(d[0])) {
            return LocalDate.parse((d[0]));
        } else {
            return (dateFromDay(d[0]));
        }
    }

    public LocalTime parseTime(String datetime) {
        String[] d = datetime.split(" ");
        return LocalTime.parse(d[1]);
    }

    public LocalDate dateFromDay(String dayStr) {
        LocalDate current = LocalDate.now();
        int day = DayOfWeek.from(current).getValue();
        String temp = dateMap.get(dayStr);
        int day2 = DayOfWeek.valueOf(temp).getValue();
        int num = 7 - day + day2;
        return current.plusDays(num);
    }
}
