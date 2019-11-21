package Storage;
import java.io.*;
import java.util.ArrayList;

import Tasks.Task;

import java.util.Scanner;

public class Storage {

    /**
     * Default path of file.
     */
    public static final String DEFAULT_PATH = "D:/NUS_STUFF/Java_Mod/duke/data/dukeTaskOuput.txt";

    private  String filename;

    /**
     * Storage class Constructor
     */
    public Storage(String filename){
        this.filename = filename;
    }

    public ArrayList<String> load() throws FileNotFoundException {

        ArrayList<String> output = new ArrayList<>();
        File f = new File(DEFAULT_PATH);
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            output.add(scan.nextLine());
        }
        scan.close();
        return output;
    }


    public void saveFile(ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter("data/dukeTaskOuput.txt");
        String tmp = "";
        try {
            for (int i = 0; i < list.size(); i++){
                tmp = tmp + list.get(i).toString() + System.lineSeparator();

                tmp = tmp.replace("(by:", "|").replace("(at:", "|").
                        replace(")", "").replaceAll("\\[", "").
                        replaceAll("]", "|").replace("\u2713", "1").
                        replace("\u2718", "0").replace("after", "|");
            }
            file.write(tmp);
            file.close();
        }

        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }



    }




}
