package subclass;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int task_no) {
        return this.taskList.remove(task_no);
    }

    public ArrayList<Task> getList() {
        return taskList;
    }


}
