package enumlist;

import thrownexceptions.EnumDayIndexWrongly;

import java.lang.module.FindException;
import java.net.BindException;

public enum Days {

    FIRST("1st", 1),
    SECOND("2nd", 2),
    THIRD("3rd", 3),
    OTHERS("th",4);

    private String day;
    private int index;

    /**
     * enum Days constructor which include day in String and the index
     * @param day day of the month in String
     * @param index index of the day in int
     */
    Days(String day, int index){
        this.day = day;
        this.index = index;
    }

    /**
     * To get the Day from enum;
     * e.g. 1st, 22th...
     * @return return Day in String
     */
    public String getDay(){
        return this.day;
    }

    /**
     * To get index of the day
     * e.g. first day of the month the index is "1";
     * @return return index of the day in int
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * To get the day String by using the index input
     * e.g. If the index input is 4, the day is "4th"
     * @param index index of the day
     * @return return day in String of the month
     * @throws EnumDayIndexWrongly if the index number input is not in the enum Days list, there will be thrown an error to user;
     */
    public static String getDay(int index) throws EnumDayIndexWrongly {
        String days = null;
        if(index>3){
            days = String.valueOf(index) + "th";
            return days;
        }

        for(Days d : Days.values()){
            if(d.getIndex()==index){
                days = d.getDay();
                break;
            }
        }

        if(days.equals(null)) throw new EnumDayIndexWrongly();

        return days;
    }
}
