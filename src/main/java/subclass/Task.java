package subclass;

import java.util.Arrays;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    private static ArrayList<Task> task_info = new ArrayList<Task>(100);
    private static int word_count = 0;

    public static void add_task(Task description) {
            task_info.add(description);
            System.out.println("\t_________________________________________");
            System.out.println("\tGot it. I've added this task:");
            word_count++;
            System.out.println("\t\t" + description);
            System.out.println("\tNow you have " + word_count + " tasks in list.");
            System.out.println("\t_________________________________________");
    }

    public static String getList() {
        int num = 1;
        String _list_ = "";
        for (Task elements : task_info) {
            _list_ += "\n\t" + num + ". " + elements;
            num++;
        }
        return _list_;
    }

    public static void markDone(int taskNo) {
        Task task_tmp = task_info.get(taskNo-1);
        task_tmp.setDone(true);
        System.out.println("\t_________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task_info.get(taskNo-1));
        System.out.println("\t_________________________________________");
    }

    public static void removeTask(int taskNo) {
        System.out.println("\t_________________________________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task_info.get(taskNo-1));
        task_info.remove(taskNo-1);
        word_count--;
        System.out.println("\tNow you have " + word_count + " tasks in the list.");
        System.out.println("\t_________________________________________");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
