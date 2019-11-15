package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import DukeTasks.*;

public class Storage {

    private Path path = Paths.get(System.getProperty("user.dir"));
    private File data = new File(path + "/data/duke.txt");

    public Storage() {

    }
	public ArrayList<Task> readFromFile() {

        ArrayList<Task> toReturn = new ArrayList<>();

        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {

                String[] next = sc.nextLine().split(" | ");
                switch (next[0]) {
                case ("T"):
                    Task todo = new Todo(next[4]);
                    if (Integer.valueOf(next[2]) == 1) {
                        todo.setStatusIconTrue();
                    }
                    toReturn.add(todo);
                    break;
                case ("E"):
                    Task event = new Event(next[4], next[6]);
                    if (Integer.valueOf(next[2]) == 1) {
                        event.setStatusIconTrue();
                    }
                    toReturn.add(event);
                    break;

                case ("D"):
                    Task deadline = new Deadline(next[4], next[6]);
                    if (Integer.valueOf(next[2]) == 1) {
                        deadline.setStatusIconTrue();
                    }
                    toReturn.add(deadline);
                    break;

                default:
                    break;
                }
            }
            sc.close();

        } catch (FileNotFoundException fileExp) {
            System.out.println(fileExp);
        }
        return toReturn;
    }


	public void writeToFile(ArrayList<Task> list) {

        try {
            FileWriter fw = new FileWriter(data);
            StringBuilder toWrite = new StringBuilder("");

            for (Task task : list) {
                toWrite.append(task.toSave() + " \n");
            }

            fw.write(toWrite.toString());
            fw.close();

        } catch (IOException ioExp) {
            System.err.println(ioExp);
        }
    }
}
