import java.util.ArrayList;
import java.util.Arrays;

public class Event extends Deadline {
    static String Type = "E";
    static ArrayList<String> forTask = new ArrayList<>();
    static String forTaskString = "";


    static ArrayList<String> timeLimit = new ArrayList<>();
    static String timeLimitString = "";

    public Event(){
        String Type = "E";
    }

    public static void splitEvent(String s){
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(s.split(" ")));


        System.out.println(split); //check

        //populate forTask///////////////////
        int i = 1;
        do {
            forTask.add(split.get(i) + " ");
            i++;
        }
        while (!split.get(i).equals("/at"));
        //System.out.println(forTask); //check

        //Convert to string to append to timeLimit)
        StringBuilder forTaskTemp = new StringBuilder();
        for (String string : forTask) {
            forTaskTemp.append(string);
        }
        forTaskString = forTaskTemp.toString();
        /////////////////////////////////////


        //populate timeLimit/////////////////
        for (int j = split.indexOf("/at")+1; j < split.size(); j++){
            timeLimit.add(split.get(j));
        }
        //System.out.println(timeLimit); //check

        //Convert to string to pass to List.enterList
        StringBuilder timeLimitTemp = new StringBuilder();
        timeLimitTemp.append(forTaskString);
        timeLimitTemp.append("(at: ");
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
