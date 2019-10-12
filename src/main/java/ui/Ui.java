package ui;

import storage.Storage;
import task.Task;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 * Represents the user interaction class where
 * it will parse the respective output to the users.
 * */
public class Ui {
    /**
     * Creation of checker variable for status.
     * */
    private boolean isChanged=false;
    /**
     * Prints welcome message of duke.
     * */
    public void showWelcome(){
        showUserLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        showUserLine();
    }
    /**
     * Prints bye message of duke.
     * */
    public void printBye(){
        showUserLine();
        System.out.println("Bye. Hope to see you again soon!");
        showUserLine();
    }
    /**
     * Scan and reads the input of user.
     * */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    /**
     * Prints out the text for user to see.
     * */
    public static void showToUser(String s){
        System.out.println(s);
    }
    /**
     * Prints out borderline.
     * */
    public static void showUserLine(){
        showToUser("____________________________________________________________"+System.lineSeparator());
    }
    /**
     * Prints out error message for user to see.
     * */
    public void showError(String s){
        System.out.println("Error: "+s);
    }
    /**
     * Status checker.
     * */
    public void changed(){
        isChanged=true;
    }
    /**
     * Saving a new copy of the list of task.
     * */
    public void saveCopy(Storage store, List<Task> tasks) throws IOException {
        store.save(tasks);
        showUserLine();
        showToUser("Changes are saved."+System.lineSeparator());
        showUserLine();
    }
    /**
     * Saving on existing copy of save.
     * */
    public void saveNow(Storage store, List<Task> changed) throws IOException {
        if(isChanged){
            saveCopy(store,changed);
        }else{
            showUserLine();
            showToUser("No change made."+System.lineSeparator());
            showUserLine();
        }
    }
    /**
     * Setting directory path for saved file.
     * */
    public void setPath(Storage store, String newPath){
        store.changePath(newPath);
    }
}

