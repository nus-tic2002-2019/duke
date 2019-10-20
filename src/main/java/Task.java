import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDoneTrue() {isDone = true;}

    public String getStatusIcon() {return (isDone ? "\u2713" : "\u2718");}

    public static void listTask(ArrayList<Task> task) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : task) {
            //System.out.println(i + "." + "[" + t.getStatusIcon() + "] " + t.getDescription());
            System.out.println(i + "." + t);
            i++;
        }
    }

    public static void doneTask(ArrayList<Task> task, int taskItem) {
        task.get(taskItem - 1).setIsDoneTrue();
        //System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + " [" + task.get(taskItem - 1).getStatusIcon() + "] " + task.get(taskItem - 1).getDescription());
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + task.get(taskItem - 1));
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
