package me.chercherlyn.duke.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import me.chercherlyn.duke.task.Task;

/**
 * Represents a storage for tasks in memory.
 */
public class TaskList {
    
    private List<Task> tasks;
    
    /**
     * Creates TaskList instance with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    /**
     * Creates TaskList instance from given collection of tasks.
     *
     * @param tasks collection of tasks
     */
    public TaskList(Collection<? extends Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    
    // getters
    
    /**
     * Returns task by index.
     *
     * @param index index
     * @return task
     */
    public Task get(int index) {
        return tasks.get(index);
    }
    
    /**
     * Returns list size
     *
     * @return list size
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Returns unmodifiable list of tasks
     *
     * @return unmodifiable list of tasks
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }
    
    // func
    
    /**
     * Adds task to list
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }
    
    /**
     * Removes tasks from list by index
     *
     * @param index index
     * @return task removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
}
