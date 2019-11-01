package storage;

import exception.DukeException;
import tasklist.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String fileName;
    protected File taskFile;
    private Scanner loadTask;
    private boolean isDone;
    private taskList task = new taskList();
    private int statusValue;
    private int indexOfBracket, indexOfDate, indexOfEndBracket;
    Class c;
    private String taskDescription, taskDate;

    public Storage (String fileName){
            this.fileName = fileName;
            taskFile = new File(fileName);
    }


    public void load() throws DukeException, FileNotFoundException{
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
    }

    public void save(String fileName, String task, String description, String dateTime, boolean status) throws IOException{
        int statusValue = status ? 1:0;
        FileWriter taskWrite = new FileWriter(fileName, true);

        taskWrite.append ("\r\n" + task + " | " + statusValue + " | " + description + " |" + dateTime);
        taskWrite.close();
    }

    public void saveList(String fileName, taskList tasks) throws IOException{
      // FileWriter taskSave = new FileWriter(fileName);
        ArrayList<Task> taskList = tasks.getList();
        for (Task eachTask:taskList){
            c = eachTask.getClass();

            switch(eachTask.getClass().getSimpleName()){
                case "Todo":
                    taskDescription = eachTask.getDescription().substring(6);
                    System.out.println(taskDescription);
                    break;
                case "Deadlines":
                    indexOfBracket = eachTask.getDescription().indexOf("(");
                    indexOfDate = eachTask.getDescription().indexOf(":");
                    indexOfEndBracket = eachTask.getDescription().indexOf(")");
                    taskDescription = eachTask.getDescription().substring(6,indexOfBracket);
                    taskDate = eachTask.getDescription().substring(indexOfDate+1, indexOfEndBracket);
                    statusValue = eachTask.getStatus() ? 1:0;
                    System.out.println("D | " + statusValue + " | " + taskDescription + " | " + taskDate);
                    break;
                case "Event":
                    indexOfBracket = eachTask.getDescription().indexOf("(");
                    indexOfDate = eachTask.getDescription().indexOf(":");
                    indexOfEndBracket = eachTask.getDescription().indexOf(")");
                    taskDescription = eachTask.getDescription().substring(6,indexOfBracket);
                    taskDate = eachTask.getDescription().substring(indexOfDate+1, indexOfEndBracket);
                    statusValue = eachTask.getStatus() ? 1:0;
                    System.out.println("E | " + statusValue + " | " + taskDescription + " | " + taskDate);
                    System.out.println(taskDescription + statusValue);
                    break;
            }
            //System.out.println(eachTask.getDescription() + eachTask.getClass().getSimpleName());
            //taskSave.write("\r\n" + task);
        }
      //  taskSave.close();
    }
}

/*
T | 1 | read book
D | 0 | return book | June 6th
E | 0 | project meeting | Aug 6th 2-4pm
T | 1 | join sports club
 */