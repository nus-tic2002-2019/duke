public class Operations {
    // operations class handles all interactions with the Task class.
    Task[] tasks = new Task[256];
    int taskCounter = 0;

    public Operations() {}

    // adds a new task to the Task array
    public void add(String description) {
        tasks[taskCounter] = new Task();
        tasks[taskCounter].setDescription(description);
        printIndex(taskCounter);
        taskCounter ++;
    }

    public void printIndex(int index){
        //System.out.println(tasks[taskCounter].description);
        tasks[taskCounter].print();
    }
    public void printList() {
        for (int i = 0; i < taskCounter; i++){
            System.out.println((i+1) + ". " + tasks[i].getDoneMessage() + " " + tasks[i].description);
        }
    }

    public String getDescription(int index){
        return tasks[index].description;
    }

    public void setDone(int listIndex) {
        // tasks are 0 indexed, but are labelled 1,2,3... hence set -1
        int taskIndex = listIndex-1;
        tasks[taskIndex].done = true;
        System.out.print("Set as done: ");
        tasks[taskIndex].print();
    }
}
