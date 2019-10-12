public class Parser{

    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];

        switch (command) {
            case "bye":
            case "": // exit if user input is empty
                return new ByeCommand();
            case "todo":
                return new TodoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(fullCommand);
            case "save":
                return new SaveCommand();
            case "saveTo":
                return new SaveToCommand(fullCommand);
            case "loadFrom":
                return new LoadFromCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            default:
                throw new DukeException("☹ OOPS!!! Unknown command! please try again" + System.lineSeparator());
        }
    }
}
