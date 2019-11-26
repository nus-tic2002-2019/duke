import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> taskList;

    /**
     * Constructs new arraylist
     */
    public TaskList() {
        TaskList.taskList = new ArrayList<>();
    }
    /**
     * 
     * @param tasks
     */

    public TaskList(ArrayList<Task> tasks) {
        TaskList.taskList = tasks;
    }
    /**
     * add task in to list
     * @param task
     */
    public static void addList(Task task) {
        TaskList.taskList.add(task);
    }
    /**
     * remove task in list
     * @param task
     * @return
     */
    public static Task deleteList(int task) {
        return TaskList.taskList.remove(task);
    }
    /**
     * return total number of item in list
     * @return
     */
    public static int getSize() {
        return taskList.size();
    }
    /**
     * gets the name of the task in the location 
     * @param task
     * @return
     */
    public static Task getTask(int task) {
        return taskList.get(task);
    }
    /**
     * list out all the tasks in array list
     * @return
     */
    public ArrayList<Task> getAllTasks(){
        return taskList;
    }
} 
