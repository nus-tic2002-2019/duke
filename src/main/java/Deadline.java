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
        System.out.print(this.description);
        System.out.print("by: " + by);
    }


}
