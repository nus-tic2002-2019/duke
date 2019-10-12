import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private boolean isChanged=false;

    public void showWelcome(){
        showUserLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showUserLine();
    }

    public void printBye(){
        showUserLine();
        System.out.println("Bye. Hope to see you again soon!");
        showUserLine();
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void showToUser(String s){
        System.out.println(s);
    }

    public static void showUserLine(){
        showToUser("____________________________________________________________"+System.lineSeparator());
    }

    public void showError(String s){
        System.out.println("Error: "+s);
    }

    public void changed(){
        isChanged=true;
    }

    public void saveCopy(Storage store, List<Task> tasks) throws IOException {
        store.save(tasks);
        showUserLine();
        showToUser("Changes are saved."+System.lineSeparator());
        showUserLine();
    }
    public void saveNow(Storage store, List<Task> changed) throws IOException {
        if(isChanged){
            saveCopy(store,changed);
        }else{
            showUserLine();
            showToUser("No change made."+System.lineSeparator());
            showUserLine();
        }
    }
    public void setPath(Storage store, String newPath){
        store.changePath(newPath);
    }
}

