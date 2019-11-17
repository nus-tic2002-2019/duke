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

/**
 * Schedule Command will find all events and deadlines with the dates that the user input
 */
public class ScheduleCommand extends Command {
    /**
     * Constructs the schedule command
     * @param taskDes
     */
    public ScheduleCommand(String taskDes){
        super(taskDes);
    }

    /**
     * The command will extract the events and deadlines that are on the date the user input
     * It will spilt up into events with time, events without time and deadlines and display them
     * @param tasks the tasklist
     * @param ui the ui
     * @param storage the storage
     * @throws DukeException any expected error
     */
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
            categoriseTask(tasks, scheduleWithTime, scheduleWithoutTime, deadlines, i, date);
        }
        sort_TaskByTime_deadlines(deadlines);
        sort_TaskByTime_events(scheduleWithTime);
        ui.displaySchedule(scheduleWithTime, scheduleWithoutTime, deadlines, date);
    }

    /**
     * Separate function to categorise the task into the different ArrayList
     * @param tasks the task list
     * @param scheduleWithTime the list of event with time
     * @param scheduleWithoutTime the list of event without time
     * @param deadlines the list of deadline
     * @param i the task index
     * @param date the date the user input
     */
    private void categoriseTask(TaskList tasks, ArrayList<Events> scheduleWithTime, ArrayList<Events> scheduleWithoutTime,
                                ArrayList<Deadlines> deadlines, int i, LocalDate date){
        if(tasks.getTask(i).getTaskType() == TaskType.TODOS){
            return;
        }
        if(tasks.getTask(i).getTaskType() == TaskType.DEADLINES){
            Deadlines deadline = (Deadlines)tasks.getTask(i);
            if(deadline.getDate().isEqual(date)) {
                deadlines.add(deadline);
            }
            return;
        }
        Events event = (Events)tasks.getTask(i);
        if(!event.getDate().isEqual(date)){
            return;
        }
        if(!event.isHasTime()){
            scheduleWithoutTime.add(event);
            return;
        }
        scheduleWithTime.add(event);
    }

    /**
     * Do a sort to arrange the deadline by their timing
     * @param deadlines list of deadline with the date the user wants
     * @throws DukeException any expected errors
     */

    private void sort_TaskByTime_deadlines(ArrayList<Deadlines> deadlines) throws DukeException {
        int indexOfDeadlineWithoutTime = deadlines.size()-1;
        for(int j = 0; j < deadlines.size() - 1; j++) { //bubble sort
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

    /**
     * Sort the event based on their time
     * @param events list of events with time
     * @throws DukeException any expected error
     */
    private void sort_TaskByTime_events(ArrayList<Events> events) throws DukeException {
        for(int j = 0; j < events.size() - 1; j++) { //bubble sort
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
