package main.java.duke.parser;

import main.java.duke.commands.*;
import main.java.duke.common.Utils;
import main.java.duke.exception.IllegalValueException;
import main.java.duke.task.Deadline;
import main.java.duke.task.Events;
import main.java.duke.task.Todo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT= Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

//    public static final Pattern TASK_TYPE_TODO_ARGS_FORMAT =
//            Pattern.compile("(?<todoDescription>[^/]+)");
//    public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT =
//            Pattern.compile("(?<deadlineDesc>[^/]+)"
//                    + " by/(?<byTime>[^/]+)");
//public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT =
//        Pattern.compile("(?<eventDesc>[^/]+)"
//                + " at/(?<atTime>[^/]+)");
       public static final Pattern KEYWORDS_ARGS_FORMAT =
        Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

        public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT =
                Pattern.compile("(?<deadlineDesc>[^/]+)"
                        + " by/(?<byYear>\\d{4})"+"-"+"(?<byMonth>\\d{2})"+"-"+"(?<byDay>\\d{2})"
                        +" "+"(?<byHour>\\d{2})(?<byMin>\\d{2})");

        public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT =
                Pattern.compile("(?<eventDesc>[^/]+)"
                        + " at/(?<atYear>\\d{4})"+"-"+"(?<atMonth>\\d{2})"+"-"+"(?<atDay>\\d{2})"
                        +" "+"(?<atHour>\\d{2})(?<atMin>\\d{2})");



    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    public static final Pattern TASK_DONE_TIME_FORMAT =
                Pattern.compile("(?<targetIndex>\\d+)"+" on/"
                        +"(?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+"-"+"(?<day>\\d{2})"
                        +" "+"(?<hour>\\d{2})(?<minute>\\d{2})");

    public static final Pattern VIEW_DONE_TASK_BY_TIME_FORMAT =
            Pattern.compile("from/(?<fromTime>[^/]+)"+" to/(?<toTime>[^/]+)");

    /**
     * Parses user input into command for execution.
     *
     * @param inputCommand full user input string
     * @return the command based on the user input
     */
    public Command parse(String inputCommand) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(inputCommand.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type the list to see all the commands.");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch(commandWord){

            case AddCommand.COMMAND_WORD_ONE:
                return prepareAddTodo(arguments);
            case AddCommand.COMMAND_WORD_TWO:
                return prepareAddDeadline(arguments);
            case AddCommand.COMMAND_WORD_THREE:
                return prepareAddEvent(arguments);

            case DoneCommand.COMMAND_WORD:
                return prepareDone(arguments);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);
            case ViewDoneCommand.COMMAND_WORD:
                return prepareViewDone(arguments);
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case HelpCommand.COMMAND_WORD:
            default:
                return new HelpCommand();
        }

        }


    /**
     * Parses arguments in the context of the add todotask command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddTodo(String args) {
        return new AddCommand(new Todo(args.trim()));
    }

    /**
     * Parses arguments in the context of the add deadline task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadline(String args) {
        final Matcher matcher= TASK_TYPE_DEADLINE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }
        return new AddCommand(new Deadline(matcher.group("deadlineDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("byYear")),
                                 Integer.parseInt(matcher.group("byMonth")),
                                 Integer.parseInt(matcher.group("byDay")),
                                 Integer.parseInt(matcher.group("byHour")),
                                 Integer.parseInt(matcher.group("byMin"))) ));
    }

    /**
     * Parses arguments in the context of the add Event task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEvent(String args) {
        final Matcher matcher= TASK_TYPE_EVENT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }


        return new AddCommand(new Events(matcher.group("eventDesc"),
                LocalDateTime.of(Integer.parseInt(matcher.group("atYear")),
                                Integer.parseInt(matcher.group("atMonth")),
                                Integer.parseInt(matcher.group("atDay")),
                                Integer.parseInt(matcher.group("atHour")),
                                Integer.parseInt(matcher.group("atMin"))) ));
    }

    /**
     * Parses argument in the context of the mark a task as done command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDone(String args) {
        try {
         Matcher matcher =TASK_DONE_TIME_FORMAT.matcher(args.trim());
        if (matcher.matches()) {
            int targetIndex=Integer.parseInt((matcher.group("targetIndex")));
            return new DoneCommand(targetIndex,
                    LocalDateTime.of(Integer.parseInt(matcher.group("year")),
                                    Integer.parseInt(matcher.group("month")),
                                    Integer.parseInt(matcher.group("day")),
                                    Integer.parseInt(matcher.group("hour")),
                                    Integer.parseInt(matcher.group("minute")))  );
        }
        else{
            int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DoneCommand(targetIndex,LocalDateTime.now());
        }
        }catch (ParseException pe){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

    }

    /**
     * Parses argument in the context of the delete a task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            assert targetIndex>0 : "Invalid number, the index should be larger than 0.";
            return new DeleteCommand(targetIndex);
        }catch (ParseException pe){
            return  new IncorrectCommand("Error");
        }
    }

    /**
     * Parses arguments in the context of the find task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final  Matcher matcher=KEYWORDS_ARGS_FORMAT.matcher((args.trim()));
        if(!matcher.matches()){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }


    private Command prepareViewDone(String args) {
        try {
            final Matcher matcher = VIEW_DONE_TASK_BY_TIME_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand("This is a incorrect format, " +
                        " you may type 'help' to see all the commands.");
            }
            return new ViewDoneCommand(Utils.getDatetimeFromString(matcher.group("fromTime")),
                    Utils.getDatetimeFromString(matcher.group("toTime")));
        }catch (IllegalValueException ive){
            return new IncorrectCommand("This is a incorrect format, " +
                    " you may type 'help' to see all the commands.");
        }

    }



    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException{
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if(!matcher.matches()){
            throw   new  ParseException("Could not match to the correct index.");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }



    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }


}

