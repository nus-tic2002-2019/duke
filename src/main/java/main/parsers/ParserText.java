package main.parsers;


import main.DukeException;
import main.Storage;
import main.commands.*;
import main.taskLists.Deadline;
import main.taskLists.Event;
import main.taskLists.Task;
import main.taskLists.ToDo;

import java.io.IOException;
import java.time.LocalDate;
import static main.UI.dukePrint;


public class ParserText<T> {

    private static final String OUTPUT_DELIMITER = "\\|";
    private static final String INPUT_DELIMITER = " ";
    public static boolean isTrue;


    public ParserText() {
        this.isTrue = true;
    }

    public static void parsetext(String input) throws DukeException, IOException {

        String[] command = input.split(INPUT_DELIMITER);


        UtilityFunc parsedDate = (Object n) -> {
            try {
                return LocalDate.parse((CharSequence) n);
            } catch (Exception e) {

                return (String) n;
            }
        };


        //Parse the comment to the correct Command Action
        switch (command[0].toUpperCase()) {

            case "BYE":
                new ByeCommand(input);
                break;

            case "LIST":
                new ListCommand(input);
                break;

            case "CLEAR":
                new ClearCommand();
                break;

            case "SAVE":
                new ArchiveCommand();
                break;

            case "LOADVIEW":
                Storage.loadview();
                break;

            case "LOAD":
                try {
                    Storage.loadFile(Integer.parseInt(input.split(INPUT_DELIMITER)[1]));
                } catch (IndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can't process this action without specifying the task!");
                } catch (NumberFormatException e) {
                    dukePrint("\t☹ OOPS!!! I can only accept numerical values. Type `list` to see the values");
                }
                break;

            case "FIND":
                try {
                    new FindCommand(input.split(INPUT_DELIMITER, 2)[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can't search the UnSearchable!");
                }

                break;

            case "DELETE":
                try {
                    new DeleteCommand(Integer.parseInt(input.split(INPUT_DELIMITER)[1]));
                } catch (IndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can't process this action without specifying the task!");
                } catch (NumberFormatException e) {
                    dukePrint("\t☹ OOPS!!! I do not have the ability to parse words to numbers yet. " +
                            "Please use a numerical value!");
                }
                break;

            case "DONE":
                try {
                    new DoneCommand(input.split(INPUT_DELIMITER)[1]);
                } catch (IndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can't process this action without specifying the task!");
                } catch (NumberFormatException e) {
                    dukePrint("\t☹ OOPS!!! I can only accept numerical values. Type `list` to see the values");
                }
                break;

            case "DEADLINE":
                try {
                    String deadlineDesc = (input.split(INPUT_DELIMITER, 2)[1]).split("/by")[0];
                    var subStringDeadline = input.substring(input.lastIndexOf("/by") + 3).trim();
                    Object doWhen = (subStringDeadline.equalsIgnoreCase(input.substring(2))) ?
                            " No Deadline Given" :  parsedDate.convert(subStringDeadline); //Parse Date here
                    Task deadline = new Deadline<>(deadlineDesc, doWhen);
                    new AddCommand(deadline);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\t☹ OOPS!!! I can't process this action without specifying the task!");
                }
                break;

            case "EVENT":
                try {
                    String eventDesc = (input.split(INPUT_DELIMITER, 2)[1]).split("/at")[0];
                    var subStringEvent = input.substring(input.lastIndexOf("/at") + 3).trim();
                    Object doAt = (subStringEvent.equalsIgnoreCase(input.substring(2))) ?
                            " No Location Given" :  parsedDate.convert(subStringEvent); // Parse Date here
                    Task event = new Event<>(eventDesc, doAt);
                    new AddCommand(event);
                } catch (IndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can only accept numerical values. Type `list` to see the values");
                }
                break;

            case "TODO":
                try {
                    String TodoDesc = (input.split(INPUT_DELIMITER, 2)[1]);
                    Task todo = new ToDo(TodoDesc);
                    new AddCommand(todo);
                } catch (IndexOutOfBoundsException e) {
                    dukePrint("\t☹ OOPS!!! I can't process this action without specifying the task!");
                }
                break;

            default:
                dukePrint("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
    }

    /**
     * This method Parses the Task Object into a String format for writing to text files
     *
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
            dukePrint("☹ OOPS!!! I'm sorry, but I don't know how to Parse this");
        }

        return parsedInput;
    }


    /**
     * This method Parses the input String into a Task Object
     *
     * @param input
     * @return
     */
    public static Task inputParse(String input) {

        // Bad attempt at a Lamda Function
        UtilityFunc parsedDate = (Object n) -> {
            try {
                return LocalDate.parse((CharSequence) n);
            } catch (Exception e) {

                return (String) n;
            }
        };

        String task = input.split(OUTPUT_DELIMITER)[0];
        Boolean status = Boolean.parseBoolean(input.split(OUTPUT_DELIMITER)[1]);
        String desc = input.split(OUTPUT_DELIMITER)[2];

        Task output = null;

        if (task.matches("T")) {
            output = new ToDo(desc);
            output.setStatus(status);
        } else if (task.matches("D")) {
            output = new Deadline<>(desc, parsedDate.convert(input.split(OUTPUT_DELIMITER)[3]));
            output.setStatus(status);
        } else if (task.matches("E")) {
            output = new Event<>(desc, parsedDate.convert(input.split(OUTPUT_DELIMITER)[3]));
            output.setStatus(status);
        } else {
            dukePrint("☹ OOPS!!! I'm sorry, but I don't know how to Parse this");
        }

        return output;
    }


}
