package com.duke.storage;

import com.duke.task.*;
import com.duke.exception.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskListDecoder {


    public static final Pattern TODO_TXT_FILE_FORMAT=Pattern.compile("T[|](?<isDone>[01])[|](?<taskDesc>[^|]+)");
    public static final Pattern DEADLINE_TXT_FILE_FORMAT=Pattern.compile("D[|](?<isDone>[01])" +
                                                                          "[|](?<taskDesc>[^|]+)" +
                                                                          "[|](?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+
                                                                          "-"+"(?<day>\\d{2})"+
                                                                           " "+"(?<hour>\\d{2})(?<minute>\\d{2})");
    public static final Pattern EVENT_TXT_FILE_FORMAT=Pattern.compile("E[|](?<isDone>[01])" +
                                                                       "[|](?<taskDesc>[^|]+)" +
                                                                       "[|](?<year>\\d{4})"+"-"+"(?<month>\\d{2})"+
                                                                        "-"+"(?<day>\\d{2})"+
                                                                        " "+"(?<hour>\\d{2})(?<minute>\\d{2})");

    public static TaskList decodeTaskList(List<String> encodedTasklist) throws IllegalValueException {

        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTasklist){
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }


    private static Task decodeTaskFromString(String encodedTask) throws IllegalValueException{
        final Matcher matcherTodo = TODO_TXT_FILE_FORMAT.matcher(encodedTask.trim());
        final Matcher matcherDeadline = DEADLINE_TXT_FILE_FORMAT.matcher(encodedTask.trim());
        final Matcher matcherEvent = EVENT_TXT_FILE_FORMAT.matcher(encodedTask.trim());

            if (matcherTodo.matches()) {
                return new Todo(matcherTodo.group("taskDesc"), "1".equals(matcherTodo.group("isDone")));
            } else if (matcherDeadline.matches()) {
                return new Deadline(matcherDeadline.group("taskDesc"),
                        LocalDateTime.of(Integer.parseInt(matcherDeadline.group("year")),
                                        Integer.parseInt(matcherDeadline.group("month")),
                                        Integer.parseInt(matcherDeadline.group("day")),
                                        Integer.parseInt(matcherDeadline.group("hour")),
                                        Integer.parseInt(matcherDeadline.group("minute"))),
                        "1".equals(matcherDeadline.group("isDone")));
            }
            // remember add exception later
            else if (matcherEvent.matches()) {
                return new Events(matcherEvent.group("taskDesc"),
                        LocalDateTime.of(Integer.parseInt(matcherEvent.group("year")),
                        Integer.parseInt(matcherEvent.group("month")),
                        Integer.parseInt(matcherEvent.group("day")),
                        Integer.parseInt(matcherEvent.group("hour")),
                        Integer.parseInt(matcherEvent.group("minute"))),
                        "1".equals(matcherEvent.group("isDone")));
            }
            else throw new IllegalValueException("No match, please check your txt file format");

    }



}
