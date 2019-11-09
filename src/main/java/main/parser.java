package main;


import main.Commands.*;
import main.TaskLists.Deadline;
import main.TaskLists.Event;
import main.TaskLists.Task;
import main.TaskLists.ToDo;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse the input of the user and returns a command based on the input.
 * @return Command  The command with reference to the given input.
 */
public class Parser {

    public static boolean isTrue;

    Parser() {
        this.isTrue = true;
    }

    //TODO Add exception here
    public static void parse(String input) {

        // Takes the first word of the input
        String[] command = input.split(" ");

        //Parse the comment to the correct Command Action
        switch (command[0].toUpperCase()){

           case "BYE":
               new ByeCommand(input);
                break;

            case "LIST":
                new ListCommand(input);
                break;

            case "DONE":
                new DoneCommand(input.split(" ")[1]);
                break;

            case "DEADLINE":
                String deadlineDesc = (input.split(" ",2)[1]).split("/by")[0];
                var subStringDeadline  = input.substring(input.lastIndexOf("/by") + 3);
                String doWhen = (subStringDeadline.equalsIgnoreCase(input.substring(2))) ?
                        " No Deadline Given" : subStringDeadline;
                System.out.println(input.substring(2));
                Task deadline = new Deadline(deadlineDesc,doWhen);
                new AddCommand(deadline);
                break;

            case "EVENT":
                String eventDesc = (input.split(" ",2)[1]).split("/at")[0];
                var subStringEvent  = input.substring(input.lastIndexOf("/at") + 3);
                String doAt = (subStringEvent.equalsIgnoreCase(input.substring(2))) ?
                        " No Location Given" : subStringEvent;
                System.out.println(input.substring(2));
                Task event = new Event(eventDesc,doAt);
                new AddCommand(event);
                break;

            case "TODO":
                String TodoDesc = (input.split(" ",2)[1]);
                Task todo = new ToDo(TodoDesc);
                new AddCommand(todo);
                break;

            default:
                System.out.println("I do not know what you want me to do");


        }


    }



}
