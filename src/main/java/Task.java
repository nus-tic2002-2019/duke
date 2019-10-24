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

    public static void addTask(ArrayList<Task> task, String line) throws DukeException {
        if (line.startsWith("todo")) {
            String todoTask = line.substring(5);
            Todo todo = new Todo(todoTask);
            task.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else if (line.startsWith("deadline")) {
            String deadlineTask = line.substring(9, (line.indexOf("/") - 1));
            String by = line.substring((line.indexOf("/") + 4));
            Deadline deadline = new Deadline(deadlineTask, by);
            task.add(deadline);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else if (line.startsWith("event")) {
            String eventTask = line.substring(6, (line.indexOf("/") - 1));
            String at = line.substring((line.indexOf("/") + 4));
            Event event = new Event(eventTask, at);
            task.add(event);
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else {
            throw new DukeException();
            /*Task t = new Task(line);
            task.add(t);
            System.out.println("added: " + t.getDescription());*/
        }
    }

    public static void listTask(ArrayList<Task> task) {
        System.out.println("Here are the tasks in your list:");
        for (Task t : task) {
            System.out.println((task.indexOf(t) + 1) + "." + t);
        }
    }

    public static void doneTask(ArrayList<Task> task, int taskItem) {
        task.get(taskItem - 1).setIsDoneTrue();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + task.get(taskItem - 1));
    }

    public static void deleteTask(ArrayList<Task> task, int taskItem) {
        Task t = task.get(taskItem - 1);
        task.remove((taskItem - 1));
        System.out.println("Noted. I've remove this task:" + System.lineSeparator() + t + System.lineSeparator() + "Now you have " + task.size() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
