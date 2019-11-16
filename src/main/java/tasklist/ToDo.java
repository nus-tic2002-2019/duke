package tasklist;

public class ToDo extends Task {

    /****
     *
     * @param description the task description that the user input
     */
    public ToDo(String description) {
        super(description);
    }

    /****
     *
     * @param description the task description that the user input
     * @param status check if the task has already marked as done
     */
    public ToDo(String description, Boolean status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return("[T]" + super.toString());
    }

    @Override
    public String saveFormat(){
        return("T " + super.saveFormat());
    }

    /****
     *
     * @param taskType the task type of the object
     */
    @Override
    public boolean taskType(String taskType){
        if (taskType.equals("todos")){
            return true;
        }
        return false;
    }
}

