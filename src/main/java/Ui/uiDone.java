package Ui;

import DukeItems.Task;
import java.util.ArrayList;

public class uiDone {

    protected ArrayList<Task> userList;
    protected String input;
    Task task = null;

    public uiDone(ArrayList<Task> userList, String input){
        this.userList = userList;
        this.input = input;
    }

    public void markDone(){
        String[] split = input.split(" "); //split input
        int doneNum = Integer.parseInt(split[1]); //storing list number
        task = userList.get(doneNum - 1);
        task.markAsDone();

        System.out.println("__________________________________________________________________");
        System.out.println("Done!");
        System.out.println("__________________________________________________________________");
    }
}
