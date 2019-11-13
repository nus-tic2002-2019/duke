package Ui;

import DukeItems.Event;
import DukeItems.Task;
import java.util.ArrayList;

public class uiEvent {

    protected ArrayList<Task> userList;
    protected String input;
    Task task = null;

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
        //System.out.println(deadTask);
        //System.out.println(deadBy);

        Event event = new Event(eventTask, eventAt);
        userList.add(event);

        System.out.println("__________________________________________________________________");
        System.out.println("Don't you DARE come late. Event added: " + System.lineSeparator() + event.toString());
        System.out.println("__________________________________________________________________");
    }
}
