import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    //protected String description;
    protected static boolean isDone;

    //private static Scanner read;
    static ArrayList<String> checkBox = new ArrayList<String>();

    public Task() {
        //this.description = description;
        this.isDone = false;
        checkBox = new ArrayList<String>();
    }

    public static String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static void markAsDone(){
        isDone = true;
    }

    public static void checkMark() {
        checkBox.add(getStatusIcon());
    }

    public static void changeMark(int m) {
        checkBox.set( m, getStatusIcon() );
    }
}