package duke.storage;

import duke.task.TaskList;
import duke.others.Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    protected String path;
    protected String data;

    public Storage(String filePath) {
        this.path = filePath;
        this.data = "";
    }

    public String load() throws FileNotFoundException {
        File f = new File(this.path);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            this.data = this.data + s.nextLine() + System.lineSeparator();
        }
        return this.data;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(new File(this.path));
        int index = 0;
        for (int i = 0; i < tasks.size(); ++i) {
            String input = index + ";" + Utility.constructInput(tasks.get(i));
            writer.write(input + System.lineSeparator());
            index++;
        }
        writer.close();
    }

    public void append(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(System.lineSeparator());
        fw.write(textToAppend);
        fw.close();
    }

    public void updateLine(int n, String input) throws IOException {
        Path path = Paths.get(this.path);
        String content = new String(Files.readAllBytes(path));
        String line = readLine(n);
        content = content.replaceAll(line, input);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    private String readLine(int n) throws IOException {
        return Files.readAllLines(Paths.get(this.path)).get(n);
    }

}
