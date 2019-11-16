import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Stores method to understand user command.
 * */
public class Parser {
    /**
     * Finding out the command keyed by user.
     * @param fullCommand user full command.
     * @return user command.
     * */
    public static String command(String fullCommand){
        return fullCommand.split(" ")[0].toLowerCase();
    }
    /**
     * Finding out the index keyed user.
     * @param fullCommand user full command.
     * @return index.
     * */
    public static int taskNumber(String fullCommand) {
        int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        assert index >= 0: "The index of task must be greater than 0";
        return index;
    }
    /**
     * Finding out the description keyed by user.
     * @param fullCommand user full command.
     * @return description
     * */
    public static String description(String fullCommand) {
        String[] s1 = fullCommand.split(" ");
        String[] s2 = fullCommand.split("/");
        return s2[0].replace(s1[0] + " ", "");
    }
    /**
     * Finding out the date keyed by user.
     * @param fullCommand user full command.
     * @return date in string.
     * */
    public static String date(String fullCommand) {
        try {
            String d = fullCommand.split("/")[1];
            return d.split(" ")[1];
        } catch (Exception e) {
            System.out.println("Please key in date with format dd-MM-yyyy");
        }
        return "0";
    }
    /**
     * Converts date from string to Date.
     * @param date date in string.
     * @return date in Date.
     * */
    public static Date convertDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat ("dd-MM-yyyy");
        Date covertDate;
        try {
            covertDate = format.parse(date);
            return covertDate;
        } catch (ParseException e) {
            System.out.println("Please use format dd-MM-yyyy for date");
        }
        return new Date();
    }
}
