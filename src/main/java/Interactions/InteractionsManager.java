package Interactions;
import storage.Storage;
import main.*;
import java.io.IOException;
import java.util.Scanner;

public class InteractionsManager {

    public void start(Storage my_file) throws DukeException, IOException {
        System.out.println("     Hello! I'm main.Duke. What can I do for you?\n");
        Operations operations = new Operations(my_file);
        InteractionsParser ip = new InteractionsParser(operations);
        ip.parse();
    }

    private String getUserCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


}
