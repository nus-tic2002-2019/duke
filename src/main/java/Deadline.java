import java.util.ArrayList;
import java.util.Arrays;

public class Deadline extends Todo {

    static String Type = "D";
    static ArrayList<String> forTask = new ArrayList<>();
    static String forTaskString = "";


    static ArrayList<String> timeLimit = new ArrayList<>();
    static String timeLimitString = "";

    public Deadline(){
        String Type = "D";
    }

    public static void splitDead(String s){
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(s.split(" ")));


        System.out.println(split); //check

        //populate forTask///////////////////
        int i = 1;
        do {
            forTask.add(split.get(i) + " ");
            i++;
        }
        while (!split.get(i).equals("/by"));
        //System.out.println(forTask); //check

        //Convert to string to append to timeLimit)
        StringBuilder forTaskTemp = new StringBuilder();
        for (String string : forTask) {
            forTaskTemp.append(string);
        }
        forTaskString = forTaskTemp.toString();
        /////////////////////////////////////


        //populate timeLimit/////////////////
        for (int j = split.indexOf("/by")+1; j < split.size(); j++){
            timeLimit.add(split.get(j));
        }
        //System.out.println(timeLimit); //check

        //Convert to string to pass to List.enterList
        StringBuilder timeLimitTemp = new StringBuilder();
        timeLimitTemp.append(forTaskString);
        timeLimitTemp.append("(by: ");
        for (String time : timeLimit) {
            timeLimitTemp.append(time);
        }
        timeLimitTemp.append(")"); //end result is full sentence, eg read book (by: date), to pass to enterList;
        timeLimitString = timeLimitTemp.toString();
        /////////////////////////////////////
    }

    public static void toList(){
        List.enterList(timeLimitString);
        //List.deadTime.add(timeLimitString);
    }

    public static void fillTaskType() {
        List.taskType.add(Type);
    }
}
