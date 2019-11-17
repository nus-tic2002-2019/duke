package Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class uiDateTimeParser {

    private String DateTime;
    private DateTimeFormatter inputDT = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    private DateTimeFormatter inputD = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private DateTimeFormatter outputDT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
    private DateTimeFormatter outputD = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    public uiDateTimeParser(String DateTime){
        this.DateTime = DateTime;
    }

    public String uiDateTimeParser(){
        LocalDateTime formattedDateTime = LocalDateTime.parse(DateTime, inputDT);
        return formattedDateTime.format(outputDT).toString();
    }

    public String uiDateParser(){
        LocalDate formattedDate = LocalDate.parse(DateTime, inputD);
        return formattedDate.format(outputD).toString();
    }
}


