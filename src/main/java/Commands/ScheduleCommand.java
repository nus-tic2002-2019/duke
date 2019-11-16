package Commands;

import Exception.DukeException;
import Parser.Parser;
import Storage.Storage;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.TaskList;
import Tasks.TaskType;
import UI.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class ScheduleCommand extends Command {

    public ScheduleCommand(String taskDes){
        super(taskDes);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            taskItem.substring(9);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new DukeException("Schedule command can't be empty");
        }
        LocalDate date = Parser.convertStringToDate(taskItem.substring(9));
        ArrayList<Events> scheduleWithTime = new ArrayList<>();
        ArrayList<Events> scheduleWithoutTime = new ArrayList<>();
        ArrayList<Deadlines> deadlines = new ArrayList<>();
        for(int i = 0; i < tasks.getSize(); i++){
            if(tasks.getTask(i).getTaskType() == TaskType.TODOS){
                continue;
            }
            if(tasks.getTask(i).getTaskType() == TaskType.DEADLINES){
                Deadlines deadline = (Deadlines)tasks.getTask(i);
                if(deadline.getDate().isEqual(date)) {
                    deadlines.add(deadline);
                }
                continue;
            }
            Events event = (Events)tasks.getTask(i);
            if(!event.getDate().isEqual(date)){
                continue;
            }
            if(!event.isHasTime()){
                scheduleWithoutTime.add(event);
                continue;
            }
            scheduleWithTime.add(event);
        }
        sort_TaskByTime_deadlines(deadlines);
        sort_TaskByTime_events(scheduleWithTime);
        ui.displaySchedule(scheduleWithTime, scheduleWithoutTime, deadlines, date);
    }

    private void sort_TaskByTime_deadlines(ArrayList<Deadlines> deadlines) throws DukeException {
        int indexOfDeadlineWithoutTime = deadlines.size()-1;
        for(int j = 0; j < deadlines.size() - 1; j++) {
            boolean isSorted = true;
            for (int i = 0; i < deadlines.size() - j - 1; i++) {
                Deadlines deadline1 = deadlines.get(i);
                Deadlines deadline2 = deadlines.get(i+1);
                //shift those deadline without time to the back
                if(!deadline1.isHasTime()){
                    int lastIndex = deadlines.size()-j-1;
                    Collections.swap(deadlines, i, lastIndex);
                    isSorted = false;
                    indexOfDeadlineWithoutTime = lastIndex;
                    continue;
                }
                //prevent comparing with deadline without time
                if(!deadline2.isHasTime()){
                    continue;
                }
                if (deadline1.getTime().isAfter(deadline2.getTime())) {
                    Collections.swap(deadlines, i, i+1);
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
        //make the order of those without time right again
        for (int k = 0; k < (deadlines.size()-indexOfDeadlineWithoutTime)/2; k++){
            Collections.swap(deadlines, indexOfDeadlineWithoutTime, deadlines.size()-k-1);
            indexOfDeadlineWithoutTime++;
        }
    }
    private void sort_TaskByTime_events(ArrayList<Events> events) throws DukeException {
        for(int j = 0; j < events.size() - 1; j++) {
            boolean isSorted = true;
            for (int i = 0; i < events.size() - j - 1; i++) {
                Events event1 = events.get(i);
                Events event2 = events.get(i+1);
                if (event1.getTime().isAfter(event2.getTime())) {
                    Collections.swap(events, i, i+1);
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

}
