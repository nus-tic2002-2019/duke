package duke.parser;

import duke.command.*;
import duke.others.DukeException;
import duke.others.Utility;
import duke.others.DateFormat;
import duke.others.Keyword;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static final String KEYWORD_EXIT_1 = Keyword.EXIT_1;
    public static final String KEYWORD_EXIT_2 = Keyword.EXIT_2;
    public static final String KEYWORD_DONE = Keyword.DONE;
    public static final String KEYWORD_DELETE = Keyword.DELETE;
    public static final String KEYWORD_TODO = Keyword.ADD_TODO;
    public static final String KEYWORD_DEADLINE = Keyword.ADD_DEADLINE;
    public static final String KEYWORD_EVENT = Keyword.ADD_EVENT;
    public static final String KEYWORD_LIST_DATE = Keyword.VIEW_BY_DATE;
    public static final String KEYWORD_LIST = Keyword.VIEW_ALL;
    public static final String KEYWORD_PENDING_1 = Keyword.VIEW_BY_STATUS_1;
    public static final String KEYWORD_PENDING_2 = Keyword.VIEW_BY_STATUS_2;
    public static final String KEYWORD_FIND = Keyword.FIND;
    public static final String KEYWORD_REMINDER = Keyword.REMINDER;
    public static final String KEYWORD_DO_AFTER_1 = Keyword.DO_AFTER_1;
    public static final String KEYWORD_DO_AFTER_2 = Keyword.DO_AFTER_2;
    public static final String ERR_NOT_A_INT = "Please enter an integer";


    /**
     * Parses user input.
     */
    public Parser() {
    }

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string.
     * @return the command based on the user input.
     */
    public static Command parse(String input) throws DukeException, DateTimeParseException {
        String keyword = getKeyword(input.trim());
        String parameter = "";
        String desc = "";
        String date = "";

        switch (keyword) {
            case (KEYWORD_EXIT_1):
            case (KEYWORD_EXIT_2):
                return new ExitCommand();
            case (KEYWORD_LIST):
                return new ViewAllCommand();
            case(KEYWORD_PENDING_1):
            case(KEYWORD_PENDING_2):
                return new ViewByStatusCommand();
            case(KEYWORD_LIST_DATE):
                date = getCleanDateStr(input);
                return new ViewByDateCommand(LocalDate.parse(date));
            case(KEYWORD_FIND):
                parameter = removeKeyword(input, KEYWORD_FIND).trim();
                if (parameter.length() == 0) {
                    throw new DukeException("Please enter a parameter after \"find\"");
                }
                return new FindCommand(parameter);
            case(KEYWORD_REMINDER):
                return new ViewByUpcomingCommand();
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
                parameter = removeKeyword(input, keyword);
                checkDesc(parameter);
                date = getCleanDateStr(input);
                LocalDate dateLocal = LocalDate.parse(date);
                desc = parameter.substring(0, parameter.lastIndexOf("/")).trim();
                return new AddCommand(keyword, desc, dateLocal);
            case(KEYWORD_TODO):
                desc = removeKeyword(input, keyword);
                checkDesc(desc);
                return new AddCommand(keyword, desc);
            case(KEYWORD_DO_AFTER_1):
            case(KEYWORD_DO_AFTER_2):
                parameter = removeKeyword(input, keyword).trim();
                String taskIndex1 = parameter.substring(0,1);
                if (!Utility.isNumber(taskIndex1)) {
                    throw new DukeException(ERR_NOT_A_INT);
                }
                checkTaskIndex(parameter);
                String taskIndex2 = parameter.substring(parameter.lastIndexOf("/") + 1).trim();
                if (!Utility.isNumber(taskIndex2)) {
                    throw new DukeException(ERR_NOT_A_INT);
                }
                return new SetDoAfterCommand(Integer.parseInt(taskIndex1)-1, Integer.parseInt(taskIndex2)-1);

        }
        return new UnknownCommand();
    }

    /**
     * Get keyword of the command based on the user input.
     *
     * @param input full user input string.
     * @return keyword of the command.
     */
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
        else if (input.matches(KEYWORD_FIND + ".*")) {
            return KEYWORD_FIND;
        }
        else if (input.matches(KEYWORD_DO_AFTER_1 + ".*")){
            return KEYWORD_DO_AFTER_1;
        }
        else if (input.matches(KEYWORD_DO_AFTER_2 + ".*")){
            return KEYWORD_DO_AFTER_2;
        }
        return input;
    }

    /**
     * Remove keyword to get the command parameters (if applicable).
     *
     * @param input full user input string.
     * @param keyword command keyword.
     * @return command parameters.
     */
    private static String removeKeyword(String input, String keyword) {
        return input.replace(keyword, "").trim();
    }

    /**
     * Checks if user has input a task description when entering a create task command.
     *
     * @param param command parameter.
     * @throws DukeException if task description is missing from the user input.
     */
    private static void checkDesc(String param) throws DukeException {
        if (param.length() == 0 || param.charAt(0) == '/') {
            throw new DukeException("Task description cannot be empty!!");
        }
    }

    /**
     * Checks if user has input a date when entering a create Event/Deadline command.
     *
     * @param param command parameter.
     * @throws DukeException if user did not lead the date with a "/" character, did not enter a date or enter an
     * invalid date format (accepted format = "yyyy-mm-dd" or "yyyy-m-d").
     */
    private static void checkDate(String param) throws DukeException {
        int delimiterIndex = param.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException("Please enter a date and lead it with \"/\"");
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0)  {
            throw new DukeException("Please include a date after the \"/\" :)");
        }
        if (date.lastIndexOf("-") < 0) {
            throw new DukeException("Duke reads date in " + DateFormat.STANDARD +
                    ". Please separate your dates with \"-\" (eg. 2019-08-15)");
        }
    }

    private static void checkTaskIndex(String param) throws DukeException {
        int delimiterIndex = param.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException("Please enter a task number and lead it with \"/\"\n(eg. doafter 3 /2)");
        }
        String date = param.substring(delimiterIndex + 1);
        if (date.length() == 0)  {
            throw new DukeException("Please include a task number after the \"/\" :)");
        }
    }

    /**
     * Condenses all the methods that needs to be performed to ensure that the date input is correct and return the
     * correct date format (if applicable).
     *
     * @param input full user input string..
     * @return date string in the correct format (yyyy-mm-dd).
     * @throws DukeException from checkDate() method.
     */
    private static String getCleanDateStr(String input) throws DukeException {
        checkDate(input);
        input = input.substring(input.lastIndexOf("/") + 1).trim();
        input = changeDateFormat(input);
        return input;
    }

    /**
     * Change the date parameter into a format that can be parsed to LocalDate.
     *
     * @param date date parameter.
     * @return a date string in "yyyy-mm-dd" format.
     */
    private static String changeDateFormat (String date) {
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
