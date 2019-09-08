public class Operations {
    // operations class handles all interactions with the Task object.
    Task[] tasks = new Task[256];
    int taskCounter = 0;

    public Operations() {}

    // adds a new task to the Task stack
    public void add(String description) {
        tasks[taskCounter] = new Task();
        tasks[taskCounter].setDescription(description);
        taskCounter ++;
    }
    public void print(int index){
        System.out.println(tasks[taskCounter-1].description);
    }
    public void printList() {
        for (int i = 0; i < taskCounter; i++){
            System.out.println((i+1) + ". "  + tasks[i].description);
        }
    }

    public void done(int taskIndex) {

    }


}
