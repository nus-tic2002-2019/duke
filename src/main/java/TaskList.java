import java.util.ArrayList;

public class TaskList {


    private static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(){

    };
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;

    }

    public static void addTask(Task s){
        tasks.add(s);
        s.setTaskIndex(tasks.size() - 1);
        Ui.displayAfterAction(s.toString(), tasks.size());

    }
    public static int getSize(){
        return tasks.size();
    }
    public static Task getTask(int i){
        return tasks.get(i);
    }

    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
        for(int i = taskIndex; i < tasks.size(); i++){
            tasks.get(i).setTaskIndex(i);
        }
    }
}
