package basic;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    /**
     * Constructs a new task list and initialises it with an empty ArrayList.
     */
    public ArrayList<Task> task = new ArrayList<Task>();

    /**
     * Checks if the task was marked as done
     * @param myString one task in the txt file
     * @return true if the tasks was marked as done, else return false
     */
    private boolean isDone(String myString){
        return !myString.toLowerCase().contains("[✘]");
    }

    /**
     * Converts all strings in the txt file to tasks
     * @param list      task list in string form.
     */
    public TaskList(ArrayList<String> list) {
        for (String s : list) {
            int positionOfDes = s.indexOf("]");
            int startPosition;
            if (s.contains("("))
                startPosition = s.indexOf("(");
            else {
                startPosition = s.length() + 1;
                positionOfDes++;
            }
            String description = s.substring(positionOfDes+4, startPosition-1);

            if (s.contains("[T]")) {
                Todo oneTask = new Todo(description);
                addTask(oneTask);
            } else if (s.contains("[D]")) {
                int positionOfDate = s.indexOf("by");
                int endPosition = s.indexOf(")");
                String dateString = s.substring(positionOfDate+4, endPosition);
                DateTimeFormatter format = DateTimeFormatter
                        .ofPattern("d MMM yyyy, HHmm");
                LocalDateTime date = LocalDateTime.parse(dateString, format);
                String day = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
                Deadline oneTask = new Deadline(description, day);
                addTask(oneTask);
            } else {
                int positionOfDate = s.indexOf("at");
                int endPosition = s.indexOf(")");
                String dateString = s.substring(positionOfDate+4, endPosition);
                DateTimeFormatter format = DateTimeFormatter
                        .ofPattern("d MMM yyyy, HHmm");
                LocalDateTime date = LocalDateTime.parse(dateString, format);
                String day = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
                Event oneTask = new Event(description, day);
                addTask(oneTask);
            }
            if (isDone(s)){
                returnTask(sizeOfTask()- 1).isDone = true;
            }
        }
    }

    ArrayList<Task> returnList() {
        return task;
    }

    /**
     * Returns the task from the specified index from the task list.
     * @param num  The index the task.
     * @return Task The task at the specified index.
     */
    public Task returnTask(int num) {
        return task.get(num);
    }

    /**
     * Adds a task to the task list.
     * @param task1  The task that is required to be added to the task list.
     */
    public void addTask(Task task1) {
        task.add(task1);
    }

    /**
     * Deletes a task from the task list.
     * @param num  The index of a task that is required to be removed from the task list.
     */
    public void deleteTask(int num) {
        task.remove(num);
    }

    /**
     * Returns the size of the current task list.
     * @return int  The size of the current task list.
     */
    public int sizeOfTask() {
        return task.size();
    }

}
