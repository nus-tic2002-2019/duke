package util;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {
    /**
     * This method is to convert a string based date time of format dd/MM/yyyy HHmm (dd:date, MM: month,
     * yyyy: year, HH: Hour, mm: minute ) into a LocalDateTime type of objects.
     * @param date String date. in the form of dd/MM/yyyy HHmm
     * @return LocalDateTime type of object
     * @throws DukeException will throw DukeException and request user to input dd/MM/yyyy HHmm format
     */
    public static LocalDateTime convertDateTime(String date) throws DukeException
    {
        try{
            date = date.trim(); // to ensure string of date have no leading and trailing space
            assert date.length()==15:date.length(); //expected length of Date Time string is 15 chars
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

            LocalDateTime d = LocalDateTime.parse(date, df);
            return d;
        }catch (DateTimeParseException e){
            throw new DukeException("invalid date time format. Please use the following format - dd/MM/yyyy HHmm");
        }
    }


}
