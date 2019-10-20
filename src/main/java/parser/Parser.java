package parser;

import thrownexceptions.DukeException;

import java.util.Vector;

public class Parser {

    //To get Description from input String;
    public static String Description(String Input){
        String D;
        String[] body = Input.split(" ");

        int D_index = Input.indexOf(" ") + 1;

        if(body[0].equals("todo")){
            D = Input.substring(D_index);
        }
        else{

            int slash_index = Input.length();

            if(body[0].equals("deadline")){
                slash_index = Input.lastIndexOf("by");
            }
            else {
                slash_index = Input.lastIndexOf("at");
            }

            D = Input.substring(D_index, slash_index - 1).trim();
        }

        return D.trim();
    }

    //To get deadline time information from input String;
    public static String deadline_time(String Input){
        //Take time out from Input
        String ST;
        int ST_index = Input.lastIndexOf("by") + 3;
        ST = Input.substring(ST_index);
        String[] input = ST.split(" ");
        if(input.length == 1) {
            ST = ST+" 59:59:99";
            input = ST.split(" ");
        }

        return ST;
    }

    //To get event time information from input String;
    public static String Event_time(String Input){
        String ET;

        int ET_index = Input.lastIndexOf("at");

        if(ET_index == -1){
            ET_index = Input.lastIndexOf("on") + 3;
        }
        else {
            ET_index = ET_index + 3;
        }

        ET = Input.substring(ET_index);
        String[] ET_Array = ET.split(" ");

        if(ET_Array.length == 1){
            ET = ET + "59:59:99";
        }

        return ET;
    }

    //To get date;
    public static String date(String datetime){
        return datetime.substring(0, 10);
    }

    //To get time;
    public static String time(String datetime){
        String Time = datetime.substring(11);

        if(Time.length() == 5){
            Time = Time +":99";
        }

        return Time;
    }

    //To form datetime;
    public static String datetime(String date, String time){
        return date+"T"+time;
    }

    //To get the input task type;
    public static String Input_Type(String Input){
        String[] Input_Words = Input.split(" ");    //To split input by " " into String Array;
        String First_Word = Input_Words[0];     //To get the first word of input;

        return First_Word;
    }

    //To check input length;
    public static void Input_Length_Checking(String First_Word, String[] Input_Words) throws DukeException {
        if(!First_Word.equals("list") && !First_Word.equals("bye") && Input_Words.length == 1){
            throw new DukeException();
        }
    }
}
