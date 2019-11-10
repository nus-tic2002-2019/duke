package com.duke.storage;

import com.duke.task.Task;
import com.duke.task.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TaskListEncorder {

    public static List<String> encodeTaskList(TaskList toSave){
        final List<String> encodedTasks= new ArrayList<>();
        for(int i=1;i<=toSave.getSize();i++){
            encodedTasks.add(encodeTaskToString(toSave.getTaskByIdx(i)));
        }
        return encodedTasks;

    }

    private static String encodeTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder =new StringBuilder();

        encodedTaskBuilder.append(task.getTaskType());
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.isDone() ? "1" : "0");
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getDescription());
        if(task.getTaskType()=="D"||task.getTaskType()=="E"){
            encodedTaskBuilder.append("|");
            encodedTaskBuilder.append(task.getTaskTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        }

        return encodedTaskBuilder.toString();


    }


}
