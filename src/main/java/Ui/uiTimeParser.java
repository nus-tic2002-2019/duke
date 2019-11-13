package Ui;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class uiTimeParser {

    private String DateTime;
    private DateTimeFormatter input = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    private DateTimeFormatter output = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());

    public uiTimeParser(String DateTime){
        this.DateTime = DateTime;
    }

    public String uiDateTimeParser(){
        LocalDateTime formattedDateTime = LocalDateTime.parse(DateTime, input);
        return formattedDateTime.format(output).toString();
    }
}
