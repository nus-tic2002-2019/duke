package Ui;

import DukeItems.Task;
import java.util.ArrayList;

public class uiTag {

    protected ArrayList<Task> userList;

    //input takes in user input. Example: tag 1 #tagDescription
    protected String input;
    Task task = null;

    public uiTag(ArrayList<Task> userList, String input){
        this.userList = userList;
        this.input = input;
    }

    public void addTag(){
        //remove spaces from input
        //now we have split[0] = tag, split[1] = 1, split[2] = #tagDescription
        String[] split = input.split(" ");

        //itemNum to find specific element in userList
        int itemNum = Integer.parseInt(split[1]);

        //get #tagDescription
        String itemTag = split[2];

        //get element from userList
        task = userList.get(itemNum-1);

        //add #tagDescription
        task.setTags(itemTag);

        System.out.println("__________________________________________________________________");
        System.out.println("Item number " + itemNum + " has been tagged as " + itemTag);
        System.out.println("__________________________________________________________________");
    }
}
