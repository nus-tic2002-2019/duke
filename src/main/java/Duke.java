import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> type = new ArrayList<>();
    public static ArrayList<Boolean> mark = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        try {
            while (printout(echo())) ;
        }

        catch (Exception e) {
        }
    }

      public static String echo() throws DukeException {
          String userInput;
          Scanner scan = new Scanner(System.in);
          userInput = scan.nextLine();

          String[] splitStr;

          if (userInput.contains("done")) {
              splitStr = userInput.split("\\s+");
              mark.set(Integer.valueOf(splitStr[1]) - 1, true);
          } else if (userInput.contains("delete")) {

          } else if ((!userInput.equals("list")) && (!userInput.equals("bye"))) {
              String typeS = getType(userInput);

              userInput = userInput.replace("todo", "");
              userInput = userInput.replace("deadline", "");
              userInput = userInput.replace("event", "");

              if (userInput.isEmpty()) {
                  System.out.println("☹ OOPS!!! The description cannot be empty.");
                  throw new DukeException();
              } else {
                  if (typeS == "E" || typeS == "D") {
                      splitStr = userInput.split("/");
                      userInput = splitStr[0] + "(" + splitStr[1] + ")";
                  }

                  list.add(userInput);
                  mark.add(false);
                  type.add(typeS);
              }
          }
          return userInput;
      }

    public static boolean printout(String userInput){
        if(userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        }
        else if(userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < list.size(); i++)
                {
                    System.out.println(i+1 + ". [" + type.get(type.size()-1) + "][" + getMark(mark.get(i))+ "] " + list.get(i));
                }
        }
        else if(userInput.contains("done")) {
           // int index = Integer.valueOf(userInput.substring(userInput.length() + 1)) - 1;
            String[] splitStr = userInput.split("\\s+");
            int index = Integer.valueOf(splitStr[1])-1;
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[" + getMark(mark.get(index))+ "] " + list.get(index));

        }
        else if(userInput.contains("delete")) {
            String[] splitStr = userInput.split("\\s+");
            int index = Integer.parseInt(splitStr[1]) - 1;

            System.out.println("Noted. I've removed this task: ");
            System.out.println("[" + type.get(index) + "][" + getMark(mark.get(index))+ "] " + list.get(index));

            list.remove(index);
            type.remove(index);
            mark.remove(index);

            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
        else {
            System.out.println("Got it. I've added this task: " + userInput);
            System.out.println("[" + type.get(type.size()-1) + "][" + getMark(mark.get(list.size()-1))+ "] " + list.get(list.size()-1));
            System.out.println("Now you have " + type.size() + " tasks in the list.");
        }
        return true;
    }

    public static String getMark(boolean result) {

        if(result == true)
            return "✓";
        else
             return "X";
    }

    public static String getType(String type) throws DukeException {

        if (type.contains("todo"))
            return "T";
        else if (type.contains("deadline"))
            return "D";
        else if (type.contains("event"))
            return "E";
        else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }
    }
}
