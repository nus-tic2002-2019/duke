package duke;

import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<Task>();
    }

    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * @param i get the specific task
     * @return return the task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * @param task adding the user task in list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }

    public Tasklist findTask(String find){
        Tasklist newTasks = new Tasklist();
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getDescription().contains(find)){
                newTasks.addTask(tasks.get(i));
            }
        }
        return newTasks;
    }

}
