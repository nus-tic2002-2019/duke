package subclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Storage {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void loadFile(String filePath) throws FileNotFoundException, ParseException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int num = 1;
        while (s.hasNext()) {
            String line_input = s.nextLine();
            //add todo, if any
            if (line_input.contains("T|")) {
                String input_items = line_input.substring(line_input.lastIndexOf("|") + 1);
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Todo(input_items));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            //add deadline, if any
            if (line_input.contains("D|")) {
                String input_by = line_input.substring(line_input.lastIndexOf("|") + 1);
                String input_items = line_input.substring(line_input.indexOf("|") + 3, line_input.lastIndexOf("|"));
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Deadline(input_items, input_by));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            //add event, if any
            if (line_input.contains("E|")) {
                String input_by = line_input.substring(line_input.lastIndexOf("|") + 1);
                String input_items = line_input.substring(line_input.indexOf("|") + 3, line_input.lastIndexOf("|"));
                String task_status_num = line_input.substring(line_input.indexOf("|") + 1);
                Task.add_task(new Event(input_items, input_by));
                if (task_status_num.contains("1")){
                    Task.markDone(num);
                }
            }
            num++;
        }
    }

    public static String toTxt (String outputs) {
        String newOutput = outputs.replace("[", "");
        newOutput = newOutput.replace("]", ",");
        newOutput = newOutput.replace("(", ",");
        newOutput = newOutput.replace(")", "");
        newOutput = newOutput.replace("\u2713", "1");
        newOutput = newOutput.replace("\u2718", "0");
        newOutput = newOutput.replace("by:", "");
        newOutput = newOutput.replace("at:", "");
        newOutput = newOutput.replace(", ", ",");
        newOutput = newOutput.replace("  ,", ",");
        newOutput = newOutput.replace(",", "|");
        newOutput = newOutput.replace(" |", "|");
        return newOutput;
    }

}
