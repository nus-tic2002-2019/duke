package duke.task;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the list used to store the various tasks.
 */
public class TaskList {
    protected static ArrayList<Task> taskList;

    /**
     * Create a task list using existing data.
     *
     * @param input is a String of task data.
     * @throws IOException if there are errors writing the file.
     * @throws DateTimeParseException if the date format incorrect.
     */
    public TaskList(String input) throws IOException, DateTimeParseException {
        this.taskList = new ArrayList();
        BufferedReader reader = new BufferedReader(new StringReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] delimited = line.split(";");
            if ("T".equals(delimited[1])) {
                taskList.add(new ToDo(delimited[3]));
            } else if ("E".equals(delimited[1])) {
                taskList.add(new Event(delimited[3], LocalDate.parse(delimited[4])));
            } else if ("D".equals(delimited[1])) {
                taskList.add(new Deadline(delimited[3], LocalDate.parse(delimited[4])));
            }
            taskList.get(taskList.size() - 1).setStatus(Boolean.parseBoolean(delimited[2]));
            taskList.get(taskList.size() - 1).setDoAfter(Integer.parseInt(delimited[5]));
            taskList.get(taskList.size() - 1).setDoBefore(Integer.parseInt(delimited[6]));
        }
    }

    /**
     * Create a task list when there are no existing data.
     */
    public TaskList() {
        this.taskList = new ArrayList();
    }

    /**
     * Check if task list is empty.
     *
     * @return true if task list is empty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Get a specific task from the task list.
     *
     * @param i is the element index of the task.
     * @return the task as a Task object.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Return the index of a task in the task list.
     *
     * @param task task object.
     * @return task index.
     */
    public int getIndex(Task task) {
        return taskList.indexOf(task);
    }

    /**
     * @return the size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param i is the element index of the task.
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Creates a new task.
     *
     * @param task is Task object to be created.
     */
    public void add(Task task) {
        taskList.add(task);
    }
}
