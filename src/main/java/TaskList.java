import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasklist;

    TaskList(){
        tasklist=new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasklist){
        this.tasklist=tasklist;
    }

    public static void addTask(Task s){
        tasklist.add(s);
    }

    public static Task deleteTask(int index){
        Task task = tasklist.get(index);
        tasklist.remove(index);
        return task;
    }

    public static Task doneTask(int index){
        Task task = tasklist.get(index);
        task.markAsDone();
        return task;
    }

    int getTaskSize(){
        return tasklist.size();
    }

    public static ArrayList<Task> getTasklist() {
        return tasklist;
    }
}
