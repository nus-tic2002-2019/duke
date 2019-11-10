package Ui;

import DukeItems.Task;
import java.util.ArrayList;

public class uiList {

    protected ArrayList<Task> userList;
    //protected ArrayList<Task>clonedList; //duplicates the userList

    public uiList(ArrayList<Task> userList){
        this.userList = userList;
        //clonedList = new ArrayList(userList);
    }

    public void printList(){
        System.out.println("__________________________________________________________________");
        System.out.println("Your current List of tasks:");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println( (i+1) + ". " + userList.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }

}
