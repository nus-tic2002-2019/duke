package Parser;
import Exceptions.*;

import java.security.spec.ECField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class myMethods {
    public String[] parseSpace(String input){
        String[] act = input.split(" ",2);
        return act;
    }

    public String parseEvents(String input){
        int indexOfSlash = input.indexOf("/");
        String tmp0 = input.substring(0, indexOfSlash);
        return tmp0;
    }

    public String parseSlash(String input){

        int indexOfSlash = input.indexOf("/");
        //String[] act = input.split("/",2);
        //String tmp0 = input.substring(7, indexOfSlash);
        String tmp1 = input.substring(indexOfSlash+1, indexOfSlash+3);
        String tmp2 = input.substring(indexOfSlash+3, input.length());
        return "(" + tmp1 + ": " + tmp2 + ")";
    }

    public static void printLines(String k){
        System.out.println(k);
    }

    public String[] parsed(String input){
        String[] act = input.split(" ",2);
        return act;
    }



    /**
     * Converts LDT Object to a string with d m y hhmm format
     * @param dateTime
     * @return
     */

    public Date dteToString(String input) throws  InvalidDateException{
        String date = input.substring(input.indexOf("/at")+ 4, input.length()); // full date time.
        String dte = input.substring(input.indexOf("/at")+ 4, input.length()-4); // date get date only

//        System.out.println("WHAT IS DTE LA : " + dte);
//        System.out.println("WHAT IS DATE LA : " + date);


        SimpleDateFormat formatterMMDD = new SimpleDateFormat("MMM-dd-yyyy HHmm");
        SimpleDateFormat formatterDDMM = new SimpleDateFormat("dd-MMM-yyyy HHmm");

        Date ddmmm = new Date();
        Date mmmdd = new Date();

        try {
            if(chkDateFormat_DDMMYYYY(dte) || chkDateFormat_MMDDYYYY(dte) ){

                if(chkDateFormat_DDMMYYYY(date)){
                    ddmmm = formatterDDMM.parse(date);

                    return ddmmm;
                }
                else if(chkDateFormat_MMDDYYYY(dte)){
                    System.out.println("COME HERE ANOT ");
                    SimpleDateFormat format1 = new SimpleDateFormat("MMM-dd-yyyy HH:mm");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                    System.out.println("DATE HERE  : " + date);
                    Date tmpDte = format1.parse(date);
                    System.out.println("WHAT IS DATE TMP DTE : " + tmpDte);
                    System.out.println("FORMATTED LEH : " + format2.parse(tmpDte.toString()));
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm");
                    return format2.parse(tmpDte.toString());
                }

            }
        }
        catch (DateTimeParseException | ParseException e){
            //throw new InvalidDateException();
        }

        return ddmmm;
    }

    /**
     * to check if date is MMDDYYYY
     * @param date
     * @return
     */
    public boolean chkDateFormat_MMDDYYYY(String date){
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("MMM-dd-yyyy");
        sdfrmt.setLenient(false);
        // System.out.println(" DATE HERE MMDD : " + date);

        Date javaDate = new Date();
        /* Create Date object
         * parse the string into date
         */
        try
        {
            javaDate = sdfrmt.parse(date);
            // System.out.println(date+" is valid date format mmm dd yyyy : ");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            // System.out.println(date + " is Invalid Date format mmm dd yyyy : ");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }


    /**
     * to check if date is DDMMYYYY
     * @param date
     * @return
     */
    public boolean chkDateFormat_DDMMYYYY(String date){
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MMM-yyyy");
        sdfrmt.setLenient(false);
        //System.out.println(" DATE HERE DDMM : " + date);
        Date javaDate = new Date();
        /* Create Date object
         * parse the string into date
         */
        try
        {
            //System.out.println(" DATE HERE DDMM : " + date);

            javaDate = sdfrmt.parse(date);
            //System.out.println(date+" is valid date format dd mmm yyyy : ");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            //System.out.println(date + " is Invalid Date format dd mmm yyyy :");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }
}
