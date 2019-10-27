package TaskList;
import java.util.ArrayList;
import Task.*;
//import Task.Task;

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

    public static void addToTaskList(String addTaskToString){
        todoListArray.add(addTaskToString);
    }

    public static int todoListArraySize() {
        return todoListArray.size();
    }

    public static String getTaskList(int task_counter) {
        return todoListArray.get(task_counter);
    }

    public static String removeTaskList(int removeTaskNumber) {
        return todoListArray.remove(removeTaskNumber);
    }
//
}
