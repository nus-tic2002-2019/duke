import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> taskList;

    public TaskList() {
        TaskList.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        TaskList.taskList = tasks;
    }

    public static void addList(Task task) {
        TaskList.taskList.add(task);
    }

    public static Task deleteList(int task) {
        return TaskList.taskList.remove(task);
    }

    public static int getSize() {
        return taskList.size();
    }

    public static Task getTask(int task) {
        return taskList.get(task);
    }

    public ArrayList<Task> getAllTasks(){
        return taskList;
    }
} 
