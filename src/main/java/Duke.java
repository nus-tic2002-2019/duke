import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        while(true)
        {
            String inputStr = input.nextLine();
            if(inputStr.toLowerCase().equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else
                System.out.println(inputStr);
        }
    }
}
