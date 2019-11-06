package main;

import storage.Storage;
import task.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Operations {
    // operations class handles all interactions with the task.Task class.
    private static ArrayList<Task> tasks = new ArrayList<>();
    int taskCounter = 0;
    Storage my_file;

    public Operations(Storage my_file) {
        this.my_file = my_file;
    }

    public void add(Task toAdd) throws IOException {
        tasks.add(toAdd);
        System.out.print ("Added task: ");
        printIndex(taskCounter);
        taskCounter ++;
        my_file.updateFile(tasks);
    }

    public void printIndex(int index){
        tasks.get(index).print();
    }
    public void printList() {
        for (int i = 0; i < taskCounter; i++){
            System.out.print (i+1 + ". ");
            tasks.get(i).print();
        }
    }

    public String getDescription(int index){
        return tasks.get(index).getDescription();
    }

    public void setDone(int listIndex) throws IOException {
        // tasks are 0 indexed, but are labelled 1,2,3... hence taskIndex = listIndex -1
        int taskIndex = listIndex-1;

        tasks.get(taskIndex).setDone(true);
        tasks.get(taskIndex).print();

    }

    public void delete(int listIndex) throws IOException {
        int taskIndex = listIndex-1;
        tasks.remove(taskIndex);
    }

    public void loadFromFile() throws FileNotFoundException {
        my_file.readFromFile();
        //
    }
}
