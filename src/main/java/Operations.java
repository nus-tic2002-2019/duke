import java.util.ArrayList;

public class Operations {
    // operations class handles all interactions with the Task class.
    private static ArrayList<Task> tasks = new ArrayList<>();
    int taskCounter = 0;
    public Operations() {}

    public void add(Task toAdd) {
        tasks.add(toAdd);
        System.out.print ("Added task: ");
        printIndex(taskCounter);
        taskCounter ++;
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
        return tasks.get(index).description;
    }

    public void setDone(int listIndex) {
        // tasks are 0 indexed, but are labelled 1,2,3... hence taskIndex = listIndex -1
        int taskIndex = listIndex-1;

        tasks.get(taskIndex).done = true;
        tasks.get(taskIndex).print();
    }

    public void delete(int listIndex) {
        int taskIndex = listIndex-1;
        tasks.remove(taskIndex);
    }
}
