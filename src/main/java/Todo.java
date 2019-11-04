import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {

    public Todo (String taskName, boolean taskDone)
    {
        super(taskName, taskDone); // calls the parent constructor
    }

    public Todo() {
        super();
    }

    public String toString()
    {
        return "[T]" + super.toString();
    }
    public void write(FileWriter storage) throws IOException {
        storage.write("T\n"); //to represent as todo
      super.write(storage);
    }
}
