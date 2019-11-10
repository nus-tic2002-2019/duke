package Tasklist;

import java.util.ArrayList;
import Exception.DukeException;

public class TaskList {

    private static ArrayList<Task> taskitems;

    public TaskList(ArrayList<Task> taskinput) throws DukeException
    {
        if (taskinput.isEmpty())
        {
            taskitems = new ArrayList<>();
            System.out.println("OOPS!!! There is empty task");
        }
        taskitems = taskinput;
    }
    public ArrayList<Task> getTaskitems(){
        return taskitems;
    }

    public int size(){
        return taskitems.size();
    }
    public Task get(int position){
        return taskitems.get(position);
    }

    public TaskList()
    {
        taskitems = new ArrayList<>();
    }


    public static void addTask(Task t, boolean bWrite){
        taskitems.add(t);
        if (bWrite == true){
            System.out.println("added:" + t.getDescription());
        }
    }

    public static void displayTasks()
    {
        for(int i=0; i < taskitems.size(); i++)
        {
            System.out.println(i + 1 + ". " + taskitems.get(i));
        }
    }

    public static void markDone(int pos, boolean bPrompt) {
        taskitems.get(pos-1).markDone(true);
        if (bPrompt==true) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    [" + taskitems.get(pos - 1).getStatusIcon() + "]" + taskitems.get(pos - 1).getDescription());
        }
    }

    public static void removeItem(int pos) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    [" +  taskitems.get(pos-1).getStatusIcon() + "]" +  taskitems.get(pos-1).toString());
        taskitems.remove(pos-1);
        System.out.println("Now you have " + taskitems.size() + " tasks in the list");
    }
}