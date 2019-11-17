package uiParser;

import java.time.*;
import java.text.DateFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import exceptions.*;

/**
 * Performs the parsing of all user inputs
 */

public class Parser implements DateValidator {
    private String actionType;
    private int taskNo;
    private static Map<String, String> dateMap;
    private DateTimeFormatter dateFormat;

    public Parser() {
        dateMap = new HashMap();
        dateMap.put("Mon", "MONDAY");
        dateMap.put("Tue", "TUESDAY");
        dateMap.put("Wed", "WEDNESDAY");
        dateMap.put("Thu", "THURSDAY");
        dateMap.put("Fri", "FRIDAY");
        dateMap.put("Sat", "SATURDAY");
        dateMap.put("Sun", "SUNDAY");
    }

    /**
     * Parse userInput and return them into a String array d
     * d[0] - type of action to be taken
     * d[1] - task description/number on which the action is to be performed on
     * d[2] - due date/time of the task
     * @param userInput the string text which user keys in
     */
    public String[] parseUi(String userInput) {
        String[] d = userInput.split(" ");
        String[] d1 = {};
        d[0] = d[0].toLowerCase();

        if ((!d[0].equals("bye")) && (!d[0].equals("list"))) {
            try {
                validateTask(d);
            } catch (NotNull e) {
                System.out.println("Not Null Exception: " + e);
                return d;
            }
        }

        if (d[0].equals("bye") || d[0].equals("list")) {
            return d;
        }

        d[1] = userInput.substring(d[0].length()+1);
        for (int i=2; i < d.length; i++) {
            d[i] = null;
        }

        if (d[0].equals("event")) {
            d1 = d[1].split("/at ");
        } else if (d[0].equals("deadline")) {
            d1 = d[1].split("/by ");
        }

        if (d[0].equals("event") || d[0].equals("deadline")) {
            d[1] = d1[0];
            d[2] = d1[1];
        }
        return d;
    }

    public void validateTask(String[] task) throws NotNull {
        if (task.length == 1) {
            throw new NotNull("Task description or number field cannot be empty.");
        } else {
            return;
        }
    }

    @Override
    public boolean isDateValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MMM d YYYY"));
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }

    public LocalDate parseDate(String datetime) {
        String[] d = datetime.split(" ");
        String[] weekdays = new DateFormatSymbols().getShortWeekdays();
        List<String> listDays = Arrays.asList(weekdays);

        if (d[0].equals("today")) {
            return LocalDate.now();
        } else if (d[0].equals("tomorrow")) {
            return LocalDate.now().plusDays(1);
        }

        if (!listDays.contains(d[0])) {
            if (isDateValid(d[0])) {
                return LocalDate.parse((d[0]));
            }
        }
        return (dateFromDay(d[0]));

    }

    public LocalTime parseTime(String datetime) {
        String[] d = datetime.split(" ");
        try {
            return LocalTime.parse(d[1]);
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time input: " + d[1]);
            return null;
        }
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
