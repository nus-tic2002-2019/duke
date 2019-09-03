import java.util.Scanner;                               //Scanner library

public class Duke {
    public static void updateStore(String[] storage, String reply, int i)
    {
        storage[i] = reply;
    }

    public static void printStore(String[] storage, int count)
    {
        for (int i = 0; i < count; i++)
        {
            System.out.println((i+1) + ". " + storage[i]);
        }
    }

    public static void main(String[] args)
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        //Level-2
        String[] storage = new String[100];             //Creating empty array of 100 to store
        int storeCount = 0;                             //Create count for items within storage

        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        String reply = myObj.nextLine();                //Reads the next line which is the input

        while (!reply.equals("bye"))
        {
            if (reply.equals("list"))                   //Checks if user asks for stored strings
            {
                printStore(storage, storeCount);
                reply = myObj.nextLine();               //Reads the next line for next input
            }
            else
            {
                updateStore(storage, reply, storeCount);
                storeCount++;
                System.out.println("Added: " + reply);  //Prints out echo + reply

                reply = myObj.nextLine();               //Reads the next line for next input
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
