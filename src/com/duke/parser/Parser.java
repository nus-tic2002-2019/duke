package com.duke.parser;

import com.duke.commands.*;
import com.duke.task.Deadline;
import com.duke.task.Events;
import com.duke.task.Todo;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT= Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

//    public static final Pattern TASK_TYPE_TODO_ARGS_FORMAT =
//            Pattern.compile("(?<todoDescription>[^/]+)");
//    public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT =
//            Pattern.compile("(?<deadlineDesc>[^/]+)"
//                    + " by/(?<byTime>[^/]+)");
//public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT =
//        Pattern.compile("(?<eventDesc>[^/]+)"
//                + " at/(?<atTime>[^/]+)");

        public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT =
                Pattern.compile("(?<deadlineDesc>[^/]+)"
                        + " by/(?<byYear>\\d{4})"+"-"+"(?<byMonth>\\d{2})"+"-"+"(?<byDay>\\d{2})"
                        +" "+"(?<byHour>\\d{2})(?<byMin>\\d{2})");

        public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT =
                Pattern.compile("(?<eventDesc>[^/]+)"
                        + " at/(?<atYear>\\d{4})"+"-"+"(?<atMonth>\\d{2})"+"-"+"(?<atDay>\\d{2})"
                        +" "+"(?<atHour>\\d{2})(?<atMin>\\d{2})");



    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");



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
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case HelpCommand.COMMAND_WORD:
            default:
                return new HelpCommand();
        }

        }



    private Command prepareAddTodo(String args) {
        return new AddCommand(new Todo(args.trim()));
    }
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

    private Command prepareDone(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DoneCommand(targetIndex);
        }catch (ParseException pe){
            return new IncorrectCommand("Error");
        }
    }


    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        }catch (ParseException pe){
            return  new IncorrectCommand("Error");
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

