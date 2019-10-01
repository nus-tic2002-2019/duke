public class ToDos extends Task {
    public ToDos(String thingsToDo) {
        super(thingsToDo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }



}
