package duke.task;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class TaskList {
    protected static ArrayList<Task> taskList;

    public TaskList(String input) throws IOException {
        this.taskList = new ArrayList();
        BufferedReader reader = new BufferedReader(new StringReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] delimited = line.split(";");
            if ("T".equals(delimited[1])) {
                taskList.add(new ToDo(delimited[3]));
            } else if ("E".equals(delimited[1])) {
                taskList.add(new Event(delimited[3], delimited[4]));
            } else if ("D".equals(delimited[1])) {
                taskList.add(new Deadline(delimited[3], delimited[4]));
            }
            taskList.get(taskList.size() - 1).setStatus(Boolean.parseBoolean(delimited[2]));
        }
    }

    public TaskList() {
        this.taskList = new ArrayList();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public int size() {
        return taskList.size();
    }

    public void remove(int i) {
        taskList.remove(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }
}
