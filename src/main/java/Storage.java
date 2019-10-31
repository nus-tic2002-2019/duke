import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> userArr;
    private static Map<String, String> map;

    public Storage(String filePath, ArrayList<Task> userArr) {
        this.filePath = filePath;
        this.userArr = userArr;
        map = new HashMap();
        map.put("todo", "T");
        map.put("event", "E");
        map.put("deadline", "D");
        //createFile();
    }

    public void createFile() {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File created.");
            } else {
                System.out.println("File exists in the system");
            }
        } catch (IOException e) {
            System.out.println("");
        }
    }

    public ArrayList<Task> load() throws IOException{
        String actionType;
        String taskStatus;

        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            int i = 0;
            s.useDelimiter("\\s*\\|\\s*|\\r\\n");

            while (s.hasNext()) {
                actionType = s.next();
                taskStatus = s.next();

                switch (actionType) {
                    case "T":
                        userArr.add(new Todo(s.next()));
                        break;
                    case "E":
                        userArr.add(new Event(s.next(), s.next()));
                        break;
                    case "D":
                        userArr.add(new Deadline(s.next(), s.next()));
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

        if (actionType.equals("done") || actionType.equals("delete")) {
            temp = textToAppend + System.lineSeparator();
        } else {
            temp = map.get(actionType) + " | " + userArr.get(taskNo).done + textToAppend + System.lineSeparator();
        }

        FileWriter fw = new FileWriter(filePath, true);
            fw.write(temp);
            fw.close();
    }

    public void replaceFile(String actionType, String taskNo) throws IOException{
        try {
            Files.copy(Paths.get(filePath), Paths.get("data/tempTaskList.txt"));
            File f = new File ("data/tempTaskList.txt");
            Scanner s = new Scanner(f);

            int num = Integer.parseInt(taskNo);
            writeToFile("");
            int i = 0;
            while (s.hasNext()) {
                if (!userArr.get(i).done) {
                    String temp = "";
                    appendToFile(actionType, 0 , s.nextLine());
                } else {
                    String a = userArr.get(num-1).getClass().getSimpleName().toLowerCase();
                    String temp = map.get(a) + " | " + userArr.get(num-1).done + " | " + userArr.get(num-1).getTask();
                    appendToFile(actionType, 0 , temp);
                    s.nextLine();
                }
                i++;
            }
            s.close();
            Files.delete(Paths.get("data/tempTaskList.txt"));
        } catch (IOException e) {
            System.out.println("module: IOException");
        }
    }
}

