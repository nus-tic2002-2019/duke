package storage;

import task.*;
import java.util.ArrayList;
import UI.Message;
import java.util.*;

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

    public void sort() {
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (Task task : list) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task; //Downcast
                deadlines.add(deadline);
            }
        }
        // O(n^2)   ):
        for (int i = 0; i < deadlines.size() - 1; ++i) {
            for (int j = 1; j < deadlines.size(); ++j) {
                if (deadlines.get(i).getDate().compareTo(deadlines.get(j).getDate()) > 0) {
                    Collections.swap(deadlines, i, j);
                }
            }
        }
        for (Deadline deadline : deadlines) {
            System.out.println("\t" + deadline);
        }
    }

}
