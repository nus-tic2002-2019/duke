package Ui;

import DukeItems.Task;
import java.util.ArrayList;

public class uiFind {

    protected ArrayList<Task> userList;

    //searchTerm holds <keyword> from "find" command, example: find <keyword>
    protected String searchTerm;

    String taskLine = "";
    Task task = null;

    public uiFind(ArrayList<Task> userList, String searchTerm){
        this.userList = userList;
        this.searchTerm = searchTerm;

    }

    public void findTask(){

        //if <keyword> has # like: #tagDescription
        for (int i = 0; i < userList.size(); i++){

            task = userList.get(i);
            taskLine = userList.get(i).toString();

            if (searchTerm.matches("#\\w")){
                if (task.verifyTag(searchTerm)){
                    System.out.println( (i+1) + ". " + userList.get(i).toString() );
                }
                else {
                    System.out.println(searchTerm + " not found!");
                }
            }

            else {
                if (taskLine.contains(searchTerm)){
                    System.out.println(taskLine);
                }
            }
        }
    }
}
