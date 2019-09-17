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
}
