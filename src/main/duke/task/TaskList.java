package main.duke.task;

import main.duke.exception.DukeException;
import main.duke.exception.DukeUnknownException;
import main.duke.storage.Storage;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> tasks;

    public Iterator<Task>iterator(){
        return tasks.iterator();
    }
    public String listTasks() {
        String tasksStr = "";
        int i = 0;
        for (Task t : tasks) {
            tasksStr += String.format("%d: %s\n", ++i, t);
        }
        return tasksStr;
    }

    public void printTasks() {
        System.out.print(listTasks());
    }

    public boolean add(String s) throws DukeException {
        Task t;
        boolean done = true;
        String[] str_arr = s.split(" ");
        int startDescIndex = s.indexOf(String.format("[%c]", Task.checkmark));  // done
        int lastDescIndex = s.length();
        if(startDescIndex == -1){
            startDescIndex = s.indexOf(String.format("[%c]", Task.crossmark));  //not done
            done = false;
        }
        switch (str_arr[1].charAt(1)){
            case 'D':
                //1: [D][❌] tic2002 (by: today)
                lastDescIndex = s.lastIndexOf(" (by: ");
                t = new Deadline(s.substring(startDescIndex + 4, lastDescIndex), s.substring(lastDescIndex + 6, s.length() - 1));
                break;
            case 'E':
                //2: [E][❌] tp visit (at: today)
                lastDescIndex = s.lastIndexOf(" (at: ");
                t = new Event(s.substring(startDescIndex + 4, lastDescIndex), s.substring(lastDescIndex + 6, s.length() - 1));
                break;
            case 'T':
                //234: [T][❌] awd
                t = new ToDo(s.substring(startDescIndex + 4, lastDescIndex));
                break;
            default:
                throw new DukeUnknownException();
        }
        t.setDone(done);
        return tasks.add(t);
    }

    public boolean add(Task t) {
        return tasks.add(t);
    }

    public Task remove(int i) {
        if (i < this.size())
            return tasks.remove(i);
        else throw new IndexOutOfBoundsException("Index out of bounds");
    }

    public boolean remove(Task t) {
        return tasks.remove(t);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void done(int i) {
        Task currTask = get(i);
        currTask.setDone(true);
    }

    public int size() {
        return tasks.size();
    }

    public TaskList(Storage s) throws DukeException {
        tasks = new ArrayList<>();
        s.readSaveFile(this);
    }
}
