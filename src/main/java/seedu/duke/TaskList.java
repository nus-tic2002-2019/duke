import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }

    public void addToTaskList(Task task){
        this.taskList.add(task);
    }

    public Task deleteFromTaskList(int task){
        return this.taskList.remove(task);
    }

    public static int getSize(){
        return taskList.size();
    }

    public static Task getTask(int task){
        return taskList.get(task);
    }

    public ArrayList<Task> getAllTasks(){
        return taskList;
    }
}