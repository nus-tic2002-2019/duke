/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.task;

import duke.ui.Ui;

/**
 *
 * @author lug3g
 */
public class Deadline extends Todo {
    private String by;

    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return Ui.printDateTime(this.by);
    }
    
    public String printTask(){
      return ("[D][" + this.getStatusIcon() + "] " + this.description + "(by: " + this.getBy() + ")");
    }
    
    public String writeTask(){
      return ("D | " + this.getStatus() + " | " + this.description + " | " + this.getBy());
    }
    
    public String getDateTime(){
        return this.by;
    }
}
