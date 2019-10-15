package seedu.duke.data.task;

import java.util.ArrayList;

public class TaskList{
    private static ArrayList<Task> taskList;

    /** 
     * Constructs a new task list and initialise with an empty ArrayList.
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
 
    /** 
     * @param tasks
     * @return 
     */
    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }
   
    /** 
     * @param task
     */
    public void addToTaskList(Task task){
        this.taskList.add(task);
    }
 
    /** 
     * @param task
     * @return Task
     */
    public Task deleteFromTaskList(int task){
        return this.taskList.remove(task);
    }
 
    /** 
     * @return int
     */
    public static int getSize(){
        return taskList.size();
    }
  
    /** 
     * @param task
     * @return Task
     */
    public static Task getTask(int task){
        return taskList.get(task);
    }
   
    /** 
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getAllTasks(){
        return taskList;
    }
}