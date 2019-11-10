package Ui;

import DukeItems.Task;
import DukeItems.Todo;
import java.util.ArrayList;

public class uiTodo {
    protected ArrayList<Task> userList;
    protected String input;
    Task task = null;
    Todo todoTask = null;

    public uiTodo(ArrayList<Task> userList, String input){
        this.userList = userList;
        this.input = input;
    }

    public void addTodo(){
        String todo = input.substring(4);
        todoTask = new Todo(todo);
        userList.add(todoTask);
        System.out.println("__________________________________________________________________");
        System.out.println("Okie dokie! Task added: " + System.lineSeparator() + todoTask.toString());
        System.out.println("__________________________________________________________________");
    }
}
