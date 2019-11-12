/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke;

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
        return this.by;
    }
    
    public String printTask(){
      return ("[D][" + this.getStatusIcon() + "] " + this.description + "(by: " + this.getBy() + ")");
    }
}
