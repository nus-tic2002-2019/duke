import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import tasks.*;

/**
 * Performs all the file handling
 */

public class Storage {
    private String filePath;
    private static ArrayList<Task> userArr;
    private static Map<String, String> map;

    /**
     * Constructor for Storage class
     * @param filePath - the file path which the ArrayList will be loaded from or saved into.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
        userArr = new ArrayList<Task>();
        map = new HashMap();
        map.put("todo", "T");
        map.put("event", "E");
        map.put("deadline", "D");
    }

    public ArrayList<Task> load() throws IOException{
        String actionType;
        String taskStatus;
        String taskDesc;
        LocalDate taskDate;
        LocalTime taskTime;

        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            int i = 0;
            s.useDelimiter("\\s*\\|\\s*|\\r\\n");
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM d yyyy");
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mma");

            while (s.hasNext()) {
                actionType = s.next();
                taskStatus = s.next();
                //taskDesc = "";

                switch (actionType) {
                    case "T":
                        taskDesc = s.next();
                        userArr.add(new Todo(taskDesc));
                        break;
                    case "E":
                        taskDesc = s.next();
                        taskDate = LocalDate.parse(s.next(),formatDate);
                        taskTime = LocalTime.parse(s.next(),formatTime);
                        userArr.add(new Event(taskDesc, taskDate, taskTime));
                        break;
                    case "D":
                        taskDesc = s.next();
                        taskDate = LocalDate.parse(s.next(),formatDate);
                        userArr.add(new Deadline(taskDesc, taskDate));
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                if (taskStatus.equals("true")) {
                    userArr.get(i).isDone();
                }
                i++;
            }
            s.close();
        } catch (IOException e) {
            System.out.println("IOException error - Module: loadFromFile");
        }
        return userArr;
    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void appendToFile(String actionType, int taskNo, String textToAppend) throws IOException {
        String temp;
        int num = userArr.size()-1;

        if (actionType.equals("done") || actionType.equals("delete")) {
            temp = textToAppend + System.lineSeparator();
        } else {
            temp = map.get(actionType) + " | " + userArr.get(num).getDone() + textToAppend + System.lineSeparator();
        }

        FileWriter fw = new FileWriter(filePath, true);
            fw.write(temp);
            fw.close();
    }

    public void replaceFile(String actionType, String taskNo) throws IOException{
        try {
            Files.copy(Paths.get(filePath), Paths.get("data/tempTaskList.txt"));
            File f = new File("data/tempTaskList.txt");
            Scanner s = new Scanner(f);

            int num = Integer.parseInt(taskNo);
            writeToFile("");
            int i = 0;
            while (i < num-1) {
                appendToFile(actionType, i, s.nextLine());
                i++;
            }

            if (actionType.equals("done")) {
                String a = userArr.get(i).getClass().getSimpleName().toLowerCase();
                String temp = map.get(a) + " | " + userArr.get(i).getDone() + " | " + userArr.get(i).getTask();
                if (a.equals("deadline")) {
                    temp = temp + " | " + userArr.get(i).getDate();
                } else if (a.equals("event")) {
                    temp = temp + " | " + userArr.get(i).getDate() + " | " + userArr.get(i).getTime();
                }
                appendToFile(actionType, i, temp);
                i++;
            }
            s.nextLine();

            while (i < userArr.size()) {
                appendToFile(actionType, i, s.nextLine());
                i++;
            }
            s.close();
            Files.delete(Paths.get("data/tempTaskList.txt"));
        } catch (IOException e) {
            System.out.println("module: IOException");
        }
    }
}

