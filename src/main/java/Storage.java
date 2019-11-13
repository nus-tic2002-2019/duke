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
                Events event = (Events)task;
                storingTask = (event.getTaskIndex()+1) + " | E"
                        + " | " + (event.getIsDone() ? "1" : "0")
                        + " | " + event.getTask()
                        + " | " + event.getDate()
                        + System.lineSeparator();
                break;
            case DEADLINES:
                Deadlines deadlines = (Deadlines)task;
                storingTask = (deadlines.getTaskIndex()+1) + " | D"
                        + " | " + (deadlines.getIsDone() ? "1" : "0")
                        + " | " + deadlines.getTask()
                        + " | " + deadlines.getDate()
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
