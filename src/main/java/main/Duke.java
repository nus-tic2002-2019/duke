package main;

import Interactions.InteractionsManager;
import storage.Storage;

import java.io.File;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        InteractionsManager im = new InteractionsManager();
        try{
            String basePath = new File("").getAbsolutePath();
            String filepath = new File("src/main/java/storage/task_list.txt").getAbsolutePath();
            Storage my_file = new Storage(filepath);
            im.start(my_file);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not a valid input");
        } catch (IOException e) {
            System.out.println("IO Exception found");
        } catch (DukeException e) {
            e.printStackTrace();
        }

    }
}
