package tasklist;

import task.Task;
import parser.Parser;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> store = new ArrayList<>();

    /**
     * Constructor that will take in a list of user inputs and store the command into an ArrayList
     * @param commands
     */
    public TaskList(ArrayList<String> commands){
        for(String each : commands){
            Task t = Parser.createTodo(each);
            store.add(t);
        }
    }

    /**
     * Empty constructor
     */
    public TaskList(){
    }

    /**
     * Adding new task to the TaskList
     * @param item
     */
    public void addTask(Task item){
        this.store.add(item);
    }

    /**
     * Set the task to be done
     * @param index
     */
    public void doneTask(int index) {
        store.get(index).setIsDone();
    }

    /**
     * Delete existing task in the ArrayList
     * @param index
     * @return deleted task
     * @throws IndexOutOfBoundsException
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        //what is a good habit return straight ? or not return straight?
        Task removed_item = store.remove(index);
        return removed_item;
    }

    /**
     * Getter for the size of the TaskList
     * @return the size of the TaskList
     */
    public int getSize(){
        return store.size();
    }

    /**
     * Getter of the task in the ArrayList
     * @param index
     * @return Task
     */
    public Task getTask(int index){
        return store.get(index);
    }

    /**
     * Return the description of the Tasks in the ArrayList
     * @return String description
     */
    public String getDescription(){
        String output = "    ____________________________________________________________\n" +
                       "     Here are the tasks in your list:\n";
        for(int i = 0;i < store.size(); i++){
            output = output + "    " + Integer.toString(i + 1) + "." + store.get(i).toString() + "\n";
        }
        output = output + "    ____________________________________________________________\n";
        return output;
    }
}
