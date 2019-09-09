package MyClasses;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<String> taskList = new ArrayList<String>(100);
    protected ArrayList<Boolean> taskStatus = new ArrayList<Boolean>(100);
    //Getter
    public void ListTask() {
        for (String task : this.taskList) {
            System.out.println(task);
        }
    }

    //Print First x number of tasks
    public void ListTask(int LoopCnt) {
        for (int x = 0; x < LoopCnt; x++) {
            System.out.println(x + ". " + this.taskList.get(x));
        }
    }

    public void AddTask(String Task) {
        this.taskList.add(Task);
        System.out.println("Added : task" + Task);
    }

    public void RemoveTask(int index) {
        String task_item = this.taskList.get(index);
        this.taskList.remove(index);
        System.out.println("Removed task " + task_item);
    }

}