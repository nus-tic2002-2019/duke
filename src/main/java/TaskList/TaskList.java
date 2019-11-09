package TaskList;

import java.util.ArrayList;

public class TaskList {
    private  static ArrayList<String> todoListArray = new ArrayList<>();
    String description;

//    private static int taskCount_addTask = 0;
//    private static int task_count = 0;
//
//    public TaskList(){
//        todolistArray = new ArrayList<>();
//    }

    /***
     * Adding task to Task List
     * @param addTaskToString adding task to To Do ArrayList
     */
    public static void addToTaskList(String addTaskToString){
        todoListArray.add(addTaskToString);
    }

    /***
     *
     * @return  return size of the todo list array
     */
    public static int todoListArraySize() {
        return todoListArray.size();
    }

    /***
     *
     * @param task_counter get the exact task under todo list array
     * @return  todo list array
     */
    public static String getTaskList(int task_counter) {
        return todoListArray.get(task_counter);
    }

    /***
     *
     * @param removeTaskNumber support for deleting task from the list at certain task number
     * @return todo list array
     */
    public static String removeTaskList(int removeTaskNumber) {
        return todoListArray.remove(removeTaskNumber);
    }
}
