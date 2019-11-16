package Ui;

import DukeItems.Task;
import java.util.ArrayList;

public class uiFind {

    protected ArrayList<Task> userList;
    protected String searchTerm;

    String taskLine = "";
    boolean continueSearch = true;

    public uiFind(ArrayList<Task> userList, String searchTerm){
        this.userList = userList;
        this.searchTerm = searchTerm;

    }

    public void findTask(){

        for (int i = 0; i < userList.size(); i++){
            taskLine = userList.get(i).toString();
            if (taskLine.contains(searchTerm)){
                System.out.println(taskLine);
            }
        }
    }
}
