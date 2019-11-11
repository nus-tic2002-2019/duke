package main;


import main.Commands.*;
import main.TaskLists.Deadline;
import main.TaskLists.Event;
import main.TaskLists.Task;
import main.TaskLists.ToDo;

import java.io.IOException;

/**
 * Parse the input of the user and returns a command based on the input.
 * @return Command  The command with reference to the given input.
 */
public class Parser {

    public static boolean isTrue;
    private static final String OUTPUT_DELIMITER = "\\|";
    private static final String INPUT_DELIMITER =" ";



    Parser() {
        this.isTrue = true;
    }


    public static void parse(String input) throws DukeException, IOException {

        // Takes the first word of the input
        String[] command = input.split(INPUT_DELIMITER);

            //Parse the comment to the correct Command Action
            switch (command[0].toUpperCase()) {

                case "BYE":
                    new ByeCommand(input);
                    break;

                case "LIST":
                    new ListCommand(input);
                    break;

                case "DELETE":
                    new DeleteCommand(Integer.parseInt(input.split(INPUT_DELIMITER)[1]) );
                    break;

                case "DONE":
                    try{
                        new DoneCommand(input.split(INPUT_DELIMITER)[1]);
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("\t☹ OOPS!!! I can't process this action without specifying the task!");
                    } catch (NumberFormatException e) {
                        System.out.println("\t☹ OOPS!!! I can only accept numerical values. Type `list` to see the values");
                    }
                    break;

                case "DEADLINE":
                    String deadlineDesc = (input.split(INPUT_DELIMITER, 2)[1]).split("/by")[0];
                    var subStringDeadline = input.substring(input.lastIndexOf("/by") + 3);
                    String doWhen = (subStringDeadline.equalsIgnoreCase(input.substring(2))) ?
                            " No Deadline Given" : subStringDeadline;
                    Task deadline = new Deadline(deadlineDesc, doWhen);
                    new AddCommand(deadline);
                    break;

                case "EVENT":
                    String eventDesc = (input.split(INPUT_DELIMITER, 2)[1]).split("/at")[0];
                    var subStringEvent = input.substring(input.lastIndexOf("/at") + 3);
                    String doAt = (subStringEvent.equalsIgnoreCase(input.substring(2))) ?
                            " No Location Given" : subStringEvent;
                    Task event = new Event(eventDesc, doAt);
                    new AddCommand(event);
                    break;

                case "TODO":
                    String TodoDesc = (input.split(INPUT_DELIMITER, 2)[1]);
                    Task todo = new ToDo(TodoDesc);
                    new AddCommand(todo);
                    break;

                default:
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
    }

    /**
     *  This method Parses the Task Object into a String format for writing to text files
     * @param input
     * @return
     */
    public static String outputParse(Task input) {

        String parsedInput = null;

        if (input instanceof ToDo) {
            parsedInput = String.format("T|%s|%s",
                    input.isDone() ? "1" : "0",
                    input.getDescription()
                    );
        } else if (input instanceof Event) {
            parsedInput = String.format("E|%s|%s|%s",
                    input.isDone() ? "1" : "0",
                    input.getDescription(),
                    ((Event) input).getAt()
                     );
        } else if (input instanceof Deadline) {
            parsedInput = String.format("D|%s|%s|%s",
                    input.isDone() ? "1" : "0",
                    input.getDescription(),
                    ((Deadline) input).getBy()
                    );
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know how to Parse this");
        }

        return parsedInput;
    }


    /**
     *  This method Parses the input String into a Task Object
     * @param input
     * @return
     */
    public static Task inputParse(String input) {

        String task = input.split(OUTPUT_DELIMITER)[0];
        Boolean status =  Boolean.parseBoolean(input.split(OUTPUT_DELIMITER)[1]);
        String desc = input.split(OUTPUT_DELIMITER)[2];

        System.out.println(task);

        Task output = null;

        if (task.matches("T")){
            output = new ToDo(desc);
            output.setStatus(status);
        } else if (task.matches("D")){
            output = new Deadline(desc, input.split(OUTPUT_DELIMITER)[3]);
            output.setStatus(status);
        }else if (task.matches("E")){
            output = new Event(desc, input.split(OUTPUT_DELIMITER)[3]);
            output.setStatus(status);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know how to Parse this");
        }

        return output;
    }


}
