package duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import duke.ui.ui;

/**
 * Start of the storage module
 */
public class storage {
    /**
     * Create variables for user interface (ui)
     */
    private static ui ui;

    /**
     * reads the saved textfile and returns it as an ArrayList
     * @return returns an ArrayList consisting of the saved textfile
     */
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

    /**
     * saves the array passed to it to a textfile
     * @param modifiedArray the array to be saved to a text file
     */
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
