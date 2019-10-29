package storage;

import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filepath;
    public File f;

    /**
     * Duke is able to append added objects to a .txt file, as a way of "saving" to harddisk.
     *
     * @param filepath Expects a file "task_list.txt" in the path: /java/storage/task_list.txt
     *                 Returns a FileNotFound error otherwise.
     */
    public Storage(String filepath) {
        try {
            this.filepath=filepath;
            this.f = new File(filepath);
            Scanner scan = new Scanner(f); // create a Scanner using the File as the source

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    /**
     * This method is called when objects are added to the Duke list.
     * Newline is added to task_list.txt to reflect added object. Follows the format:
     * Task type | Done or not done | task description | by when. For example:
     * D|0|eat lunch | 3pm
     * @param tasks Takes an ArrayList of tasks object, iterates through it, and appends to task_list.txt
     * @throws IOException
     */
    public void updateFile(ArrayList<Task> tasks) throws IOException {
        // function takes an ArrayList of tasks. Iterate through the tasklist and write to the text file.

        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).getSaveFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();
        }
        catch(IOException e){
            System.out.println("IO Exception found");
        }
    }

    public ArrayList<Task> readFromFile() throws FileNotFoundException {
        // Returns an ArrayList<task.Task>. Reads from f.
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(this.f);
        System.out.println("storage.readFromFile() output is:");
        while (s.hasNext()) {
            // process each line with s.nextLine()
            String currentLine = s.nextLine();
            String[] arrayCurrentLine = currentLine.split("|");
            // check what type of event
            String taskType = arrayCurrentLine[0];
            switch (taskType){
                case "E":


            }

            System.out.println(s.nextLine());
        }
        return tasks;
    }
}
