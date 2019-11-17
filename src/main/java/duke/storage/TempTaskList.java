package duke.storage;

import duke.task.*;
import java.util.ArrayList;
import duke.UI.Message;
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
        for (int i = 0; i < deadlines.size(); ++i) {
            for (int j = 0; j < deadlines.size()-1; ++j) {
                if (deadlines.get(j).getDate().compareTo(deadlines.get(j+1).getDate()) > 0) {
                    Collections.swap(deadlines, j, j+1);
                }
            }
        }
        for (Deadline deadline : deadlines) {
            System.out.println("\t" + deadline);
        }
    }

}
