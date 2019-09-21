package MyClasses;

import java.util.ArrayList;

public class TaskList {
    protected static final String HoriLine = "--------------------------------------------------\n";
    protected static ArrayList<String> taskList = new ArrayList<String>(100);
    protected static ArrayList<Boolean> taskStatus = new ArrayList<Boolean>(100);
    //Getter
    public void ListTask() {
        for (int i = 0; i < taskList.size(); i++) {
            String temp = this.taskStatus.get(i) ? "\u2713" : "\u2613";
            System.out.println(i + 1 + ". [" + temp + "] " + this.taskList.get(i));
        }
        System.out.println("\n" + HoriLine);
    }

    //Print First x number of tasks
    public void ListTask(int LoopCnt) {
        Boolean OutofBound = false;
        if (LoopCnt > this.taskList.size()) {
            LoopCnt = this.taskList.size();
            OutofBound = true;
        }
        for (int i = 0; i < LoopCnt; i++) {
            String temp = this.taskStatus.get(i) ? "\u2713" : "\u2613";
            System.out.println(i + 1 + ". [" + temp + "] " + this.taskList.get(i));
        }
        if (OutofBound == true) {
            System.out.println("Assigned Command value greater than added list of tasks.");
        }
        System.out.println("\n" + HoriLine);
    }

    public void DoneTask(int idx) {
        if (idx <= this.taskStatus.size()) {
            this.taskStatus.set(idx, true);
            System.out.println("Nice! I've marked this task as done : ");
            System.out.println("[\u2713] " + this.taskList.get(idx));
        } else {
            System.out.println("Item at the specified index does not exist");
        }
    }

    public void AddTask(String Task) {
        this.taskList.add(Task);
        this.taskStatus.add(false);
        System.out.println("Added task : " + Task + "\n" + HoriLine);

    }

    public void RemoveTask(int index) {
        String task_item = this.taskList.get(index);
        this.taskList.remove(index);
        this.taskStatus.remove(index);
        System.out.println("Removed task " + task_item + "\n" + HoriLine);
    }

}