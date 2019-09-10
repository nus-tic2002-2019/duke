package subclass;

import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] inputs = new Task[100];
    private static int word_count = 0;

    public static void add_task(Task description){
        inputs[word_count] = description;
        System.out.println("\t_________________________________________");
        System.out.println("\tGot it. I've added this task:");
        word_count++;
        Task[] print_array = Arrays.copyOf(inputs, word_count);
        int num = 1;
        System.out.println("\t" + description);
        System.out.println("\tNow you have " + word_count + " tasks in list.");
        System.out.println("\t_________________________________________");
    }

    public static void getList() {
        System.out.println("\t_________________________________________");
        Task[] print_array = Arrays.copyOf(inputs, word_count);
        int num = 1;
        for (Task item : print_array) {
            System.out.println("\t"+ num + ". "+ print_array[num-1].toString());

            num++;

        }
        System.out.println("\t_________________________________________");
    }

    public static void markDone(int taskNo) {
        inputs[taskNo-1].setDone(true);
        Task[] print_array = Arrays.copyOf(inputs, word_count);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + print_array[taskNo-1]);
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
