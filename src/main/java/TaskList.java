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
    public static void addTask(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: \n" + t.print() + "\nNow you have tasks in the list: "+tasks.size());
    }
    public static void removeTask(int i){
        Task T = tasks.get(i-1);
        tasks.remove(i-1);
        System.out.println("Noted. I've removed this task: \n" + T.print());
        System.out.println("\nTasks in the list: " + tasks.size());
    }
    public static String getDescription(){
        List<String> out = new ArrayList<>();
        assert (tasks!=null) && (tasks.size()>0): "No Tasks!";
        for(int i=0;i<tasks.size();i++) {
            if(i==0) {
                out.add("[" + (i + 1) + "] " + tasks.get(i).list());
            }else{
                out.add(out.get(i-1)+System.lineSeparator()+"[" + (i + 1) + "] " + tasks.get(i).list());
            }
        }
        return out.get(out.size()-1);
    }
    public static void markAsDone(int index) {
        tasks.get(index - 1).setDone(true);
        Task T = tasks.get(index-1);
        System.out.println("Nice! I've marked this task as done: \n" + T.print());
    }

    public static List<Task> getTaskList(){
        return tasks;
    }

}
