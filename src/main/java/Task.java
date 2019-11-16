import java.util.ArrayList;

public class Task {
    private ArrayList <Todo> taskList;

    public void addTodo(String todo) {
        Todo task = new Todo(todo);
        taskList.add(task);
    }

    public void addEvent(String todo) throws DukeException {
        if (todo.indexOf("/at") < 0) {
            throw new DukeException("We are unable to add that event. Try specifying /at for an event. E.g. 'Dinner /at 6pm'");
        }
        Todo event = new Event(todo.split("/at", 2)[0], todo.split("/at", 2)[1]);
        taskList.add(event);
    }

    public void addDeadline(String todo) throws DukeException {
        if (todo.indexOf("/by") < 0) {
            System.out.println(todo.indexOf("/by"));
            throw new DukeException("We are unable to add that deadline. Try specifying /by for an event. E.g. 'Project /by Sunday'");
        }
        Todo deadline = new Deadline(todo.split("/by", 2)[0], todo.split("/by", 2)[1]);
        taskList.add(deadline);
    }

    public ArrayList getTask() {
        return taskList;
    }

    public Todo getTask(int index) throws DukeException {
        if (getSize() == 0 || getSize() <= index) {
            throw new DukeException("Selection number incorrect.");
        }
        return taskList.get(index);
    }

    public int getSize () {
        return taskList.size();
    }

    public boolean setDone(int itemNumber, boolean done) throws DukeException {
        if (getSize() == 0 || getSize() <= itemNumber) {
            throw new DukeException("Selection number incorrect.");
        }
        if (itemNumber >= 0) {
            taskList.get(itemNumber).setDone(done);
            return true;
        }
        return false;
    }

    public boolean removeTask(int itemNumber) throws DukeException {
        if (getSize() == 0 || getSize() <= itemNumber) {
            throw new DukeException("Selection number incorrect.");
        }
        if (itemNumber >= 0) {
            taskList.remove(itemNumber);
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
