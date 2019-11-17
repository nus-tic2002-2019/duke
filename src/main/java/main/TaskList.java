package newDuke.main;

import newDuke.DukeTasks.Task;
import java.util.ArrayList;

/**
 * Stores all the Tasks in an ArrayList. Contains methods to access and update the ArrayList of Tasks.
 */

public class TaskList {

    private ArrayList<Task> list;

    /**
     * Instantiates a TaskList object with an empty ArrayList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Instantiates a TaskList object with an ArrayList of Task (given as an argument).
     *
     * @param list an ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns a Task at given index of this TaskList.
     *
     * @return task at given index of this TaskList.
     */
    public Task getTask(int i) {
        return this.list.get(i);
    }

    /**
     * Replaces the ArrayList of this TaskList with another ArrayList of Tasks (given as an argument).
     *
     * @param list An ArrayList of Tasks to replace the ArrayList of this TaskList.
     */
    public void updateTaskList(ArrayList<Task> list) {
        this.list = list;
    }
	
   /**
     * Returns the ArrayList of Tasks of this TaskList.
     *
     * @return The ArrayList of Tasks of this TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.list;
    }
	
   /**
     * Adds a single Task to this TaskList.
     *
     * @param task A task to be added
     */
    public void addTaskList(Task task) {
        this.list.add(task);
    }
	
   /**
     * Returns the size of this TaskList.
     *
     * @return Returns the size of this TaskList.
     */
	public int getSize() {
        return list.size();
    }
	
   /**
     * Returns the index of given Task.
     *
     * @return Returns the index of given Task.
     */
	public int indexOf(Task t) {
        return list.indexOf(t);
    }
	
   /**
     * Changes the task at a given index.
     *
     */
	public void changeTask(int index, Task t) {
        list.set(index, t);
    }

}
