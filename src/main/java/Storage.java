import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;

public class Storage {

    public static void loadFile(String filePath, ArrayList<Task> tasks) {

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
                        tasks.add(new Event(command[2].trim(), command[3].trim(), Parser.converted_date(command[3].trim())));
                        if (command[1].trim().equals("\u2713"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                    case "D":
                        tasks.add(new Deadline(command[2].trim(), command[3].trim(), Parser.converted_date(command[3].trim())));
                        if (command[1].trim().equals("\u2713"))
                            tasks.get(tasks.size() - 1).markAsDone();
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public static void saveFile(String filePath, ArrayList<Task> tasks){

        try {
            FileOutputStream out = new FileOutputStream(filePath);
            for( int i=0 ; i<tasks.size() ; i++ ){
                String s = tasks.get(i).save_toString().
                        replaceAll("\\[","").
                        replaceAll("]","|").
                        replace("(by:","|").
                        replace("(at:","|").
                        replace(")","") +
                        System.lineSeparator();
                byte b[]= s.getBytes();
                out.write(b);
            }
            out.close();
        } catch (IOException e){
            System.out.println("Oops!! Cannot save file !");
        }
    }
}
