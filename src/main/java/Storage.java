import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Storage {

    private static String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public static ArrayList<Task> getListOfTask() throws FileNotFoundException{


        return new ArrayList();
    }
    public static ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = getListOfTask();
            return tasks;
        }catch (FileNotFoundException e) {
            throw new DukeException("Empty list process to create new");
        }

    }

    private static String convert_task_storing(Task task) throws DukeException, IllegalStateException {
        String storingTask;
        switch (task.getTaskType()){
            case EVENTS:
                storingTask = (task.getTaskIndex()+1) + " | E"
                        + " | " + (task.getIsDone() ? "1" : "0")
                        + " | " + task.getTask()
                        + " | " + task.getDate()
                        + System.lineSeparator();
                break;
            case DEADLINES:
                storingTask = (task.getTaskIndex()+1) + " | D"
                        + " | " + (task.getIsDone() ? "1" : "0")
                        + " | " + task.getTask()
                        + " | " + task.getDate()
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
                String storingTask = convert_task_storing(lists.getTask(i));
                fileWrite.write(storingTask);
            }
            fileWrite.close();
        }catch(IOException | DukeException e){
            e.printStackTrace();
        }

    }
}
