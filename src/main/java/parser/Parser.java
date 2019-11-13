package parser;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.UnknownCommand;
import command.ViewAllCommand;
import others.DukeException;
import others.Utility;

public class Parser {

    public static final String KEYWORD_DONE = "done";
    public static final String KEYWORD_DELETE = "delete";
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_DEADLINE = "deadline";
    public static final String KEYWORD_EVENT = "event";
    public static final String ERR_NOT_A_INT = "Please enter an integer";

    public Parser() {
    }

    public static Command parse(String input) throws DukeException {
        String keyword = getKeyword(input.trim());
        String parameter = "";
        String desc = "";
        switch (keyword) {
            case ("bb"):
            case ("bye"):
                return new ExitCommand();
            case ("list"):
                return new ViewAllCommand();
            case("pending"):
                return new ViewAllCommand(false);
            case(KEYWORD_DONE):
                parameter = removeKeyword(input, KEYWORD_DONE);
                if (Utility.isNumber(parameter)) {
                    return new DoneCommand( Integer.parseInt(parameter) - 1);
                }
                else {
                    throw new DukeException(ERR_NOT_A_INT);
                }
            case(KEYWORD_DELETE):
                parameter = removeKeyword(input, KEYWORD_DELETE);
                if (Utility.isNumber(parameter)) {
                    return new DeleteCommand( Integer.parseInt(parameter) - 1);
                }
                else {
                    throw new DukeException(ERR_NOT_A_INT);
                }
            case(KEYWORD_DEADLINE):
            case(KEYWORD_EVENT):
                checkDescAndDate(input, keyword);
                desc = removeKeyword(input, keyword);
                int delimiterIndex = desc.lastIndexOf("/");
                String date = desc.substring(delimiterIndex + 1);
                desc = desc.substring(0, delimiterIndex).trim();
                return new AddCommand(keyword, desc, date);
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
        return input;
    }

    private static String removeKeyword(String input, String keyword) {
        return input.replace(keyword, "").trim();
    }

    private static void checkDescAndDate(String input, String keyword) throws DukeException {
        String desc = removeKeyword(input, keyword);
        checkDesc(desc, keyword);
        int delimiterIndex = desc.lastIndexOf("/");
        if (delimiterIndex == -1) {
            throw new DukeException("Please enter a date and lead it with \"/\"");
        }
        String date = desc.substring(delimiterIndex + 1);
        if (date.length() == 0)  {
            throw new DukeException("Please include a date after the \"/\" :)");
        }
    }

    private static void checkDesc(String input, String keyword) throws DukeException {
        String desc = removeKeyword(input, keyword);
        if (desc.length() == 0 || desc.charAt(0) == '/') {
            throw new DukeException("task.Task description cannot be empty!!");
        }
    }
}
