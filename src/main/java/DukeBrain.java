public class DukeBrain extends Duke {

    private boolean alive;

    public DukeBrain() {
        alive = true;
        System.out.println("Hello I'm Duke!!");
        System.out.println("What can I do for you?");
        line();
    }

    public boolean isAlive() {
        return alive;
    }

    private void setDead() {
        this.alive = false;
    }

    public void converse(String input) {

        if (input.contains("Bye") || input.contains("bye"))
            this.setDead();
        
        System.out.println( "  " + input);
        line();
    }


}
