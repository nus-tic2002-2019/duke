public class ToDo extends Task {

    protected boolean isDone;

    public ToDo(){
        super();
    }

    public ToDo(String s){
        super(s);
    }
    public ToDo(String s, boolean isDone){
        super(s);
        this.isDone=isDone;
    }

    public String list(){
        return "[T]" + print();
    }

    public String print(){
        if(isDone) {
            return "[\u2713]" + super.print();
        }else{
            return "[\u2718]" + super.print();
        }
    }
    public void setDone(boolean isDone){
        this.isDone=isDone;
    }
    public boolean getDone(){
        return this.isDone;
    }
    public String getText(){
        return super.getText();
    }
}