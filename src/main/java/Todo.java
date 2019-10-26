public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public void print(){
        System.out.print("[T]: ");
        this.printDoneMessage();
        System.out.println(this.description);
    }
    @Override
    public String getSaveFormat(){
        String saveFormat = "T|";
        if (this.done == true){
            saveFormat = saveFormat + "1";
        }
        else { saveFormat = saveFormat + "0"; }

        saveFormat = saveFormat + "|" + this.description;

        return saveFormat;

    }
}
