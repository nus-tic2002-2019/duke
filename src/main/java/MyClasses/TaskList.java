package MyClasses;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList = new ArrayList<String>(100);

    //Getter
    public void ListTask() {
        for (String task : this.taskList) {
            System.out.println(task);
        }
    }

    //Print First x number of tasks
    public void ListTask(int LoopCnt) {
        for (int x = 0; x < LoopCnt; x++) {
            System.out.println(this.taskList.get(x));
        }
    }

    public void AddTask(String Task) {
        this.taskList.add(Task);
    }

    public void RemoveTask(int index) {
        this.taskList.remove(index);
    }

}