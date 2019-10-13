package storage;

import exception.DukeException;
import tasklist.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String fileName;
    protected File taskFile;
    private Scanner loadTask;
    private boolean isDone;
    private taskList task = new taskList();


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
}
