package main;



import main.TaskLists.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static main.Duke.Tasks;

// TODO Create filepath and implement save and Load features
public class Storage {

    private String filepath;
    private static File file;

    public Storage(String filepath) throws IOException {
        this.filepath = filepath;


    }


    // Creates or Loads Existing File based on file path
    public void start() throws IOException {
        this.file = new File(filepath);

        //Create the file
        if (file.createNewFile())
        {
            System.out.println("New File is created!");
        } else {
            //TODO - Create method to load files to ArrayList
            this.load();
        }
    }

    public void load() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String read;

        if (file.length() == 0){
            System.out.println("File is Empty");
        } else {
            while ((read = br.readLine()) != null)
                Tasks.add(Parser.inputParse(read));
            System.out.println("Records Loaded to ArrayList");
        }

}

    public static void save() throws IOException {

        //Delete Existing Content
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        //Write Content
        try (FileWriter fritter = new FileWriter(file, true);
             BufferedWriter buffer = new BufferedWriter(fritter)) {

            for (Object input : Tasks) {
                buffer.write(Parser.outputParse((Task) input));
                buffer.newLine();
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

}
