import task.*;

import java.util.ArrayList;


public class TaskLists {

    public ArrayList<Task> list;

    public TaskLists() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Get the current list size.
     * @return The current list size in integer
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Get the current list.
     * @return The current list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Check whether the list is empty.
     * @return True if list is empty, false if list is not empty.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Runs through the current list till the end of the list.
     * @return A string of list.
     * @throws DukeExceptionEmptyList If the list is empty
     */
    public String displayList() throws DukeExceptionEmptyList {
        if (isEmpty()) {
            throw new DukeExceptionEmptyList();
        }
        int index = 1;
        String answer = "";
        for (int i = 0; i < getSize(); i++) {
            answer = (answer + index + "." + this.list.get(i).getType() + this.list.get(i).getTaskStatus() + " " + this.list.get(i).getTask() + " " + this.list.get(i).getDetails());
            index++;
            if (i == getSize() - 1) {
                continue;
            }
            answer += "\n\t";
        }
        return answer;
    }

    /**
     * Users to search a task from the current list in accordance with the keyword he typed in.
     * @param message The keyword user typed in to search
     * @return The list of tasks which has the closest result from the searched keyword.
     * @throws DukeExceptionEmptyList If the list is empty.
     */
    public String findTask(String message) throws DukeExceptionEmptyList {
        TaskLists foundResult = new TaskLists();
        if (isEmpty()) {
            throw new DukeExceptionEmptyList();
        }
        message = message.substring(5);
        System.out.println(message);
        for (int i = 0; i < getSize(); i++) {
            if (this.list.get(i).getDescription().contains(message)) {
                foundResult.list.add(this.list.get(i));
            }
        }
        return foundResult.displayList();
    }

    /**
     * Displays the latest task of the list.
     * @return The latest task of the list.
     */
    public String displayLatestTask() {
        int number = getSize() - 1;
        return list.get(number).getType() + list.get(number).getTaskStatus() + " " + list.get(number).getTask() + " " + list.get(number).getDetails();
    }

    /**
     * Selected task by users.
     * @param number The task number which is selected by user.
     * @return The selected task.
     */
    public String displaySelectedTask(int number) {
        return list.get(number).getType() + list.get(number).getTaskStatus() + " " + list.get(number).getTask() + " " + list.get(number).getDetails() + "\n";
    }

    /**
     * Duke adds a ToDo task.
     * @param message Details of the ToDo task.
     */
    public void addToDo(String message) {
        list.add(new ToDo((message.substring(5))));
    }

    /**
     * Duke adds a Deadlines task.
     * @param message Details of the Deadlines task.
     */
    public void addDeadline(String message) {
        int index = message.indexOf('/');
        boolean readFromFile = false;
        if (message.charAt(0) == '_') {
            readFromFile = true;
            message = message.substring(1);
        }
        list.add(new Deadlines(message.substring(9, index - 1), message.substring(index + 3), readFromFile));
    }

    /**
     * Duke adds a Events task.
     * @param message Details of the Events task.
     */
    public void addEvent(String message) {
        int index = message.indexOf('/');
        list.add(new Events(message.substring(6, index - 1), message.substring(index + 4)));
    }

    /**
     * Duke adds a DoAfter task.
     * @param message Details of the DoAfter task.
     */
    public void addDoAfter(String message) {
        int index = message.toLowerCase().indexOf("after");
        boolean readFromFile = false;
        if (message.charAt(0) == '_') {
            readFromFile = true;
            message = message.substring(1);
        }
        list.add(new DoAfter(message.substring(3, index - 1), message.substring(index + 5), readFromFile));
    }

    /**
     * Duke deletes a task as defined by users.
     * @param message The selected task as defined by user to delete.
     * @return The task that was deleted.
     * @throws DukeExceptionInvalidTaskInputFormat when users' input is not readable by duke.
     */
    public String deleteTask(String message) throws DukeExceptionInvalidTaskInputFormat {
        if (message.length() < 7 || message.charAt(6) != ' ' || message.charAt(7) == ' ') {
            throw new DukeExceptionInvalidTaskInputFormat();
        }
        String number = message.substring(7).trim();
        int index = Integer.parseInt(number);
        index = index - 1;
        String selectedTask = displaySelectedTask(index);
        list.remove(index);
        return selectedTask;

    }

    /**
     * Duke sets a task done as defined by users.
     * @param message The selected task defined by user to be set as done.
     * @return The task that was set as done.
     * @throws DukeExceptionInvalidTaskInputFormat when users' input is not readable by duke.
     */
    public String doneTask(String message) throws DukeExceptionInvalidTaskInputFormat {
        if (message.length() < 5 || message.charAt(4) != ' ' || message.charAt(5) == ' ') { // Handling errors: When user types done1 done2 or done   2
            throw new DukeExceptionInvalidTaskInputFormat();
        }
        String number = message.substring(5).trim();
        int index = Integer.parseInt(number);
        index = index - 1;
        this.list.get(index).setTaskDone();
        String selectedTask = displaySelectedTask(index);
        return selectedTask;
    }
}

