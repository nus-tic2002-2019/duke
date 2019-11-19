package main.duke.storage;

import main.duke.exception.DukeException;
import main.duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String path_str = "data/duke.txt";


    /**
     * @param t
     * @return false means no saved data, true means has saved data.
     */
    public void readSaveFile(TaskList t) throws DukeException {
        File f = new File(path_str);
        if (!f.exists()) {
            System.out.println("Save file not found. Creating new file!");
            f = writeToSaveFile("");
            System.out.println(f.getAbsolutePath());
        }
        loadSaveFile(f, t);
    }

    public void loadSaveFile(File f, TaskList t) throws DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                t.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * @param s string representation of data to be saved.
     * @return Writes s to savefile at ./data/duke.txt, overwriting the original content.
     */
    public File writeToSaveFile(String s) {
        File f = new File(path_str);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(path_str);
            fw.write(s);
            fw.close();
        } catch (IOException e) {

        }
        return f;
    }

    public Storage() {
    }

}
