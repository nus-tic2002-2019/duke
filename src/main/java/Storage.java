import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Stores method to deal with loading and saving from file.
 * */
public class Storage {
    protected String filePath;
    protected boolean isLoad;
    /**
     * Create new Storage.
     * @param filePath file address.
     * */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.isLoad = true;
    }
    /**
     * Load file to new Duke task list.
     * @param tasks task list to be loaded.
     * */
    public void loadFile(ArrayList<Task> tasks) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = br.readLine()) != null) {
                String[] command = s.split("\\|", 0);

                switch (command[0].trim()) {
                    case "T":
                        tasks.add(new Todo(command[2].trim()));
                        if (command[1].trim().equals("\u2713"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "E":
                        tasks.add(new Event(command[2].trim(), command[3].trim(),
                                Parser.convertDate(command[3].trim())));
                        if (command[1].trim().equals("\u2713"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "D":
                        tasks.add(new Deadline(command[2].trim(), command[3].trim(),
                                Parser.convertDate(command[3].trim())));
                        if (command[1].trim().equals("\u2713"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                }
            }
        } catch (IOException e) {
            UI.splitLine();
            System.out.println("File not found");
            System.out.println("Pls key in the correct file address with '\\'" );
            UI.splitLine();
            isLoad = false;
        }
    }
    /**
     * Save updates to file.
     * @param tasks task list to be saved.
     * */
    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            for( int i=0 ; i<tasks.size() ; i++ ){
                String s = tasks.get(i).save_toString() + System.lineSeparator();
                byte b[]= s.getBytes();
                out.write(b);
            }
            out.close();
        } catch (IOException e) {
            UI.splitLine();
            System.out.println("Oops!! Cannot save file!");
            UI.splitLine();
        }
    }

}
