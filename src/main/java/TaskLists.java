import task.Task;
import task.ToDo;
import task.Deadlines;
import task.Events;

import java.util.ArrayList;


public class TaskLists {

    public ArrayList<Task> list;

    public TaskLists() {
        this.list = new ArrayList<Task>();
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> getList(){
        return this.list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

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

    public String displayLatestTask() {
        int number = getSize() - 1;
        return list.get(number).getType() + list.get(number).getTaskStatus() + " " + list.get(number).getTask() + " " + list.get(number).getDetails();
    }

    public String displaySelectedTask(int number) {
        return list.get(number).getType() + list.get(number).getTaskStatus() + " " + list.get(number).getTask() + " " + list.get(number).getDetails() + "\n";
    }

    public void addToDo(String message) {
        list.add(new ToDo((message.substring(5))));
    }

    public void addDeadline(String message) {
        int index = message.indexOf('/');
        list.add(new Deadlines(message.substring(9, index - 1), message.substring(index + 4)));
    }

    public void addEvent(String message) {
        int index = message.indexOf('/');
        list.add(new Events(message.substring(6, index - 1), message.substring(index + 4)));
    }

    public String deleteTask(String message) throws DukeExceptionInvalidTaskInputFormat {
        if (message.length() < 7 || message.charAt(6) != ' ' || message.charAt(7) == ' ') { // Handling errors: When user types done1 done2 or done   2
            throw new DukeExceptionInvalidTaskInputFormat();
        }
        String number = message.substring(7).trim();
        int index = Integer.parseInt(number);
        index = index - 1;
        String selectedTask = displaySelectedTask(index);
        list.remove(index);
        return selectedTask;

    }

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

