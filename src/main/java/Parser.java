public class Parser {

    public static Command parse(String fullCommand) throws DukeException{
        String keyCommand = fullCommand.toLowerCase();
        if(fullCommand.contains(" ")){
            int dividerPosition = fullCommand.indexOf(" ");
            keyCommand = fullCommand.substring(0, dividerPosition).toLowerCase();
        }

        switch(keyCommand){
            case "bye":
                return new ByeCommand();
            case "list":
            case "help":
            case "find":
            case "todo":
            case "event":
            case "deadline":
            case "save":
            case "delete":
            default:
                throw new DukeException("Unknown command!");
        }



    }
}
