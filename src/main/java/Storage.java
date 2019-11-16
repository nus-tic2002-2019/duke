import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File file;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    void createFile() throws IOException {
        file.createNewFile();
    }

    void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            tasks.add(new Task(scanner.nextLine().substring(3)));
        }
    }

    void writeToFile(String text) throws IOException {
        FileWriter filewriter = new FileWriter(filepath, true);

        filewriter.write(text);
        filewriter.close();
    }

    /*public void removeLine(String lineContent) throws IOException {
        File file = new File(filepath);
        List<String> out = Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }*/

}
