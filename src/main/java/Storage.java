import java.io.*;

import task.*;

import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_PATH = "D:/Keeyong Folder/NUS/Semester 2-1/TIC2002 Introduction to Software Engineering/Project/src/main/java/data/duke.txt";
    private String filePath;

    public Storage() {
        this.filePath = DEFAULT_PATH;
    }

    public void saveFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String s = "";
        for (int j = 0; j < list.size(); j++) {
            s = s + list.get(j).getType() + " " + list.get(j).getTaskStatus() + " " + list.get(j).getTask() + " " + list.get(j).getDetails() + System.lineSeparator();
            s = s.replace("(by:", "|").replace("(at:", "|").replace(")", "").replaceAll("\\[", "").
                    replaceAll("]", "|").replace("\u2713", "1").replace("\u2718", "0");
        }
        fw.write(s);
        fw.close();
    }


    public void readFile(TaskLists tasks) throws IOException,DukeException {
        BufferedReader s = null;
        s = new BufferedReader(new FileReader(DEFAULT_PATH));
        String input = null;
        while ((input = s.readLine()) != null) {
            if (input.charAt(0) != 'T' || input.charAt(0) != 'E' || input.charAt(0) != 'D'){
                throw new DukeException();
            }
            char status = input.charAt(3);
            switch (Character.toString(input.charAt(0))) {
                case "T":
                    input = input.substring(5);
                    tasks.addToDo("task " + input);
                    if (status == '1') {
                        int index = tasks.getSize() - 1;
                        tasks.getList().get(index).setTaskDone();
                    }
                    break;
                case "E":
                    input = input.substring(5);
                    input = input.replace("|", "/at");
                    tasks.addEvent("event" + input);
                    if (status == '1') {
                        int index = tasks.getSize() - 1;
                        tasks.getList().get(index).setTaskDone();
                    }
                    break;
                case "D":
                    input = input.substring(5);
                    input = input.replace("|", "/by");
                    tasks.addDeadline("_deadline" + input);
                    if (status == '1') {
                        int index = tasks.getSize() - 1;
                        tasks.getList().get(index).setTaskDone();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}


