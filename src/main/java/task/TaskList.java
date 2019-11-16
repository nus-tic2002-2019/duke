package task;

import exception.DukeException;

import java.util.ArrayList;
import java.util.List;
import ui.Ui;
/**
 * Represents the list of task created by the user.
 * */
public class TaskList {
    /**
     * Creation of list for tasks.
     * */
    private static List<Task> tasks;
    /**
     * It takes in any existing list from user,
     * if none it will start a new list.
     * */
    public TaskList(List<Task> input) throws DukeException {
        if(input.isEmpty()){
            tasks=new ArrayList();
            throw new DukeException("☹ OOPS!!! Empty String");
        }
        tasks=input;
    }

    public TaskList() {

    }
    /**
     * It takes in a word and tries to match it in the list,
     * then prints out the list of matched words.
     * */
    public static void findTask(String word){
        Ui.showUserLine();
        System.out.println("Here are the matching tasks in your list:"+ System.lineSeparator());
        for(int i=0; i<tasks.size();i++)
            if (tasks.get(i).list().contains(word)) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i).list());
            }
        Ui.showUserLine();
    }
    /**
     * It takes in a new task and adds it to the list,
     * then prints out the respective message after adding.
     * */
    public static void addTask(Task t) {
        tasks.add(t);
        Ui.showUserLine();
        System.out.println("Got it. I've added this task: \n" + t.list() + "\nNow you have tasks in the list: "+tasks.size());
        Ui.showUserLine();
    }
    /**
     * Searches for respective index in the list,
     * deletes the entry according the index.
     * */
    public static void removeTask(int i){
        Task T = tasks.get(i-1);
        tasks.remove(i-1);
        Ui.showUserLine();
        System.out.println("Noted. I've removed this task: \n" + T.list());
        System.out.println("\nTasks in the list: " + tasks.size());
        Ui.showUserLine();
    }
    /**
     * It starts printing out the list of task
     * */
    public static void getDescription(){
        Ui.showUserLine();
        System.out.println("Here are the tasks in your list:"+ System.lineSeparator());
        assert (tasks!=null) && (tasks.size()>0): "No Tasks!";
        for(int i=0;i<tasks.size();i++) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i).list());
        }
        Ui.showUserLine();
    }
    /**
     * It searches for the index indicated by user
     * and marks against the list as done.
     * */
    public static void markAsDone(int index) {
        tasks.get(index - 1).setDone(true);
        Task T = tasks.get(index-1);
        Ui.showUserLine();
        System.out.println("Nice! I've marked this task as done: \n" + T.list());
        Ui.showUserLine();
    }
    /**
     * Return the task in the task-list.
     * */
    public static List<Task> getTaskList(){
        return tasks;
    }

}
