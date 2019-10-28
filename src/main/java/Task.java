import java.util.ArrayList;

public class Task {
    private ArrayList <Todo> taskList;

    public void addTodo(String todo) {
        Todo task = new Todo(todo);
        taskList.add(task);
    }

    public void addEvent(String todo) {
        try {
            Todo event = new Event(todo.split("/at", 2)[0], todo.split("/at", 2)[1]);
            taskList.add(event);
        } catch(Exception e) {
            addTodo(todo);
        }
    }

    public void addDeadline(String todo) {
        try {
            Todo deadline = new Deadline(todo.split("/by", 2)[0], todo.split("/by", 2)[1]);
            taskList.add(deadline);
        } catch(Exception e) {
            addTodo(todo);
        }
    }

    public ArrayList getTask() {
        return taskList;
    }

    public Todo getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public boolean setDone(int itemNumber, boolean done) {
        if (itemNumber >= 0 && getSize() > itemNumber) {
            taskList.get(itemNumber).setDone(done);
            return true;
        }
        return false;
    }

    public void printTaskList() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).getType() + "[" + (taskList.get(i).getDone() ? "✓" : "✗") + "] " + taskList.get(i).display());
        }
    }

    public Task() {
        this.taskList = new ArrayList<Todo>();
    }
}
