import java.util.Scanner;                               //Scanner library
import java.util.Arrays;                                //Arrays library
import java.util.regex.*;                               //Regular Expressions library
import java.util.ArrayList;                             //ArrayList library

public class Duke {

    public static void updateStore(Task[] storage, String reply, int i) throws NoClassException, DukeException
    {
        String replied = reply;

            if (reply.startsWith("todo"))
            {
                reply = reply.replace("todo", "");      //replacing the task with nothing
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                storage[i] = new ToDo(reply);
            }
            else if (reply.startsWith("deadline"))
            {
                reply = reply.replace("deadline", "");
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                String[] input = reply.split("/");
                storage[i] = new Deadline(input[0], input[1]);
            }
            else if (reply.startsWith("event"))
            {
                reply = reply.replace("event", "");
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                String[] input = reply.split("/");
                storage[i] = new Event(input[0], input[1]);
            }
            else
            {
                throw new NoClassException();
            }
    }

    public static void printStore(Task[] storage, int count)
    {
        for (int i = 0; i < count; i++)
        {
            System.out.println((i+1) + ". " + storage[i].printTask());
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
                try
                {
                    updateStore(storage, reply, storeCount);
                    storeCount++;
                    System.out.println("Got it. I've added this task:\n" + reply + "\nNow you have " + storeCount + " in the list.");  //Prints out echo + reply
                }
                catch (NoClassException e)
                {
                    System.out.println("Please state the type of Task..");
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Please input the correct format of task : [Task Type] [Task] / [date]");
                }
                catch (DukeException e)
                {
                    DukeException exceptional = new DukeException(reply);
                    System.out.println("☹ OOPS!!! The description of a " + exceptional.printtask() + " cannot be empty.");
                }
                reply = myObj.nextLine();               //Reads the next line for next input
            }
        }
        System.out.println("Bye. Hope to see you again soon!");


    }
}
