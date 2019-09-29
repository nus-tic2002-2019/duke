import java.util.Scanner;                               //Scanner library
import java.util.Arrays;                                //Arrays library
import java.util.regex.*;                               //Regular Expressions library

public class Duke {

    public static void updateStore(Task[] storage, String reply, int i)
    {
        storage[i] = new Task(reply);
    }

    public static void printStore(Task[] storage, int count)
    {
        for (int i = 0; i < count; i++)
        {
            System.out.println((i+1) + ".[" + storage[i].getIcon() + "] " + storage[i].printTask());
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

        Task[] storage = new Task[100];                 //Creating empty array of 100 to store
        int storeCount = 0;                             //Create count for items within storage
        int num = 0;                                        //Store for the integer input

        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        String reply = myObj.nextLine();                //Reads the next line which is the input
        reply = reply.toLowerCase();                    //Avoid error on input due to input of upper/lower casing

        while (!reply.equals("bye"))
        {
            if (reply.equals("list"))                   //Checks if user asks for stored strings
            {
                printStore(storage, storeCount);
                reply = myObj.nextLine();               //Reads the next line for next input
            }
            else if (reply.startsWith("done"))          //if user is adding marking items as done
            {
                Pattern p = Pattern.compile("\\d+");    //Using pattern to find digit
                Matcher m = p.matcher(reply);           //Find pattern as stipulated in reply
                while (m.find())
                {
                    String temp = m.group();            //Store the patterns found
                    num = Integer.parseInt(temp);       //Changes into integer
                }

                num = num-1;                            //Aligns numbering with stored array[]

                storage[num].markDone();                //Update Task as done
                System.out.println("Nice! I've marked this task as done:\n[" + storage[num].getIcon() + "] " + storage[num].printTask());

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
