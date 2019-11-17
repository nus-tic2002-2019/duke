package main.duke.task;

import main.duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public void printTasks() {
        int i = 0;
        for (Task t : tasks) {
            System.out.format("%d: %s\n", ++i, t);
        }
    }

    public boolean add(Task t) {
        return tasks.add(t);
    }

    public Task remove(int i) {
        if(i < this.size())
            return tasks.remove(i);
        else throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public boolean remove(Task t) {
        return tasks.remove(t);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void done(int i) {
        Task currTask = get(i);
        currTask.setDone(true);
    }

    public int size() {
        return tasks.size();
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
