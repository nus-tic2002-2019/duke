/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.task;

import java.util.*;
/**
 *
 * @author lug3g
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    
    public TaskList(){
        ArrayList<Task> Tasks = new ArrayList <Task>();
        this.tasks = Tasks;
    }
    
    public int getSize(){
        return this.tasks.size();
    }
    
    public Task getTask(int x){
        return this.tasks.get(x);
    }
    
    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }
}
