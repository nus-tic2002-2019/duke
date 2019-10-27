import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReading {

    public static void fileReading(String filePath, ArrayList<Task> tasks) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s;

            while ((s = br.readLine()) != null) {
                String[] command = s.split("\\|", 0);

                switch (command[0].trim()) {
                    case "T":
                        tasks.add(new Todo(command[2].trim()));
                        if (command[1].trim().equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "E":
                        tasks.add(new Event(command[2].trim(), command[3].trim()));
                        if (command[1].trim().equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "D":
                        tasks.add(new Deadline(command[2].trim(), command[3].trim()));
                        if (command[1].trim().equals("1"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}