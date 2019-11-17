package Ui;

import DukeItems.Deadline;
import DukeItems.Task;

import java.util.ArrayList;

public class uiDeadline {

    protected ArrayList<Task> userList;
    protected String input;
    Task task = null;
    uiDateTimeParser DateTimeParser = null;


    public uiDeadline(ArrayList<Task> userList, String input){
        this.userList = userList;
        this.input = input;
    }

    public void addDeadline(){
        String[] dsplit = input.split("/");
        String deadTask = dsplit[0];
        String deadBy = dsplit[1];

        deadTask = deadTask.substring(9); //set start of string after "deadline"
        deadBy = deadBy.substring(3); //set start of string after "by"

        DateTimeParser = new uiDateTimeParser(deadBy);

        Deadline deadline = new Deadline(deadTask, DateTimeParser.uiDateTimeParser());
        userList.add(deadline);

        System.out.println("__________________________________________________________________");
        System.out.println("Procrastination is forbidden. Deadline added: " + System.lineSeparator() + deadline.toString());
        System.out.println("__________________________________________________________________");

        //DateTimeFormatter input = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        //DateTimeFormatter output = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        //LocalDateTime DateTime = LocalDateTime.parse(deadBy, input);
        //System.out.println(DateTime.format(output));

    }
}
