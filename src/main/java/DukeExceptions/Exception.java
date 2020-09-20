package newDuke.DukeExceptions;
public class Exception extends RuntimeException {

    public enum Code {
	UNKNOWN_COMMAND, // done 
	EMPTY_TODO_DESCRIPTION, //done
	EMPTY_DONE_DESCRIPTION, // done
        INVALID_TASK_NUMBER, // done for done, not yet for delete
        EMPTY_DEADLINE_DESCRIPTION, // done
        MISSING_DEADLINE_DATETIME, // done
        EMPTY_EVENT_DESCRIPTION, //done
        MISSING_EVENT_VENUE, //done
	EMPTY_DELETE_DESCRIPTION,
	FILE_CONTENTS_FORMAT_ERROR, // done
	WRONG_DEADLINE_FORMAT // done
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
				
	case EMPTY_DELETE_DESCRIPTION:
		return "☹ OOPS!!! You must provide a task number for 'delete' command!";
			
	case FILE_CONTENTS_FORMAT_ERROR:
		return "☹ OOPS!!! Please check the format of your file contents";
			
	case WRONG_DEADLINE_FORMAT:
		return "☹ OOPS!!! Please write the deadline format like this yyyy-mm-dd format (e.g., 2019-10-15)";
			
		default:
            break;
        }
        return "";
    }
}
