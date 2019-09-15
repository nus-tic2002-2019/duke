public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description){
        super(description);
    }
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void print(){
        System.out.print("[T]: ");
        this.printDoneMessage();
        System.out.print(this.description);
        System.out.print("start: " + start);
        System.out.print(" end: " + end);
    }



}
