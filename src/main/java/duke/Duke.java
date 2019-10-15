package duke;                                           //list the package that this class is in

import duke.exception.*;                                //duke's exception packages (SoC)
import duke.task.*;                                     //duke's task packages

import java.util.Scanner;                               //Scanner library
import java.util.regex.*;                               //Regular Expressions library
import java.util.ArrayList;                             //ArrayList library

public class Duke {

    public static void updateStore(ArrayList<Task> storage, String reply, int i) throws NoClassException, DukeException
    {
        String replied = reply;

            if (reply.startsWith("todo"))
            {
                reply = reply.replace("todo", "");      //replacing the task with nothing
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                storage.add(new ToDo(reply));
            }
            else if (reply.startsWith("deadline"))
            {
                reply = reply.replace("deadline", "");
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                String[] input = reply.split("/");
                storage.add(new Deadline(input[0], input[1]));
            }
            else if (reply.startsWith("event"))
            {
                reply = reply.replace("event", "");
                if (reply.isBlank())
                {
                    throw new DukeException(replied);
                }
                String[] input = reply.split("/");
                storage.add(new Event(input[0],input[1]));
            }
            else
            {
                throw new NoClassException();
            }
    }

    public static void printStore(ArrayList<Task> storage, int count)
    {
        for (int i = 0; i < count; i++)
        {
            System.out.println((i+1) + ". " + storage.get(i).printTask());
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

        ArrayList<Task> storage = new ArrayList<>();    //Create ArrayList for tasks
        int storeCount = 0;                             //Create counter for items within storage
        int num = 0;                                    //Store for the integer input

        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm duke.Duke\n What can I do for you?");

        String reply = myObj.nextLine();                //Reads the next line which is the input
        reply = reply.toLowerCase();                    //Avoid error on input due to input of upper/lower casing

        while (!reply.equals("bye"))
        {
            if (reply.equals("list"))                   //Checks if user asks for stored strings
            {
                if (storage.size() == 0)
                {
                    System.out.println("No tasks in there lah");
                    reply = myObj.nextLine();           //Reads the next line for next input
                }
                else
                {
                    printStore(storage, storeCount);
                    reply = myObj.nextLine();           //Reads the next line for next input
                }
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
                if (num == 0)
                {
                    System.out.println("There is no task in there!");
                    reply = myObj.nextLine();           //Reads the next line for next input
                }
                else
                {
                    num = num-1;                        //Aligns numbering with stored array[]

                    try
                    {
                        storage.get(num).markDone();    //Update duke.task.Task as done
                        System.out.println("Nice! I've marked this task as done:\n" + storage.get(num).printTask());
                        num = 0;
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Is there such a task?");
                    }

                    reply = myObj.nextLine();           //Reads the next line for next input
                }
            }
            else if (reply.startsWith("delete"))        //User trying to delete item
            {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(reply);

                while (m.find())
                {
                    String temp = m.group();
                    num = Integer.parseInt(temp);
                }
                if (num == 0)
                {
                    System.out.println("No Tasks to delete T.T");
                    reply = myObj.nextLine();           //Reads the next line for next input
                }
                else
                {
                    try
                    {
                        num = num - 1;
                        String deleted = storage.get(num).printTask();
                        storage.remove(num);
                        storeCount--;
                        num = 0;
                        System.out.println("Noted. I've removed this task: \n" + deleted + "\nNow you have " + storeCount + " tasks in the list");
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("There might be no such task?");
                    }

                    reply = myObj.nextLine();               //Reads the next line for next input

                }

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
                    System.out.println("Please input type of task..");
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Please input the correct format of task : [duke.task.Task Type] [duke.task.Task] / [date]");
                }
                catch (DukeException e)
                {
                    DukeException exceptional = new DukeException(reply);
                    System.out.println("☹ OOPS!!! The description of a " + exceptional.printtask() + " cannot be empty.");
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Please input valid task number to delete!");
                }
                reply = myObj.nextLine();               //Reads the next line for next input
            }
        }
        System.out.println("Bye. Hope to see you again soon!");


    }
}
