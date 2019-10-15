package storage;

import taskclasses.*;

public class Encoder {
    public static String InputStringToFile (String Input, String[] Input_Words){
        String First_W = Input_Words[0];
        String Description=Task.Description(Input);
        String Timing;
        String Status=Task.;
        String Task_Type;

        switch(First_W){
            case "todo":
                Task_Type="T";
                return Task_Type + " | " + Status + " | " + Description;
                break;
            case "event":
                Task_Type="E";
                Timing=Task.Event_time(Input);
                break;
            case "deadline":
                Task_Type="D";
                Timing=Task.Deadline_time(Input);
                break;
        }


    }
}
