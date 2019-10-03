package subclass;

public class Parser {

    public static String parseTask(String line) {
        String input = line.substring(line.indexOf(" ") + 1);
        return input;
    }

    public static String parseDeadline_by(String line) {
        String by = line.substring(line.indexOf("/by") + 4);
        return by;
    }

    public static String parseEvent_at(String line) {
        String at = line.substring(line.indexOf("/at") + 4);
        return at;
    }

    public static String parseTask_description(String input_items) {
        String sub_description = input_items.substring(0, input_items.indexOf("/")-1);
        return sub_description;
    }

}
