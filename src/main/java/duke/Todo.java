package duke;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    /***
     * overloaded constructor from task name and task done
     * @param taskName
     * @param taskDone
     */
    public Todo (String taskName, boolean taskDone) {
        super(taskName, taskDone); // calls the parent constructor
    }

    /***
     * default constructor
     */
    public Todo() {
        super();
    }

    /***
     * converting todo task input into string format
     * @return
     */
    public String toString() {
        return "[T]" + super.toString();
    }
    public void write(FileWriter storage) throws IOException {
        storage.write("T\n"); //to represent as todo
      super.write(storage);
    }
}
