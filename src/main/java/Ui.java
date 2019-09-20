import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private boolean isChanged=false;

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printBye(){System.out.println("Bye. Hope to see you again soon!");}

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showToUser(String s){
        System.out.println(s);
    }


    public void showError(String s){
        System.out.println("Error: "+s);
    }

    public void changed(){
        isChanged=true;
    }

    public void saveCopy(Storage store, List<Task> tasks) throws IOException {
        store.save(tasks);
        showToUser("Changes are saved."+System.lineSeparator());
    }
    public void saveNow(Storage store, List<Task> changed) throws IOException {
        if(isChanged){
            saveCopy(store,changed);
        }else{
            showToUser("No change made."+System.lineSeparator());
        }
    }
    public void setPath(Storage store, String newPath){
        store.changePath(newPath);
    }
}

