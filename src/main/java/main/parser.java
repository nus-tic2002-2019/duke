package main;


import main.Commands.*;
import main.TaskLists.Deadline;
import main.TaskLists.Event;
import main.TaskLists.Task;
import main.TaskLists.ToDo;
import org.jetbrains.annotations.NotNull;


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


    public static void parse(@NotNull String input) throws DukeException{

        // Takes the first word of the input
        String[] command = input.split(" ");

       try {
            //Parse the comment to the correct Command Action
            switch (command[0].toUpperCase()) {

                case "BYE":
                    new ByeCommand(input);
                    break;

                case "LIST":
                    new ListCommand(input);
                    break;

                case "DONE":
                    try{
                        new DoneCommand(input.split(" ")[1]);
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("\t☹ OOPS!!! I can't process this action without specifying the task!");
                    } catch (NumberFormatException e) {
                        System.out.println("\t☹ OOPS!!! I can only accept numerical values. Type `list` to see the values");
                    }
                    break;

                case "DEADLINE":
                    String deadlineDesc = (input.split(" ", 2)[1]).split("/by")[0];
                    var subStringDeadline = input.substring(input.lastIndexOf("/by") + 3);
                    String doWhen = (subStringDeadline.equalsIgnoreCase(input.substring(2))) ?
                            " No Deadline Given" : subStringDeadline;
                    Task deadline = new Deadline(deadlineDesc, doWhen);
                    new AddCommand(deadline);
                    break;

                case "EVENT":
                    String eventDesc = (input.split(" ", 2)[1]).split("/at")[0];
                    var subStringEvent = input.substring(input.lastIndexOf("/at") + 3);
                    String doAt = (subStringEvent.equalsIgnoreCase(input.substring(2))) ?
                            " No Location Given" : subStringEvent;
                    Task event = new Event(eventDesc, doAt);
                    new AddCommand(event);
                    break;

                case "TODO":
                    String TodoDesc = (input.split(" ", 2)[1]);
                    Task todo = new ToDo(TodoDesc);
                    new AddCommand(todo);
                    break;

                default:
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch(Exception e) {
           //TODO Continue to Add in more Exception Scenarios
            throw new DukeException("I do not have this scenario catered.. shutting down.");
        }

    }

}
