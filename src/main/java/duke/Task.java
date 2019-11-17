package duke;

import java.util.ArrayList;

public class Task {
    private static ArrayList <Todo> taskList;

    public static void addTodo(String todo) {
        Todo task = new Todo(todo);
        taskList.add(task);
    }

    public static void addEvent(String todo) throws DukeException {
        if (todo.indexOf("/at") < 0) {
            throw new DukeException("We are unable to add that event. Try specifying /at for an event. E.g. 'Dinner /at 17/11/2019 1800'");
        }
        try {
            Todo event = new Event(todo.split("/at", 2)[0], todo.split("/at", 2)[1]);
            taskList.add(event);
        } catch (DukeException error) {
            throw error;
        }
    }

    public static void addDeadline(String todo) throws DukeException {
        if (todo.indexOf("/by") < 0) {
            System.out.println(todo.indexOf("/by"));
            throw new DukeException("We are unable to add that deadline. Try specifying /by for an event. E.g. 'Project /by 17/11/2019 2359'");
        }
        try {
            Todo deadline = new Deadline(todo.split("/by", 2)[0], todo.split("/by", 2)[1]);
            taskList.add(deadline);
        } catch (DukeException error) {
            throw error;
        }
    }

    public static Todo getTask(int index) throws DukeException {
        if (getSize() == 0 || getSize() <= index) {
            throw new DukeException("Selection number incorrect.");
        }
        return taskList.get(index);
    }

    public static int getSize () {
        return taskList.size();
    }

    public static boolean setDone(int itemNumber, boolean done) throws DukeException {
        if (getSize() == 0 || getSize() <= itemNumber) {
            throw new DukeException("Selection number incorrect.");
        }
        if (itemNumber >= 0) {
            taskList.get(itemNumber).setDone(done);
            return true;
        }
        return false;
    }

    public static boolean removeTask(int itemNumber) throws DukeException {
        if (getSize() == 0 || getSize() <= itemNumber) {
            throw new DukeException("Selection number incorrect.");
        }
        if (itemNumber >= 0) {
            taskList.remove(itemNumber);
            return true;
        }
        return false;
    }

    public static void printTaskList() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println(i+1 + "." + taskList.get(i).getType() + "[" + (taskList.get(i).getDone() ? "✓" : "✗") + "] " + taskList.get(i).display());
        }
    }

    public static void sortTask(String type) {
        ArrayList <Todo> tempList = new ArrayList<>();;
        switch(type) {
            case "todo":
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("T")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("E")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("D")) {
                        tempList.add(taskList.get(i));
                    }
                }
                taskList = tempList;
                break;
            case "event":
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("E")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("T")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("D")) {
                        tempList.add(taskList.get(i));
                    }
                }
                taskList = tempList;
                break;
            case "deadline":
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("D")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("T")) {
                        tempList.add(taskList.get(i));
                    }
                }
                for (int i = 0; i < getSize(); i++) {
                    if (taskList.get(i).getType().equals("E")) {
                        tempList.add(taskList.get(i));
                    }
                }
                taskList = tempList;
                break;
            default:
                break;
        }
    }

    public Task() {
        this.taskList = new ArrayList<>();
    }
}
