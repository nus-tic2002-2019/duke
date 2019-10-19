package enumlist;

import java.security.PublicKey;

public enum Month {
    Jan("JAN", 1),
    January("JANUARY", 1),
    Feb("FEB", 2),
    FEBRUARY("FEBRUARY", 2),
    Mar("MAR", 3),
    March("MARCH", 3),
    APR("APR", 4),
    APRIL("APRIL",4),
    MAY("MAY",5),
    JUNE("JUNE",6),
    JULY("JULY",7),
    AUG("AUG",8),
    AUGUST("AUGUST",8),
    SEP("SEP",9),
    SEPTEMBER("SEPTEMBER",9),
    OCT("OCT",10),
    OCTOBER("OCTOBER",10),
    NOV("NOV",11),
    NOVEMBER("NOVEMBER",11),
    DEC("DEC",12),
    DECEMBER("DECEMBER",12);

    private String month;
    private int index;

    Month(String month, int index) {
        this.month = month;
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }



    public String getMonth(){
        return this.month.substring(0,3) ;
    }

}
