import java.util.ArrayList;

public class TaskList {

    private static int numberOfTask = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(){

    };
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public static void addTask(Task s){
        tasks.add(s);
        s.setTaskIndex(numberOfTask);
        numberOfTask++;
        Ui.displayAfterAction(s.getTask(), numberOfTask);

    }
    public static int getSize(){
        return tasks.size();
    }
    public static Task getTask(int i){
        return tasks.get(i);
    }

    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }
}
