package main.java.duke.commands;

import main.java.duke.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * view and list all the tasks which is done during a specific period.
 */
public class ViewDoneCommand extends Command {

    public static final String COMMAND_WORD="viewdone";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": view the finished tasks done during a specific period.\n" +
            "Syntax: "+COMMAND_WORD+" from/TIME to/TIME (all the time format is YYYY-MM-dd HHmm)\n" +
            "Example: "+COMMAND_WORD+" from/2019-08-08 0800 to/2019-12-12 1200";

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

    /**
     * Retrieves all tasks in the TaskList whose finishTime is in a specific period
     * @return list of tasks found
     */
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
