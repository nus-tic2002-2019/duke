package MyClasses;

import java.util.ArrayList;

public class TaskList {
    private static final String HoriLine = "--------------------------------------------------\n";
    protected static ArrayList<String> taskList = new ArrayList<String>(100);
    protected ArrayList<Boolean> taskStatus = new ArrayList<Boolean>(100);
    //Getter
    public void ListTask() {
        for (int i = 0; i < taskList.size(); i++)
            System.out.println(i + ". " + this.taskList.get(i));
        System.out.println("\n" + HoriLine);
    }

    //Print First x number of tasks
    public void ListTask(int LoopCnt) {
        for (int i = 0; i < LoopCnt; i++) {
            System.out.println(i + ". " + this.taskList.get(i));
        }
        System.out.println("\n" + HoriLine);
    }

    public void AddTask(String Task) {
        this.taskList.add(Task);
        System.out.println("Added task : " + Task + "\n" + HoriLine);

    }

    public void RemoveTask(int index) {
        String task_item = this.taskList.get(index);
        this.taskList.remove(index);
        System.out.println("Removed task " + task_item + "\n" + HoriLine);
    }

}