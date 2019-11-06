package Interactions;
import storage.Storage;
import main.*;
import java.io.IOException;
import java.util.Scanner;

public class InteractionsManager {
    /**
     * Greets the user and begins user interaction process. This method should be called in the main Duke method.
     * @param my_file a Storage object. Recommended for storage object to be created in the Duke main method.
     * @throws DukeException
     * @throws IOException
     */
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
