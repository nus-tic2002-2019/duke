package duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import duke.ui.ui;

public class storage {
    private static ui ui;

    public ArrayList readFile() {
        ui = new ui();
        ArrayList<String> TasksList = new ArrayList<String>();
        try (BufferedReader fileInput = new BufferedReader(new FileReader("list.txt"))) {
            String arrayInput;
            while ((arrayInput = fileInput.readLine()) != null) {
                TasksList.add(arrayInput);
            }
        } catch (Exception e) {
            ui.printLine1();
            ui.readError();
            ui.printLine1();
        }
        return TasksList;
    }

    public void saveFile(ArrayList<String> modifiedArray) {
        ui = new ui();
        try{
            FileWriter fileOutput = new FileWriter("list.txt");
            for(String str: modifiedArray) {
                fileOutput.write(str + System.lineSeparator());
            }
            fileOutput.close();
        } catch (Exception e) {
            ui.printLine1();
            ui.writeError();
            ui.printLine1();
        }
    }
}
