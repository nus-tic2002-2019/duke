package Storage;

import Commands.NewDeadlinesCommand;
import Commands.NewEventCommand;
import Tasks.*;
import Exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Storage {

    private static String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    private static Task creatingEventOrDeadline(String taskType, String taskDes, String taskDateTime) throws DukeException {
        if(!(taskType.contains("E") || taskType.contains("D"))){
            throw new DukeException("Unknown task Type");
        }
        if(taskType.contains("E")){
            return NewEventCommand.eventTimeSetter(taskDes, taskDateTime);
        }
        return NewDeadlinesCommand.deadlineTimeSetter(taskDes, taskDateTime);

    }

    private static Task convertTaskFromFile(String text) throws DukeException {
        Task task;
        int firstDivider = text.indexOf("| ");
        String taskType = text.substring(firstDivider + 2, firstDivider + 3);
        String taskDoneString = text.substring(firstDivider + 6, firstDivider +7);
        String taskDetails = text.substring(firstDivider + 10);
        if(taskDetails.contains(" | ")){
            int timeDivider = taskDetails.indexOf(" | ");
            String taskDes = taskDetails.substring(0, timeDivider);
            String taskTime = taskDetails.substring(timeDivider + 3);
            task = creatingEventOrDeadline(taskType, taskDes, taskTime);
            return task;
        }
        if(!taskType.contains("T")){
            throw new DukeException("Unknown task type");
        }
        task = new ToDos(taskDetails);
        if(!(taskDoneString.contains("0")||taskDoneString.contains("1"))) { // to create assertion
            throw new DukeException("Unknown boolean");
        }
        if(taskDoneString.contains("1")){
            task.edit_done(true);
        }

        return task;
    }


    private static ArrayList<Task> getListOfTask() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        if(f.length() == 0){
            return new ArrayList<Task>();
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            Task task = convertTaskFromFile(s.nextLine());
            tasks.add(task);
            task.setTaskIndex(tasks.size() - 1);
        }

        return tasks;
    }
    public static ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = getListOfTask();
            return tasks;
        }catch (FileNotFoundException e) {
            throw new DukeException("Empty list process to create new");
        }

    }

    private static String convertTaskStoring(Task task) throws DukeException, IllegalStateException {
        String storingTask;
        switch (task.getTaskType()){
            case EVENTS:
                Events event = (Events)task;
                storingTask = (event.getTaskIndex()+1) + " | E"
                        + " | " + (event.getIsDone() ? "1" : "0")
                        + " | " + event.getTask()
                        + " | " + event.getDateTimeString()
                        + System.lineSeparator();
                break;
            case DEADLINES:
                Deadlines deadlines = (Deadlines)task;
                storingTask = (deadlines.getTaskIndex()+1) + " | D"
                        + " | " + (deadlines.getIsDone() ? "1" : "0")
                        + " | " + deadlines.getTask()
                        + " | " + deadlines.getDateTimeString()
                        + System.lineSeparator();
                break;
            case TODOS:
                storingTask = (task.getTaskIndex()+1) + " | T"
                        + " | " + (task.getIsDone() ? "1" : "0")
                        + " | " + task.getTask()
                        + System.lineSeparator();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task.getTaskType());
        }
        return storingTask;
    }

    public static void save(TaskList lists){
        try {
            FileWriter fileWrite = new FileWriter(filePath);
            for (int i = 0; i < lists.getSize(); i++) {
                String storingTask = convertTaskStoring(lists.getTask(i));
                fileWrite.write(storingTask);
            }
            fileWrite.close();
        }catch(IOException | DukeException e){
            e.printStackTrace();
        }

    }
}
