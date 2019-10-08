import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        InteractionsManager im = new InteractionsManager();
        try{
            im.start();
        }
        catch (DukeException x){
            System.out.println("Not a valid input");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not a valid input");
        }

    }
}
