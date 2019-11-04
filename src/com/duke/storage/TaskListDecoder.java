package com.duke.storage;

import com.duke.task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskListDecoder {


    public static final Pattern TODO_TXT_FILE_FORMAT=Pattern.compile("T[|](?<isDone>[01])[|](?<taskDesc>[^|]+)");
    public static final Pattern DEADLINE_TXT_FILE_FORMAT=Pattern.compile("D[|](?<isDone>[01])" +
                                                                          "[|](?<taskDesc>[^|]+)" +
                                                                          "[|](?<time>[^|]+)");
    public static final Pattern EVENT_TXT_FILE_FORMAT=Pattern.compile("E[|](?<isDone>[01])" +
                                                                       "[|](?<taskDesc>[^|]+)" +
                                                                       "[|](?<time>[^|]+)");

    public static TaskList decodeTaskList(List<String> encodedTasklist){

        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTasklist){
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }


    private static Task decodeTaskFromString(String encodedTask){
         final Matcher matcherTodo= TODO_TXT_FILE_FORMAT.matcher(encodedTask.trim());
         final Matcher matcherDeadline=DEADLINE_TXT_FILE_FORMAT.matcher(encodedTask.trim());
         final Matcher matcherEvent=EVENT_TXT_FILE_FORMAT.matcher(encodedTask.trim());
         if(matcherTodo.matches()){
                 return new Todo(matcherTodo.group("taskDesc"),"1".equals(matcherTodo.group("isDone")));
         }
       else if(matcherDeadline.matches()){
            return new Deadline(matcherDeadline.group("taskDesc"),matcherDeadline.group("time"),
                    "1".equals(matcherDeadline.group("isDone")));
        }
       // remember add exception later
        else //if(matcherEvent.matches()){
            return new Events(matcherEvent.group("taskDesc"),matcherDeadline.group("time"),
                    "1".equals(matcherEvent.group("isDone")));
        }



}
