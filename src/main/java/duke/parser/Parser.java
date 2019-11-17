package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.UnknownCommand;
import duke.command.ViewAllCommand;
import duke.command.ViewByDateCommand;
import duke.others.DukeException;
import duke.others.Utility;
import duke.others.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static final String KEYWORD_DONE = "done";
    public static final String KEYWORD_DELETE = "delete";
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_DEADLINE = "deadline";
    public static final String KEYWORD_EVENT = "event";
    public static final String ERR_NOT_A_INT = "Please enter an integer";
    public static final String KEYWORD_LIST_DATE = "list date";

    protected static String date;

    public Parser() {
    }

    public static Command parse(String input) throws DukeException, DateTimeParseException {
        String keyword = getKeyword(input.trim());
        String parameter = "";
        String desc = "";
        String date = "";

        switch (keyword) {
            case ("bb"):
            case ("bye"):
                return new ExitCommand();
            case ("list"):
                return new ViewAllCommand();
            case("pending"):
            case("list pending"):
                return new ViewAllCommand(false);
            case(KEYWORD_LIST_DATE):
                date = getDateStr(input);
                return new ViewByDateCommand(LocalDate.parse(date));
            case(KEYWORD_DONE):
                parameter = removeKeyword(input, KEYWORD_DONE);
                if (Utility.isNumber(parameter)) {
                    return new DoneCommand( Integer.parseInt(parameter) - 1);
                } else {
                    throw new DukeException(ERR_NOT_A_INT);
                }
            case(KEYWORD_DELETE):
                parameter = removeKeyword(input, KEYWORD_DELETE);
                if (Utility.isNumber(parameter)) {
                    return new DeleteCommand( Integer.parseInt(parameter) - 1);
                } else {
                    throw new DukeException(ERR_NOT_A_INT);
                }
            case(KEYWORD_DEADLINE):
            case(KEYWORD_EVENT):
                checkDesc(input, keyword);
                desc = removeKeyword(input, keyword);
                date = getDateStr(input);
                LocalDate dateLocal = LocalDate.parse(date);
                desc = desc.substring(0, desc.lastIndexOf("/")).trim();
                return new AddCommand(keyword, desc, dateLocal);
            case(KEYWORD_TODO):
                checkDesc(input, keyword);
                desc = removeKeyword(input, keyword);
                return new AddCommand(keyword, desc);
        }
        return new UnknownCommand();
    }

    private static String getKeyword(String input) {
        if (input.matches(KEYWORD_DONE + ".*")) {
            return KEYWORD_DONE;
        }
        else if (input.matches(KEYWORD_DELETE + ".*")) {
            return KEYWORD_DELETE;
        }
        else if (input.matches(KEYWORD_DEADLINE + ".*")) {
            return KEYWORD_DEADLINE;
        }
        else if (input.matches(KEYWORD_EVENT + ".*")) {
            return KEYWORD_EVENT;
        }
        else if (input.matches(KEYWORD_TODO + ".*")) {
            return KEYWORD_TODO;
        }
        else if (input.matches(KEYWORD_LIST_DATE + ".*")) {
            return KEYWORD_LIST_DATE;
        }
        return input;
    }

    private static String removeKeyword(String input, String keyword) {
        return input.replace(keyword, "").trim();
    }

    private static void checkDesc(String input, String keyword) throws DukeException {
        String desc = removeKeyword(input, keyword);
        if (desc.length() == 0 || desc.charAt(0) == '/') {
            throw new DukeException("Task description cannot be empty!!");
        }
    }

    private static void checkDate(String input) throws DukeException {
        int delimiterIndex = input.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException("Please enter a date and lead it with \"/\"");
        }
        String date = input.substring(delimiterIndex + 1);
        if (date.length() == 0)  {
            throw new DukeException("Please include a date after the \"/\" :)");
        }
        if (date.lastIndexOf("-") < 0) {
            throw new DukeException("Duke reads date in " + DateFormat.STANDARD +
                    ". Please separate your dates with \"-\" (eg. 2019-08-15)");
        }
    }

    private static String getDateStr (String input) throws DukeException {
        checkDate(input);
        input = input.substring(input.lastIndexOf("/") + 1);
        input = getCleanDate(input);
        return input;
    }

    private static String getCleanDate(String date) {
        String day = date.substring(date.lastIndexOf("-")+1);
        if (day.length() == 1) {
            day = "0" + day;
        }
        String month = date.substring(date.indexOf("-")+1, date.lastIndexOf("-"));
        if (month.length() == 1) {
            month = "0" + month;
        }
        String year = date.substring(0,4);
        return year + "-" + month + "-" + day;
    }
}
