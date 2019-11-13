package DukeExceptions;
public class Exception extends RuntimeException {

    public enum Code {
		UNKNOWN_COMMAND,
		EMPTY_TODO_DESCRIPTION,
        EMPTY_DONE_DESCRIPTION,
        INVALID_TASK_NUMBER,
        EMPTY_DEADLINE_DESCRIPTION,
        MISSING_DEADLINE_DATETIME,
        EMPTY_EVENT_DESCRIPTION,
        MISSING_EVENT_VENUE,
    }

    private Code code;

    public Exception(Code code) {
        this.code = code;
    }

    public String errorDescription() {

        switch (code) {
			case UNKNOWN_COMMAND:
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
				
			case EMPTY_TODO_DESCRIPTION:
                return "☹ OOPS!!! The description of a todo cannot be empty.";
				
            case EMPTY_DONE_DESCRIPTION:
                return "☹ OOPS!!! You must provide a task number for 'done' command!";
				
            case INVALID_TASK_NUMBER:
                return "☹ OOPS!!! You entered an invalid task number!";
				
            case EMPTY_DEADLINE_DESCRIPTION:
                return "☹ OOPS!!! The description of a deadline cannot be empty.";

            case MISSING_DEADLINE_DATETIME:
                return "☹ OOPS!!! The date and or time of a deadline must contain ' /by '.";

            case EMPTY_EVENT_DESCRIPTION:
                return "☹ OOPS!!! The description of an event cannot be empty.";

            case MISSING_EVENT_VENUE:
                return "☹ OOPS!!! The venue of an event must contain ' /at '.";

            default:
                break;
        }
        return null;
    }
}
