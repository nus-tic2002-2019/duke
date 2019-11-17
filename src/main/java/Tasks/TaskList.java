package Tasks;

import UI.Ui;

import java.util.ArrayList;

/**
 * Store the list of task for the program to call and use
 */
public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs the task list
     */
    public TaskList(){

    }

    /**
     * Constructs the task list with a available list of task
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;

    }

    /**
     * Adding of task into the task list and set the task index as well as display the action after it has been added
     * @param s the task that the user requested to be added
     */
    public static void addTask(Task s){
        tasks.add(s);
        s.setTaskIndex(tasks.size() - 1);
        Ui.displayAfterAction(s.toString(), tasks.size());

    }

    /**
     * Getting the number of task in the list
     * @return the number of task in the list
     */
    public static int getSize(){
        return tasks.size();
    }

    /**
     * Get the specific task from list of task
     * @param i the task index for the task that the user wants
     * @return the task that the user requested
     */
    public static Task getTask(int i){
        return tasks.get(i);
    }

    /**
     * Remove the task from the list that the user requested
     * @param taskIndex the task index that the user requested
     */
    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
        for(int i = taskIndex; i < tasks.size(); i++){
            tasks.get(i).setTaskIndex(i);
        }
    }

    /**
     * Remove everything from the task list
     */
    public void clearAll(){
        tasks.clear();
    }
}
