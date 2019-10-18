import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        InteractionsManager im = new InteractionsManager();
        try{
            String filepath = "/Users/james/Desktop/tic2002/duke/src/main/java/task_list.txt";
            Storage my_file = new Storage(filepath);
            im.start(my_file);
        }
        catch (DukeException x){
            System.out.println("Not a valid input");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not a valid input");
        } catch (IOException e) {
            System.out.println("IO Exception found");
        }

    }
}
