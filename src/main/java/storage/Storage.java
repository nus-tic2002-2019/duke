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
            String readFileArr[] = loadTask.nextLine().split(" \\| ", 4);

            if (readFileArr[1].equals("1")) isDone = true;
            else isDone = false;
            switch (readFileArr[0]) {
                case "T":
                    tasksList.add(new Todo(readFileArr[2], isDone));
                    break;
                case "D":
                    LocalDate deadlineDate = LocalDate.parse(readFileArr[3]);
                    tasksList.add(new Deadlines(readFileArr[2], deadlineDate, isDone));
                    break;
                case "E":
                    String [] eventDateTime = readFileArr[3].split(" ",2);
                    LocalDate eventDate = LocalDate.parse(eventDateTime[0]);
                    String [] eventStartEndTime = eventDateTime[1].split(" - ");
                    LocalTime eStartTime = LocalTime.parse(eventStartEndTime[0]);
                    LocalTime eEndTime = LocalTime.parse(eventStartEndTime[1]);
                    tasksList.add(new Event(readFileArr[2], eventDate, eStartTime, eEndTime, isDone));
                    break;
                default:
                    throw new DukeException(readFileArr[0]);
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
T | 1 | read book
D | 0 | return book | 2019-06-06
E | 0 | project meeting | 2019-08-06 22:00 - 23:00
T | 1 | join sports club
 */