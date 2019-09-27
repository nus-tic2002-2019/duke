import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> tasks;

    public TaskList(List<Task> input) throws DukeException{
        if(input.isEmpty()){
            tasks=new ArrayList();
            throw new DukeException("☹ OOPS!!! Empty String");
        }
        tasks=input;
    }

    public TaskList() {

    }

    public static void findTask(String word){
        Ui.showUserLine();
        System.out.println("Here are the matching tasks in your list:"+ System.lineSeparator());
        for(int i=0; i<tasks.size();i++)
            if (tasks.get(i).list().contains(word)) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i).list());
            }
        Ui.showUserLine();
    }

    public static void addTask(Task t) {
        tasks.add(t);
        Ui.showUserLine();
        System.out.println("Got it. I've added this task: \n" + t.list() + "\nNow you have tasks in the list: "+tasks.size());
        Ui.showUserLine();
    }
    public static void removeTask(int i){
        Task T = tasks.get(i-1);
        tasks.remove(i-1);
        Ui.showUserLine();
        System.out.println("Noted. I've removed this task: \n" + T.list());
        System.out.println("\nTasks in the list: " + tasks.size());
        Ui.showUserLine();
    }
    public static void getDescription(){
        Ui.showUserLine();
        System.out.println("Here are the tasks in your list:"+ System.lineSeparator());
        assert (tasks!=null) && (tasks.size()>0): "No Tasks!";
        for(int i=0;i<tasks.size();i++) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i).list());
        }
        Ui.showUserLine();
    }
    public static void markAsDone(int index) {
        tasks.get(index - 1).setDone(true);
        Task T = tasks.get(index-1);
        Ui.showUserLine();
        System.out.println("Nice! I've marked this task as done: \n" + T.list());
        Ui.showUserLine();
    }

    public static List<Task> getTaskList(){
        return tasks;
    }

}
