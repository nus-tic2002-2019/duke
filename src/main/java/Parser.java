
public class Parser {
    private Parser(){}
    public static String parse(String fullCommand){
        String command = fullCommand.split(" ")[0];
        return command;
    }
    public static int getIndex(String fullCommand){

        return Integer.parseInt(fullCommand.split(" ")[1]);
    }

    public static ToDo createTodo(String fullCommand) throws DukeException {
        if(fullCommand.substring(5).isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for TODO");
        }
        return new ToDo(fullCommand.substring(5));
    }

    public static Deadline createDeadline(String fullCommand) throws DukeException {

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for DEADLINE");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for DEADLINE");
        }

        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(8, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        return new Deadline(itemName,itemName2);
    }

    public static Event createEvent(String fullCommand) throws DukeException {

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for EVENT");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for EVENT");
        }
        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(5, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        return new Event(itemName,itemName2);
    }

    public static String getPath(String fullCommand) throws DukeException {
        System.out.println("New file path has been set into: "+fullCommand.split("\"")[1]);
        return fullCommand.split("\"")[1];
    }
}
