package TaskList;

import Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    /**
     * Private Variable for Storing list of Tasks.
     */
    private ArrayList<Task> tasklist = new ArrayList<>();


    /**
     * Empty Constructor
     */
    public TaskList(){

    }

    /**
     * Initialize new task list with specified tasks.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasklist = tasks;
    }
    /**
     * Getter for the size of the TaskList
     * @return the size of the TaskList
     */
    public int getSize(){
        return tasklist.size();
    }

    /**
     * Getter for Object reference in TaskList Array
     */
    public Task getTask(int idx){
        return tasklist.get(idx);
    }

    /**
     * Adding new task to the TaskList
     * @param item
     */
    public void addTask(Task item){
        this.tasklist.add(item);
    }

    /**
     * Returns all the task in ArrayList format.
     * @return
     */
    public ArrayList<Task> getAllTasks(){
        return tasklist;
    }

    /**
     * Delete existing task in the ArrayList
     * @param index
     * @return deleted task
     * @throws IndexOutOfBoundsException
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        //what is a good habit return straight ? or not return straight?
        Task to_be_Removed = tasklist.remove(index);
        return to_be_Removed;
    }
}
