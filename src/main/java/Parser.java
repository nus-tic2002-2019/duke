public class Parser {

    public static String command(String fullCommand){
        return fullCommand.split(" ")[0].toLowerCase();
    }

    public static int taskNumber(String fullCommand){
        return Integer.parseInt(fullCommand.split(" ")[1]) - 1;
    }

    public static String description(String fullCommand){
        String[] s1 = fullCommand.split(" ");
        String[] s2 = fullCommand.split("/");
        return s2[0].replace(s1[0] + " ", "");
    }

    public static String deadlineTime(String fullCommand){
        return fullCommand.split("/")[1].replace("by ", "");
    }

    public static String eventTime(String fullCommand){
        return fullCommand.split("/")[1].replace("at ", "");
    }
}
