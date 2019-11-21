package Parser;
import Exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class myMethods {


    /**
     * Converts LDT Object to a string with d m y hhmm format
     * @param input
     * @return
     */

    public Date dteToString(String input) throws  InvalidDateException{

        String date = input.substring(input.indexOf("/by")+ 4, input.length()); // full date time.
        String dte = input.substring(input.indexOf("/by")+ 4, input.length()-4); // date get date only

      //  System.out.println("WHAT IS DTE LA : " + dte);
     //   System.out.println("WHAT IS DATE LA : " + date);


        SimpleDateFormat formatterMMDD = new SimpleDateFormat("MMM-dd-yyyy HHmm");// not working dky
        SimpleDateFormat formatterDDMM = new SimpleDateFormat("dd-MMM-yyyy HHmm");

        SimpleDateFormat formartterYYMM = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Date ddmmm = new Date();
        Date mmmdd = new Date();

        try {
            if(chkDateFormat_DDMMYYYY(dte) || chkDateFormat_MMDDYYYY(dte) || chkDateFormat_YYYYMMDD(dte)){

                if(chkDateFormat_DDMMYYYY(date)){
                 //   System.out.println("COME HERE MEH?");
                    ddmmm = formatterDDMM.parse(date);
                 //   System.out.println("@@@@@ semo date lai de : " + ddmmm);
                    return ddmmm;
                }
                else if(chkDateFormat_MMDDYYYY(dte)){
                 //   System.out.println("COME HERE ANOT ");
                    SimpleDateFormat format1 = new SimpleDateFormat("MMM-dd-yyyy HHmm");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy HHmm");
                  //  System.out.println("DATE HERE  : " + date);
                    Date tmpDte = format1.parse(date);
                  //  System.out.println("WHAT IS DATE TMP DTE : " + tmpDte);
                    //System.out.println("FORMATTED LEH : " + format2.parse(tmpDte.toString()));
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm");
                    return tmpDte;
                }
                else if(chkDateFormat_YYYYMMDD(dte)){
                  //  System.out.println("COME HERE ANOT YY");
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HHmm");
                    SimpleDateFormat format3 = new SimpleDateFormat("dd-MMM-yyyy HHmm");
                 //   System.out.println("DATE HERE  : " + date);
                    Date tmpDte = format1.parse(date);
                 //   System.out.println("WHAT IS DATE TMP DTE : " + tmpDte);
                    return tmpDte;
                }

            }
        }
        catch (DateTimeParseException | ParseException e){
            throw new InvalidDateException("     ☹ OOPS!!! Date, Syntax Wrong, Please use : DD-MMM-YYYY(13-Oct-2019) HHmm (1000) Or " +
                    "MMM-DD-YYYY HHmm Or YYYY-MM-DD");
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

    /**
     * to check if date is YYYYMMDD
     * @param date
     * @return
     */
    public boolean chkDateFormat_YYYYMMDD(String date){
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        //System.out.println(" DATE HERE DDMM : " + date);
        Date javaDate = new Date();
        /* Create Date object
         * parse the string into date
         */
        try
        {
           // System.out.println(" DATE HERE DDMM : " + date);

            javaDate = sdfrmt.parse(date);
          //  System.out.println(date+" is valid date format yyyy mm dd : ");
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
