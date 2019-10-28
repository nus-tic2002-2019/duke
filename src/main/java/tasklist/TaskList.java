package tasklist;

import Task.Task;
import parser.Parser;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> store = new ArrayList<>();

    public TaskList(ArrayList<String> commands){
        for(String each : commands){
            Task t = Parser.createTodo(each);
            store.add(t);
        }
    }

    public TaskList(){
    }

    public void addTask(Task item){
        this.store.add(item);
    }

    public void doneTask(int index) {
        store.get(index).setIsDone();
    }

    public Task deleteTask(int index) {
        //what is a good habit return straight ? or not return straight?
        Task removed_item = store.remove(index);
        return removed_item;
    }

    public int getSize(){
        return store.size();
    }

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
