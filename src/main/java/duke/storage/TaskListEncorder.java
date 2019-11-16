package main.java.duke.storage;

import main.java.duke.task.Task;
import main.java.duke.task.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Encodes the {@code taskList} object into a data file for storage.
 */
public class TaskListEncorder {

    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeTaskList(TaskList toSave){
        final List<String> encodedTasks= new ArrayList<>();
        for(int i=1;i<=toSave.getSize();i++){
            encodedTasks.add(encodeTaskToString(toSave.getTaskByIdx(i)));
        }
        return encodedTasks;

    }

    /**
     * Encodes the {@code task} into a decodable and readable string representation.
     */
    private static String encodeTaskToString(Task task) {
        final StringBuilder encodedTaskBuilder =new StringBuilder();

        encodedTaskBuilder.append(task.getTaskType());
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.isDone() ? "1" : "0");
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getDescription());
        encodedTaskBuilder.append("|");
        if(task.getTaskType()=="D"||task.getTaskType()=="E"){
            encodedTaskBuilder.append(task.getTaskTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            encodedTaskBuilder.append("|");
        }
        if(task.isDone()){
            encodedTaskBuilder.append(task.getFinishTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        }


        return encodedTaskBuilder.toString();


    }


}
