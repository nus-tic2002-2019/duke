package Ui;

import DukeItems.Event;
import DukeItems.Task;
import java.util.ArrayList;

public class uiEvent {

    protected ArrayList<Task> userList;
    protected String input;
    Task task = null;
    uiDateTimeParser DateTimeParser = null;

    public uiEvent(ArrayList<Task> userList, String input){
        this.userList = userList;
        this.input = input;
    }

    public void addEvent(){
        String[] esplit = input.split("/");
        String eventTask = esplit[0];
        String eventAt = esplit[1];

        eventTask = eventTask.substring(6);
        eventAt = eventAt.substring(3);

        DateTimeParser = new uiDateTimeParser(eventAt);

        Event event = new Event(eventTask, DateTimeParser.uiDateTimeParser());
        userList.add(event);

        System.out.println("__________________________________________________________________");
        System.out.println("Don't you DARE come late. Event added: " + System.lineSeparator() + event.toString());
        System.out.println("__________________________________________________________________");
    }
}
