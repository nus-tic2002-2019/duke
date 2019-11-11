package basic;

import command.*;

/**
 * Parses user input.
 */
public class Parser {
    protected static Ui ui = new Ui();

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    static Command parse(String userInput) {
        if (userInput.toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else if (userInput.toLowerCase().equals("list")) {
            return new ListCommand();
        } else if (userInput.toLowerCase().contains("done")) {
            return new MarkAsDoneCommand(userInput);
        } else if (userInput.toLowerCase().contains("after")) {
            return new AfterCommand(userInput);
        } else if (userInput.toLowerCase().contains("find")) {
            return new FindCommand(userInput);
        } else if (userInput.toLowerCase().substring(0,1).equals("t") ||
                userInput.toLowerCase().contains("todo") && !userInput.toLowerCase().contains("event") && !userInput.toLowerCase().contains("deadline")) {
            return new AddTodoCommand(userInput);
        } else if (userInput.toLowerCase().substring(0,1).equals("d") ||
                userInput.toLowerCase().contains("deadline") && !userInput.toLowerCase().contains("event") && !userInput.toLowerCase().contains("todo")) {
            return new AddDeadlineCommand(userInput);
        } else if (userInput.toLowerCase().substring(0,1).equals("e") ||
                userInput.toLowerCase().contains("event") && !userInput.toLowerCase().contains("deadline") && !userInput.toLowerCase().contains("todo")) {
            return new AddEventCommand(userInput);
        } else if (userInput.toLowerCase().contains("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.toLowerCase().contains("date")) {
            return new DateCommand(userInput);
        } else {
            return new Command();
        }
    }

}
