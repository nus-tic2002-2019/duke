package subclass;

public class Parser {

    public static Command parse(String full_input) throws DukeException {
        String input = full_input;

        switch (input.split(" ")[0]) {
            case TodoCommand.INPUT:
                return new TodoCommand(false, input);
            case DeadlineCommand.INPUT:
                return new DeadlineCommand(false, input);
            case EventCommand.INPUT:
                return new EventCommand(false, input);
            case ListCommand.INPUT:
                return new ListCommand(false, "");
            case DoneCommand.INPUT:
                return new DoneCommand(false, input);
            case DeleteCommand.INPUT:
                return new DeleteCommand(false, input);
            case FindCommand.INPUT:
                return new FindCommand(false, input);
            case HelpCommand.INPUT:
                return new HelpCommand(false, "");
            case ByeCommand.INPUT:
                return new ByeCommand(true, "");
            default:
                throw new DukeException();
        }
    }

    public static String parseTask(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }

    public static String parseDeadline_by(String line) {
        return line.substring(line.indexOf("/by") + 4);
    }

    public static String parseEvent_at(String line) {
        return line.substring(line.indexOf("/at") + 4);
    }

    public static String parseTask_description(String input_items) {
        return input_items.substring(0, input_items.indexOf("/")-1);
    }

}
