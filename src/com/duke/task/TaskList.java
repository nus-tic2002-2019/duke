package com.duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TaskList {

    private final List<Task> taskList=new ArrayList<>();

    public TaskList(){ }

    public TaskList(Task[] tasks){
        final List<Task> initialTasks= Arrays.asList(tasks);
        taskList.addAll(initialTasks);
    }

    public TaskList(Collection<Task> tasks) {
        taskList.addAll(tasks);
    }

    public TaskList(TaskList source){
        taskList.addAll(source.taskList);
    }


    public void addTask(Task toAdd){
        taskList.add(toAdd);
    }

    public Task getTaskByIdx(int taskIdx){
        return taskList.get(taskIdx-1);
    }

    public void removeTask(Task toRemove){
        taskList.remove(toRemove);
    }

    public void removeTask(int toRemoveIdx){
        taskList.remove(toRemoveIdx);
    }

    public int getSize(){
        return taskList.size();
    }
}
