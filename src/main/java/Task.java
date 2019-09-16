public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {return description;}

    public void setIsDoneTrue() {isDone = true;}

    public String getStatusIcon() {return (isDone ? "\u2713" : "\u2718");}

    public static void addTask(Task[] task, int size, Task t) {
        task[size] = t;
        System.out.println("added: " + task[size].getDescription());
    }

    public static void listTask(Task[] task, int size) {
        int i = 1;
        if (size == 0) {
            System.out.println("There's nothing in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int j = 0; j<= size; j++/*Task content : task*/) {
                if (i > size) {break;}
                System.out.println(Integer.toString(i) + "." + "[" + task[j].getStatusIcon() + "] " + task[j].getDescription());
                i++;
            }
        }
    }

    public static void doneTask(Task[] task, int taskItem) {
        task[taskItem-1].setIsDoneTrue();
        System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() + " [" + task[taskItem-1].getStatusIcon() + "] " + task[taskItem-1].getDescription());
    }

}
