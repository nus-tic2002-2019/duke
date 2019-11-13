package com.duke.commands;

import com.duke.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewDoneCommand extends Command {

    public static final String COMMAND_WORD="viewdone";

    private LocalDateTime fromTime,toTime;

    public ViewDoneCommand(LocalDateTime fromTime,LocalDateTime toTime){
        this.fromTime=fromTime;
        this.toTime=toTime;
    }

    @Override
    public void execute(){
        final List<Task> foundTasks= getTasksFromTimePeriod(fromTime,toTime);
        if(foundTasks.isEmpty()){
            System.out.print("No Result Found\n");
            return;
        }
        for (int i = 1; i <= foundTasks.size(); i++) {
            System.out.println(i + ". " + foundTasks.get(i-1).toString());
        }

    }


    private List<Task> getTasksFromTimePeriod(LocalDateTime fromTime,LocalDateTime toTime) {
        final List<Task> matchedTasks= new ArrayList<>();
        for(Task task : taskList.getAllTasks()){
            if(task.isDone()&&task.getFinishTime().isAfter(fromTime)&&task.getFinishTime().isBefore(toTime)){
                matchedTasks.add(task);
            }
        }
        return  matchedTasks;
    }

}
