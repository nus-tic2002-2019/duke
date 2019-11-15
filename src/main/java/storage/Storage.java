package storage;

import exception.DukeException;
import tasklist.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    protected String fileName;
    protected static File taskFile;
    private Scanner loadTask;
    private boolean isDone;
    Priority taskPriority;
    /**
     * The Storage Constructor accept the location of the file and creates a new File object for that file
     * @param fileName the full path of the file
     */
    public Storage (String fileName){
            this.fileName = fileName;
            taskFile = new File(fileName);
    }
    /**
     * The loadList method attempts to load the list from the file and store into a list
     * @return it will return a List object which contains the tasks loaded from the file
     */

    public List<Task> loadList() throws DukeException, FileNotFoundException{
        List<Task> tasksList = new ArrayList<>();
        loadTask = new Scanner(taskFile);
        while (loadTask.hasNext()){
            String readFileArr[] = loadTask.nextLine().split(" \\| ", 5);

            //easier understanding of readFileArr parts
            String taskType = readFileArr[0];
            String taskStatus = readFileArr[1];
            String strTaskPriority = readFileArr[2];
            String taskDescription = readFileArr[3];


            if (taskStatus.equals("1")) isDone = true;
            else isDone = false;

            switch(strTaskPriority){
                case "L":
                    taskPriority = Priority.LOW;
                    break;
                case "M":
                    taskPriority = Priority.MEDIUM;
                    break;
                case "H":
                    taskPriority = Priority.HIGH;
                    break;
            }

            switch (taskType) {
                case "T":
                    tasksList.add(new Todo(taskDescription, isDone, taskPriority));
                    break;
                case "D":
                    String dlDate = readFileArr[4];
                    LocalDate deadlineDate = LocalDate.parse(dlDate);
                    tasksList.add(new Deadlines(taskDescription, deadlineDate, isDone));
                    break;
                case "E":
                    String eDateTime = readFileArr[4];
                    String [] eventDateTime = eDateTime.split(" ",2);
                    LocalDate eventDate = LocalDate.parse(eventDateTime[0]);
                    String [] eventStartEndTime = eventDateTime[1].split(" - ");
                    LocalTime eStartTime = LocalTime.parse(eventStartEndTime[0]);
                    LocalTime eEndTime = LocalTime.parse(eventStartEndTime[1]);
                    tasksList.add(new Event(taskDescription, eventDate, eStartTime, eEndTime, isDone));
                    break;
                default:
                    throw new DukeException("unknown task found");
            }
        }
        return tasksList;
    }
    /**
     * The saveList method attempts to save the list to the file
     * @param fileName is the full path of the file
     * @param tasks is the list of tasks which you which to save
     */

    public static void saveList(String fileName, taskList tasks) throws IOException{
      FileWriter taskSave = new FileWriter(fileName);
        ArrayList<Task> taskList = tasks.getList();
        for (Task eachTask:taskList){
            taskSave.write(eachTask.saveToFile());
        }
      taskSave.close();
    }

    public static File getFile(){return taskFile;};
}

/*
T | 1 | L | read book
D | 0 | L | return book | 2019-06-06
E | 0 | L | project meeting | 2019-08-06 22:00 - 23:00
T | 1 | L | join sports club
D | 0 | L | test save file | 2019-11-09

 */