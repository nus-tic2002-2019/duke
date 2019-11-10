package Ui;

import DukeItems.Task;

import java.util.ArrayList;

public class uiDelete {
    protected ArrayList<Task> userList;
    //protected java.util.ArrayList<Task> clonedList; //duplicates the userList
    protected String input;
    protected int delNum;
    Task task = null;

    public uiDelete(ArrayList<Task>userList, String input, int delNum){
        this.userList = userList;
        this.input = input;
        this.delNum = delNum;
        //clonedList = new ArrayList(userList);
    }

    public void itemDelete(){
        System.out.println("__________________________________________________________________");
        System.out.println("Noted! I've removed this task: " + System.lineSeparator() + userList.get(delNum - 1).toString());

        userList.remove(delNum - 1);

        System.out.println("You now have " + userList.size() + " task(s) in the list.");
        System.out.println("__________________________________________________________________");
    }

}
