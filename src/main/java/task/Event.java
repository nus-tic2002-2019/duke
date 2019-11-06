package task;

public class Event extends Task {

    protected String at;

    public Event(String description){
        super(description);
    }
    public Event(String description, String at) {
        super(description);
        this.at = at;

    }

    @Override
    public void print(){
        System.out.print("[E]: ");
        this.printDoneMessage();
        System.out.print(" " + this.description);
        System.out.print("at: " + at + "\n");
    }

    @Override
    public String getSaveFormat(){
        String saveFormat = "E|";
        if (this.done == true){
            saveFormat = saveFormat + "1";
        }
        else { saveFormat = saveFormat + "0"; }

        saveFormat = saveFormat + "|" + this.description;
        saveFormat = saveFormat + "|" + this.at;
        return saveFormat;

    }


}
