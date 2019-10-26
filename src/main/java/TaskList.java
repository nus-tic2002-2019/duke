import java.util.ArrayList;

public class TaskList {

    private static int numberOfTask = 0;
    private static ArrayList<Task> Tasks = new ArrayList<>();
    public static void addTask(Task s){
        Tasks.add(s);
        numberOfTask++;
        System.out.println("added: "+ s.getTask());
    }
}
