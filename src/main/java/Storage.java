import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.io.File;
import java.io.FileNotFoundException;

public class Storage {

    private File file;
    private BufferedReader fileRead;

    public Storage(String filePath) {
        try {
            this.file = new File(filePath);
            fileRead = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
    /** 
     * create a file when storage file is not found
     */
    public void createFile() {
        try {
            if (file.getParentFile().mkdirs()) {
            }
            if (file.createNewFile()) {
            }
        } catch (IOException e) {
            new IOException("The file " + file.getAbsolutePath() + " is unable to be create.");
        }
    }

    /**
     * save input from user to file
     * @throws IOException
     */
    public void saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        String add = "";
        for (int i = 0; i < TaskList.getSize(); ++i) {
            Task task = TaskList.getTask(i);
            String taskClass = "";
            int isDone = 0;
            String description = task.description;
            Date date = null;

            if (task instanceof Todo) {
                taskClass = "T";
            } else if (task instanceof Event) {
                taskClass = "E";
                date = ((Event) task).at;
            } else if (task instanceof Deadline) {
                taskClass = "D";
                date = ((Deadline) task).by;
            }
            if (task.isDone) {
                isDone = 1;
            } else {
                isDone = 0;
            }
            if (date.equals(null)) {
                add += taskClass + " | " + Integer.toString(isDone) + " | " + description + "\n";
            } else {
                add += taskClass + " | " + Integer.toString(isDone) + " | " + description + " | " + date + "\n";
            }
        }
        try {
            fileWriter.write(add);
            fileWriter.close();
        } catch (IOException e) {
            new IOException("The file " + file.getAbsolutePath() + " has encountered an error writing." + e);
        }
    }

    /**
     * Read file and add item to array list when start of Duke
     * @throws DukeException
     * @throws ParseException
     */
    public ArrayList<Task> readFile() throws IOException, ParseException, DukeException {
        ArrayList<Task> TaskList = new ArrayList<>(100);
        String line = fileRead.readLine();
        while(line != null){
            String[] splitLine = line.split(" \\| ");
            switch(splitLine[0]){
                case "E":
                    Event newEvent = new Event(splitLine[2], EventCommand.convertDateTime(splitLine[3]));
                    if(splitLine[1].equals("1")){
                        newEvent.markAsDone();
                    }
                    TaskList.add(newEvent);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(splitLine[2], DeadlineCommand.convertDateTime(splitLine[3]));
                    if(splitLine[1].equals("1")){
                        newDeadline.markAsDone();
                    }
                    TaskList.add(newDeadline);
                    break;
                case "T":
                    Todo newTodo = new Todo(splitLine[2]);
                    if(splitLine[1].equals("1")){
                    newTodo.markAsDone();
                    }
                    TaskList.add(newTodo);
            }
            line = fileRead.readLine();
        }   
        fileRead.close();
        return TaskList;
    }
}