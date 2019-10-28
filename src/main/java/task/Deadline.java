package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description){
        super(description);
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void print(){
        System.out.print("[D]: ");
        this.printDoneMessage();
        System.out.print(" " + this.description);
        System.out.print("by: " + by + "\n");

    }

    @Override
    public String getSaveFormat(){
        String saveFormat = "D|";
        if (this.done == true){
            saveFormat = saveFormat + "1";
        }
        else { saveFormat = saveFormat + "0"; }

        saveFormat = saveFormat + "|" + this.description;
        saveFormat = saveFormat + "|" + this.by;
        return saveFormat;

    }

}
