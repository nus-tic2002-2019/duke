import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<Task>();
    }

    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }
}
