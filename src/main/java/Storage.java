import task.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_PATH = "src/main/java/data/duke.txt";
    private String filePath;

    public Storage() {
        this.filePath = DEFAULT_PATH;
    }

    /**
     * Save the list of tasks into a text file in duke's root folder as duke.txt whenever user exits from duke.
     * @param list The current list duke is handling.
     * @throws IOException If user defines their own file path, and the file path has some problem.
     */
    public void saveFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String s = "";
        for (int j = 0; j < list.size(); j++) {
            s = s + list.get(j).getType() + " " + list.get(j).getTaskStatus() + " " + list.get(j).getTask() + " "
                    + list.get(j).getDetails() + System.lineSeparator();
            s = s.replace("(by:", "|").replace("(at:", "|").
                    replace(")", "").replaceAll("\\[", "").
                    replaceAll("]", "|").replace("\u2713", "1 ").
                    replace("\u2718", "0 ").replace("after", "|");
        }
        fw.write(s);
        fw.close();
    }

    /**
     * Read files from the default file path of duke, duke.txt whenever duke starts up.
     * @param tasks The new task list created for this duke session to be throw into this function from the start of the program.
     * @throws IOException If the filepath has some problems.
     * @throws DukeException If the file context is not in the duke's list format.
     */
    public void readFile(TaskLists tasks) throws IOException, DukeExceptionFileInput {
        BufferedReader s = null;
        s = new BufferedReader(new FileReader(DEFAULT_PATH));
        String input = null;
        while ((input = s.readLine()) != null) {
            if (input.charAt(0) == 'T' || input.charAt(0) == 'E' || input.charAt(0) == 'D' || input.charAt(0) == 'A') {
                char status = input.charAt(3);
                assert status == '0' || status == '1' : "At this point in time, task status should be either " +
                        "done(1) or not done(0), please check your task status in Duke.txt again.";
                switch (Character.toString(input.charAt(0))) {
                    case "T":
                        input = input.substring(6);
                        tasks.addToDo("task " + input);
                        if (status == '1') {
                            int index = tasks.getSize() - 1;
                            tasks.getList().get(index).setTaskDone();
                        }
                        break;
                    case "E":
                        input = input.substring(6);
                        input = input.replace("|", "/at");
                        tasks.addEvent("event" + input);
                        if (status == '1') {
                            int index = tasks.getSize() - 1;
                            tasks.getList().get(index).setTaskDone();
                        }
                        break;
                    case "D":
                        input = input.substring(6);
                        input = input.replace("|", "/by");
                        tasks.addDeadline("_deadline" + input);
                        if (status == '1') {
                            int index = tasks.getSize() - 1;
                            tasks.getList().get(index).setTaskDone();
                        }
                        break;
                    case "A":
                        input = input.substring(6);
                        input = input.replace("|", "after");
                        tasks.addDoAfter("_do" + input);
                        if (status == '1') {
                            int index = tasks.getSize() - 1;
                            tasks.getList().get(index).setTaskDone();
                        }
                        break;
                    default:
                        break;
                }
            } else {
                throw new DukeExceptionFileInput();
            }
        }
    }
}


