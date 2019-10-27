package enumlist;

import thrownexceptions.MonthIndexWrong;

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
    MAY_lONG("MAY ",5),
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

    /**
     * enum Month constructor which contain month and index input
     * @param month month in String
     * @param index index of the month in int
     */
    Month(String month, int index) {
        this.month = month;
        this.index = index;
    }

    /**
     * To get the index of the month
     * @return return index in int
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * To get month in String
     * @return return month in String
     */
    public String getMonth(){
        return month;
    }

    /**
     * To get the full name of the month base on the index input
     * e.g. The input index is 11, therefor the month full name will be "November"
     * @param index index of the month
     * @return return month full name
     * @throws MonthIndexWrong is the index input is not in the enum Month range, there will be an error throw to user
     */
    public static String getMonth_FullName (int index) throws MonthIndexWrong {
        String MONTH = null;

        for(Month m : Month.values()){
            if(m.getIndex() == index && m.getMonth().length()>3){
                MONTH = m.month.trim();
                break;
            }
        }

        if(MONTH.equals(null)) throw new MonthIndexWrong();

        return FirstLetterUpperCase(MONTH);
    }

    /**
     * To get the month name in short form String.
     * e.g. The month index input is 8, so the month name in short form is "Aug"
     * @param index index of month
     * @return return month short form name
     * @throws MonthIndexWrong If the month index input is not in the enum Month range, there will be an error be throw to user
     */
    public static String getMonth_ShortForm (int index) throws MonthIndexWrong {
        String MONTH = null;
        for(Month m : Month.values()){
            if(m.getIndex() == index){
                MONTH = m.getMonth();
                break;
            }
        }

        if(MONTH.equals(null)) throw new MonthIndexWrong();

        return FirstLetterUpperCase(MONTH);
    }

    /**
     * To only change the first letter of the input to Uppercase
     * @param LowerCase input String, all in lowerCase
     * @return return the input String with the first letter in Uppercase
     */
    public static String FirstLetterUpperCase(String LowerCase){
        LowerCase = LowerCase.toLowerCase();
        char[] cs = LowerCase.toCharArray();
        cs[0] -= 32;

        return String.valueOf(cs);
    }
}
