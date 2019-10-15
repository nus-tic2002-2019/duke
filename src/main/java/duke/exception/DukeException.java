package duke.exception;

public class DukeException extends Exception {
    protected String output;

    /**
     * Taking in the user input thrown into the exception to generate the class of Task creating the error.
     * This method is set within the call function to catch exceptions,
     * The method uses switch cases to find the type of Task error.
     * @param input error input that falls into the exception case
     * @return The Task class where error falls into
     */
    public DukeException(String input)
    {
        this.output = input;
    }

    public String printtask()
    {
        switch(this.output)
        {
            case "todo":
                this.output = "todo";
                break;
            case "deadline":
                this.output = "deadline";
                break;
            case "event":
                this.output = "event";
                break;
            case "delete":
                this.output = "delete";
                break;
        }

        return this.output;
    }
}
