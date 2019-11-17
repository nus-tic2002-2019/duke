public class Parser {

    /**
     * To return the type of command from Users for easy reference in main program
     */
    public String user_input(String input) {
        String input_Action = "" ;

        if(input.toLowerCase().contains("bye")) {
            input_Action = "bye";
        } else if(input.toLowerCase().contains("list")){
            input_Action = "list";
        } else if(input.toLowerCase().contains("done")) {
            input_Action = "done";
        } else if(input.toLowerCase().contains("todo")) {
            input_Action = "todo";
        } else if(input.toLowerCase().contains("deadline")) {
            input_Action = "deadline";
        } else if(input.toLowerCase().contains("event")) {
            input_Action = "event";
        } else if(input.toLowerCase().contains("delete")) {
            input_Action = "delete";
        } else if(input.toLowerCase().contains("find")) {
            input_Action = "find" ;
        } else if(input.toLowerCase().contains("edit")) {
            input_Action = "edit" ;
        } else {
            input_Action = "unknown";
        }

        return input_Action;
    }
}
