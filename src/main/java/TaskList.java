import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable{
    ArrayList<Task> line = new ArrayList<Task>();
    int count = 0;

    public int getCount() {
        return count;
    }

    public Task get(int index)
    {
        return line.get(index);
    }

    public void add(Task t)
    {
        line.add(t);
    }

    public void newTodoTask(String x, boolean done) {
        Todo t = new Todo(x, done);
        line.add(t);
        count++;
    }

    public void newDeadlineTask(String name, boolean done, String by) {
        Deadline d = new Deadline(name, false, by);
        line.add(d);
        count++;
    }

    public void newEventTask(String name, boolean done, String at)
    {
        Event e = new Event(name, false, at);
        line.add(e);
        count ++;
    }


    public Task removeTask(int index) {
        count --;
        return line.remove(index-1);

    }

    @Override
    public void write(FileWriter storage) throws IOException {
        for(int i=0; i<line.size(); i++)
        {
            line.get(i).write(storage); //write entire task list to storage
        }
    }

    @Override
    public void read(BufferedReader fileRead) throws IOException {
        String type = null;
        line.clear();
        while ((type = fileRead.readLine()) != null) {
            if (type.equals("T")) {
                Todo t = new Todo();
                t.read(fileRead);
                line.add(t);
                count++;
            } else if (type.equals("D")) {
                Deadline d = new Deadline();
                d.read(fileRead);
                line.add(d);
                count++;
            } else if (type.equals("E")) {
                Event e;
                e = new Event();
                e.read(fileRead);
                line.add(e);
                count++;
            }
        }
    }
}