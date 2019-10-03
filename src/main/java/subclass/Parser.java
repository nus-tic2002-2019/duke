package subclass;

public class Parser {

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
