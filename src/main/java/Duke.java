import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String[] tmp = new String[100];
        int size = 0;
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
            else if(inputStr.toLowerCase().equals("list"))
            {
                for(int i = 0; i < size; i++)
                {
                    System.out.format("%d: %s\n", i + 1, tmp[i]);
                }
            }
            else
            {
                System.out.println("added: " + inputStr);
                tmp[size++] = inputStr;
            }
        }
    }
}
