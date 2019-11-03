package STORAGE;

import TASK.*;
import java.util.ArrayList;

public class TempTaskList {
    private ArrayList<Task> list;
    public TempTaskList() {
        list = new ArrayList<Task>();
    }

    public Task get(int index) {
        return list.get(index);
    }
    public void set(Task task) {
        list.add(task);
    }
    public int size() {
        return list.size();
    }
    public void print(){
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
            ++taskNumber;
        }
    }

}
