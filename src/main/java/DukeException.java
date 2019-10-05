public class DukeException extends Exception {
    protected String output;

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
        }

        return this.output;
    }
}
