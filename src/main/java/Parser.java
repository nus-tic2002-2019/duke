import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String date(String fullCommand) {
        try {
            String d = fullCommand.split("/")[1];
            return d.split(" ")[1];
        } catch (Exception e) {
            System.out.println("Please key in date with format dd-MM-yyyy");
        }
        return "0";
    }

    public static Date converted_date(String date) {
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
