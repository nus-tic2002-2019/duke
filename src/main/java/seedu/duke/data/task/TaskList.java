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
     * Constructs a new task list and initialise with the specified task list.
     * @param tasks Task list specified by the user.
     */
    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }
   
    /** 
     * Adds a task to the task list.
     * @param task  The task that is required to be added to the task list.
     */
    public void addToTaskList(Task task){
        this.taskList.add(task);
    }
 
    /** 
     * Deletes a task from the task list.
     * @param task  The task that is required to be removed from the task list.
     * @return Task The task that was removed from the task list.
     */
    public Task deleteFromTaskList(int task){
        return this.taskList.remove(task);
    }
 
    /** 
     * Returns the size of the current task list.
     * @return int  The size of the current task list.
     */
    public static int getSize(){
        return taskList.size();
    }
  
    /** 
     * Returns the task from the specified index from the task list.
     * @param task  The index the task is located at.
     * @return Task The task at the specified index.
     */
    public static Task getTask(int task){
        return taskList.get(task);
    }
   
    /** 
     * Returns all the task in an ArrayList format.
     * @return ArrayList<Task>  The list of tasks in an ArrayList format.
     */
    public ArrayList<Task> getAllTasks(){
        return taskList;
    }
}