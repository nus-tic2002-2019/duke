package task;

import java.util.List;

public class Originator{

    private int state;
    // The class could also contain additional data that is not part of the
    // state saved in the memento..

    public void set(int state) {
        this.state = state;
        System.out.println("Originator: Setting state to " + state);
    }

    public Memento saveToMemento(List<Task> tasklist) {
        Memento mem=new Memento(this.state);
        for(int i=0;i<tasklist.size();i++){
            mem.tasks+= tasklist.get(i).print()+"\n" ;
        }
        System.out.println("Originator: Saving to Memento.");
        return mem;
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }

    public static class Memento {
        public String tasks;
        private final int state;
        public String getString(){
            return tasks;
        }

        public Memento(int stateToSave) {
            state = stateToSave;
        }

        // accessible by outer class only
        private int getSavedState() {
            return state;
        }
    }
}
