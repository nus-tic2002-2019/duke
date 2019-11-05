package storage;

import exception.DukeException;
import tasklist.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    protected String fileName;
    protected File taskFile;
    private Scanner loadTask;
    private boolean isDone;

    public Storage (String fileName){
            this.fileName = fileName;
            taskFile = new File(fileName);
    }


  /*  public void load() throws DukeException, FileNotFoundException{
        List<Task> tasksList = new ArrayList();
        loadTask = new Scanner(taskFile);
        while (loadTask.hasNext()){
            String readFileArr[] = loadTask.nextLine().split(" \\| ", 4);
            if (readFileArr[1].equals("1")) isDone = true;
            else isDone = false;

            switch (readFileArr[0]) {
                case "T":
                    taskList.addTodo(new Todo(readFileArr[2], isDone));
                    break;
                case "D":
                    taskList.addDeadlines(new Deadlines(readFileArr[2], readFileArr[3], isDone));
                    break;
                case "E":
                    taskList.addEvent(new Event(readFileArr[2], readFileArr[3], isDone));
                    break;
                default:
                    throw new DukeException(readFileArr[0]);
            }
        }
        return tasksList;
    }*/

    public List<Task> load() throws DukeException, FileNotFoundException{
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
                    //LocalDateTime eventDate = LocalDateTime.parse(readFileArr[3], dateFormat);
                    tasksList.add(new Event(readFileArr[2], readFileArr[3], isDone));
                    break;
                default:
                    throw new DukeException(readFileArr[0]);
            }
        }
        return tasksList;
    }

    public void saveList(String fileName, taskList tasks) throws IOException{
      FileWriter taskSave = new FileWriter(fileName);
        ArrayList<Task> taskList = tasks.getList();
        for (Task eachTask:taskList){
            //System.out.println(eachTask.saveToFile());
            taskSave.write(eachTask.saveToFile());

        }
      taskSave.close();
    }
}

/*
T | 1 | read book
D | 0 | return book | 2019-06-06
E | 0 | project meeting | 2019-08-06
T | 1 | join sports club
 */