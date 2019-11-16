public class Parser {
    public Parser() {}

    public String getChoice(String message) {
        return message.split(" ", 0)[0].toLowerCase();
    }

    public int getItemNumber(String message) throws DukeException {
        String[] splitMessage = message.split(" ", 0);
        if (splitMessage.length > 1) {
            return Integer.parseInt(splitMessage[1]) - 1;
        }
        throw new DukeException("Missing selection number.");
    }

    public String getBody(String message) throws DukeException {
        String[] splitMessage = message.split(" ", 2);
        if (splitMessage.length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return splitMessage[1];
    }
}
