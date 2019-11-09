package parse;

public class Parser {
    public Parser () {

    }
    public static String convertFileName (String name) {
        name = name.toLowerCase();
        name = name.trim();
        String[] parts = name.split(" ");
        String filename = " ";
        for (String part : parts) {
            filename = filename + "-" + part;
        }
        return filename.substring(2);
    }
    public static String[] fileLineBreak (String line){
        String[] parts = line.split("\\|", 4);

        return parts;
    }

}
