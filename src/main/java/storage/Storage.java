package storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import tasklist.*;
import java.io.FileWriter;
import java.io.IOException;
import task.*;

public class Storage {

    protected String filename;

    /**
     * Constructor of Storage
     * @param name
     */
    public Storage(String name){
        this.filename = name;
    }

    /**
     * Static function to write string to files
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Return ArrayList of the commands found in the text file
     * @return ArrayList<String>
     * @throws FileNotFoundException
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> output = new ArrayList<>();
        File f = new File(filename);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            output.add(s.nextLine());
        }
        s.close();
        return output;
    }

    /**
     * Save the ArrayList<String> into a text file
     * @param task
     */
    public void save(TaskList task) {
        String file = "/Users/tankaiwei/Desktop/duke/src/main/java/data/test.txt";
        String output = "";
        try {
            for(int i = 0; i < task.getSize(); i++){
                output = output + task.getTask(i).toString() + "\n";
            }
            writeToFile(file, output);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
