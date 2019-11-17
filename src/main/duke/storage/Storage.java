package main.duke.storage;

import java.io.File;
import  java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {

    private static final String path_str = "data/duke.txt";
    public void readSaveFile() throws FileNotFoundException, IOException {

        File f = new File(path_str);
        if(f.exists()){

        }
        else{
            System.out.println("Save file not found. Creating new file!");
            writeToSaveFile("");
        }
    }
    public void writeToSaveFile(String s) throws IOException{
        File f = new File(path_str);
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(path_str, true);
        fw.write(s);
        fw.close();
    }
    public Storage() {
    }

}
