/*
 *  TaskList.java
 *  Defines task list.
 */
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasklist;

    /*
     * Constructs TaskList object
     */
    TaskList(){
        tasklist=new ArrayList<Task>();
    }

    /*
     * Constructs TaskList object
     * @param tasklist List of task.
     */
    TaskList(ArrayList<Task> tasklist){
        this.tasklist=tasklist;
    }

    /*
     * This method add task into task list.
     * @param s Object of Task class.
     */
    public static void addTask(Task s){
        tasklist.add(s);
    }

    /*
     * This method delete task from task list.
     * @param index Task's index .
     * @return task Deleted Task object.
     */
    public static Task deleteTask(int index){
        Task task = tasklist.get(index);
        tasklist.remove(index);
        return task;
    }

    /*
     * This method mark task as done.
     * @param index Task's index .
     * @return task Task object marked as done.
     */
    public static Task doneTask(int index){
        Task task = tasklist.get(index);
        task.markAsDone();
        return task;
    }

    /*
     * Returns number of tasks in task list.
     */
    int getTaskSize(){
        return tasklist.size();
    }

    /*
     * This method returns all task in task list.
     * @return task All task object stored in task list.
     */
    public static ArrayList<Task> getTasklist() {
        return tasklist;
    }
}
