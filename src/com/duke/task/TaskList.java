package com.duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    private final List<Task> taskList=new ArrayList<>();

    public TaskList(){ }

    public TaskList(Task[] tasks){
        final List<Task> initialTasks= Arrays.asList(tasks);
        taskList.addAll(initialTasks);
    }


    public void addTask(Task toAdd){
        taskList.add(toAdd);
    }
}
