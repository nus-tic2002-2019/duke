package main.duke.task;

import main.duke.command.*;
import main.duke.exception.DukeMissingDescException;
import main.duke.exception.DukeUnknownException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    /**
     * @param dt_str String representing LocalDateTime
     * @return A LocalDateTime object as closely corresponding to dt_str
     */
    public static LocalDateTime parseDateTimeStr(String dt_str) {
        //early exit.
        if (Parser.isValidISODateTimeStr(dt_str)) {
            return LocalDateTime.parse(dt_str);
        }
        LocalDateTime parsed_time;
        LocalTime time = null;
        LocalDate date = null;

        if (Parser.isValidISODateStr(dt_str)) {
            date = LocalDate.parse(dt_str);
        } else if (Parser.isValidISOTimeStr(dt_str)) {
            time = LocalTime.parse(dt_str);
        } else {  //do magic parsing...
            //2/12/2019 1800
            //dd/mm/yyyy tttt
            String[] temp = dt_str.split(" ");
            if (temp[0].indexOf('/') != -1) {
                date = strToDate(temp[0], '/');
            }
            if (temp[0].indexOf('-') != -1) {
                date = strToDate(temp[0], '-');
            }
            if (temp.length > 1) {
                time = strToTime(temp[1]);
            } else if (date == null){
                time = strToTime(temp[0]);
            }
        }
        parsed_time = LocalDateTime.of((date == null) ? LocalDate.now() : date, (time == null) ? LocalTime.MIDNIGHT : time);
        return parsed_time;
    }

    private static LocalDate strToDate(String s, char split) {
        String[] date_strs = s.split(String.valueOf(split));
        return LocalDate.of(Integer.parseInt(date_strs[2]), Integer.parseInt(date_strs[1]), Integer.parseInt(date_strs[0]));
    }

    private static LocalTime strToTime(String s) {
        LocalTime time;
        if (Parser.isValidISOTimeStr(s))
            time = LocalTime.parse(s);
        else {
            int hr, min;
            if (s.indexOf(':') != -1) {
                String[] time_strs = s.split(":");
                hr = Integer.parseInt(time_strs[0]);
                min = Integer.parseInt(time_strs[1]);
            } else {
                hr = Integer.parseInt(s.substring(0, 2));
                min = Integer.parseInt(s.substring(2));
            }
            time = LocalTime.of(hr, min);
        }
        return time;
    }
    //region validity checks for string parsing.

    private static boolean isValidISODateTimeStr(String s) {
        try {
            LocalDateTime.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidISODateStr(String s) {
        try {
            LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidISOTimeStr(String s) {
        try {
            LocalTime.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    //endregion


    /**
     * @param inputStr raw String representing user's action
     * @return Command that program can execute.
     * @throws DukeUnknownException if Parser is unable to understand inputStr
     * @throws DukeMissingDescException if Parser finds that action is missing a description
     */
    public static Command parseInput(String inputStr) throws DukeUnknownException, DukeMissingDescException {
        Command parsedCommand;
        String[] strings = inputStr.split(" ");
        String firstStr = strings[0].strip().toLowerCase();
        switch (firstStr) {
            case "bye":
                parsedCommand = new ExitCommand();
                break;
            case "list":
                parsedCommand = new ListCommand();
                break;
            case "delete":
            case "done":
                int pos = Integer.parseInt(strings[1]) - 1;
                UpdateCommand.Operation op = UpdateCommand.Operation.valueOf(firstStr.substring(0, 1).toUpperCase() + firstStr.substring(1));
                parsedCommand = new UpdateCommand(op, pos);
                break;
            case "check":
                parsedCommand = new CheckCommand(strings[1]);
                break;
            default:    //task processing.
                if (firstStr.isBlank() || firstStr.isEmpty())
                    throw new DukeUnknownException();
                parsedCommand = new UpdateCommand(UpdateCommand.Operation.Add, processTask(strings));
                break;
        }
        return parsedCommand;
    }

    private static Task processTask(String[] inputStrs) throws DukeUnknownException, DukeMissingDescException {
        String tempStr = "";
        Task createdTask;
        switch (inputStrs[0]) {
            default:
                throw new DukeUnknownException();
            case "todo":
                for (int i = 1; i < inputStrs.length; i++) {
                    tempStr = tempStr.concat(String.format("%s ", inputStrs[i]));
                }
                createdTask = new ToDo(tempStr.stripTrailing()); //remove extra space added on last elem.
                break;
            case "deadline":
            case "event":
                createdTask = parseEventOrDeadline(inputStrs);
                break;
        }
        return createdTask;
    }

    private static Task parseEventOrDeadline(String[] inputStrs) throws DukeMissingDescException {
        String tempStr1 = "", tempStr2 = "";
        int delimiterIndex = Arrays.asList(inputStrs).indexOf((inputStrs[0].equals("event")) ? "/at" : "/by");
        for (int i = 1; i < delimiterIndex; i++) {
            tempStr1 = tempStr1.concat(String.format("%s ", inputStrs[i]));
        }
        for (int i = delimiterIndex + 1; i < inputStrs.length; i++) {
            tempStr2 = tempStr2.concat(String.format("%s ", inputStrs[i]));
        }
        tempStr1 = tempStr1.strip();
        tempStr2 = tempStr2.strip();
        return (inputStrs[0].equals("event")) ? new Event(tempStr1, tempStr2) : new Deadline(tempStr1, tempStr2);
    }
}
