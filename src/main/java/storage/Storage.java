package storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Storage {

    protected String filename;

    public Storage(String name){
        this.filename = name;
    }

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
}
