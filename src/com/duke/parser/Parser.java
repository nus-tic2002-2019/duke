package com.duke.parser;

import com.duke.commands.*;
import com.duke.task.Deadline;
import com.duke.task.Events;
import com.duke.task.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT= Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

//    public static final Pattern TASK_TYPE_TODO_ARGS_FORMAT =
//            Pattern.compile("(?<todoDescription>[^/]+)");
    public static final Pattern TASK_TYPE_DEADLINE_ARGS_FORMAT =
            Pattern.compile("(?<deadlineDesc>[^/]+)"
                    + " by/(?<byTime>[^/]+)");
    public static final Pattern TASK_TYPE_EVENT_ARGS_FORMAT =
            Pattern.compile("(?<eventDesc>[^/]+)"
                    + " at/(?<atTime>[^/]+)");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");



    public static Command parse(String inputCommand) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(inputCommand.trim());
//        if (!matcher.matches()) {
//            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
//        }

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
        return new AddCommand(new Deadline(matcher.group("deadlineDesc"),
                                           matcher.group("byTime")));
    }
    private Command prepareAddEvent(String args) {
        final Matcher matcher= TASK_TYPE_EVENT_ARGS_FORMAT.matcher(args.trim());
        return new AddCommand(new Events(matcher.group("eventDesc"),
                                         matcher.group("atTime")));
    }

    private Command prepareDone(String args) {
    }


    private Command prepareDelete(String args) {
        final int targetIndex = parseArgsAsDisplayedIndex(args);
        return new DeleteCommand(targetIndex);
    }


    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     */
    private int parseArgsAsDisplayedIndex(String args)  {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());

        return Integer.parseInt(matcher.group("targetIndex"));
    }



}
}
