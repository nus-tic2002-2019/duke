import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> line = new ArrayList<Task>();
        String input = "";
        int count = 0;
        while (!input.equals("bye")) //if string doesn't equal to bye
        {
            try
            {
                input = sc.nextLine();
                String[] arrOfString = input.split(" ");
                if (input.equals("list"))
                {
                    System.out.println("_______________________________________________");
                    for (int i = 0; i < count; i++)
                    {

                        System.out.println(i+1 + ". " + line.get(i));

                    }
                    System.out.println("_______________________________________________");
                }
                else if (arrOfString[0].equals("done"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a done cannot be empty");
                    }
                    int index = Integer.parseInt(arrOfString[1]);
                    line.get(index-1).markAsDone();
                    System.out.println("_______________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("\t["+ line.get(index-1));
                    System.out.println("_______________________________________________");
                }

                else if (arrOfString[0].equals("todo"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a todo cannot be empty");
                    }
                    String replaceString = input.replace("todo ", "");
                    Todo t = new Todo(replaceString, false);
                    line.add(t);
                    System.out.println("_______________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + line.get(count));
                    count ++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println("_______________________________________________");


                }
                else if (arrOfString[0].equals("deadline"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a deadline cannot be empty");
                    }
                    String replaceString = input.replace("deadline ","");
                    String [] splitBy = replaceString.split(" /by ");
                    if(splitBy.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                    }
                    Deadline d = new Deadline(splitBy[0], false, splitBy[1]);
                    line.add(d);
                    System.out.println("_______________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + line.get(count));
                    count ++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println("_______________________________________________");
                }

                else if (arrOfString[0].equals("event"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a event cannot be empty");
                    }
                    String replaceString = input.replace("event", "");
                    String [] splitAt = replaceString.split(" /at ");
                    if(splitAt.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                    }
                    Event e = new Event(splitAt[0], false, splitAt[1]);
                    line.add(e);
                    System.out.println("_______________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + line.get(count));
                    count ++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println("_______________________________________________");
                }

                else if(!arrOfString[0].equals("bye"))
                {
                    throw new InvalidCommandException();
                }
            }
            catch (InvalidCommandException e)
            {
                System.out.println("Oops sorry, I don't know what that means :( ");
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }

        }


        System.out.println("Bye. Hope to see you again soon !");
    }
}
