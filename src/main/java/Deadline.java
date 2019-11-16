/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke;

import java.util.*;

/**
 *
 * @author lug3g
 */
public class Deadline extends Todo {
    private Date by;

    public Deadline(String description,Date by) {
        super(description);
        this.by = by;
    }

    public void setBy(Date by) {
        this.by = by;
    }

    public Date getBy() {
        return this.by;
    }
    
    public String printTask(){
      return ("[D][" + this.getStatusIcon() + "] " + this.description + "(by: " + this.getBy() + ")");
    }
    
    public String writeTask(){
      return ("D | " + this.getStatus() + " |  " + this.description + " | " + this.getBy());
    }
}
