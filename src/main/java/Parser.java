public class Parser {

    /**
     * To return the type of command from Users for easy reference in main program
     */
    public String user_input(String input) {
        String input_action = "" ;

        if(input.toLowerCase().contains("bye"))    input_action = "bye";
        else if(input.toLowerCase().contains("list"))   input_action = "list";
        else if(input.toLowerCase().contains("done"))    input_action = "done";
        else if(input.toLowerCase().contains("todo"))    input_action = "todo";
        else if(input.toLowerCase().contains("deadline"))    input_action = "deadline";
        else if(input.toLowerCase().contains("event"))    input_action = "event";
        else if(input.toLowerCase().contains("delete"))    input_action = "delete";

        else {
            input_action = "unknown";
        }
        return input_action;
    }
}
