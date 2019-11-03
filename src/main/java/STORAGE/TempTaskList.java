package STORAGE;

import TASK.*;
import java.util.ArrayList;
import UI.Message;

public class TempTaskList {
    private ArrayList<Task> list;
    private Message ui;

    public TempTaskList() {
        list = new ArrayList<Task>();
        ui = new Message();
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
        ui.printTaskList(list);
    }

}
