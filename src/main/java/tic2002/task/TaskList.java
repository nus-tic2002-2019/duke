package tic2002.task;

import java.util.ArrayList;

/**
 * Represents TaskList class.
 */
public class TaskList {
    //Declare variables
    private ArrayList<Task> taskList;

    //Constructor
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    //Getter
    /**
     * Returns size of Task ArrayList.
     *
     * @return int
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Returns Task by the given index number.
     *
     * @param i as index number to search.
     * @return Task
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    //Setter
    /**
     * Adds new Task to Task ArrayList.
     *
     * @param currentTask
     */
    public void addTask(Task currentTask) {
        taskList.add(currentTask);
    }

    /**
     * Removes Task by given index number from Task ArrayList.
     *
     * @param i as index number to remove
     */
    public void deleteTask(int i) {
        taskList.remove(i);
    }

    /**
     * Clears TaskList.
     */
    public void clearTask() {
        taskList.clear();
    }
}
