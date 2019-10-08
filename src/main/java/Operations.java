import java.util.ArrayList;

public class Operations {
    // operations class handles all interactions with the Task class.
    Task[] tasks = new Task[256];
    //private static ArrayList<Task> tasks = new ArrayList<>();

    int taskCounter = 0;

    public Operations() {}

    // takes a Todo, Deadline, or Event object.
    public void add(Task toAdd) {
        tasks[taskCounter] = toAdd;
        System.out.print ("Added task: ");
        printIndex(taskCounter);
        taskCounter ++;
    }

    public void printIndex(int index){
        //System.out.println(tasks[taskCounter].description);
        tasks[taskCounter].print();
    }
    public void printList() {
        for (int i = 0; i < taskCounter; i++){
            System.out.print (i+1 + ". ");
            tasks[i].print();

        }
    }

    public String getDescription(int index){
        return tasks[index].description;
    }

    public void setDone(int listIndex) {
        // tasks are 0 indexed, but are labelled 1,2,3... hence taskIndex = listIndex -1
        int taskIndex = listIndex-1;
        tasks[taskIndex].done = true;
        tasks[taskIndex].print();
    }
}
