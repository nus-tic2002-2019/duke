package storage;

import task.*;
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
    public void add(Task task) {
        list.add(task);
    }
    public ArrayList<Task> getAllTasks() {return list;}
    public int size() {
        return list.size();
    }
    public void delete(int index){
        list.remove(index);
    }
    public void print(){
        ui.printTaskList(list);
    }
}
