import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class contains all the methods for file access. It includes
 * automatic creation of a duke.txt file, automatic loading of the file contents
 * and saving new tasks and updates to the duke.txt file.
 */
public class Storage {
    private String filepath;
    private File file;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * During program start up, the createFile method automatically creates
     * a new duke.txt file at "C:\Users\Shit\Documents\Duke Project\data" directory
     * if there is no duke.txt file existing in this directory.
     */
    void createFile() throws IOException {
        file.createNewFile();
    }

    /**
     * During program start up, the loadFile method automatically loads
     * the contents of the existing duke.txt file located at
     * "C:\Users\Shit\Documents\Duke Project\data" directory.
     */
    void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            tasks.add(new Task(scanner.nextLine().substring(3)));
        }
    }

    /**
     * The writeToFile method automatically updates the duke.txt file located
     * at "C:\Users\Shit\Documents\Duke Project\data" directory should there
     * be added task, deleted task and updated status.
     */
    void writeToFile(String text) throws IOException {
        FileWriter filewriter = new FileWriter(filepath, true);

        filewriter.write(text);
        filewriter.close();
    }

}
