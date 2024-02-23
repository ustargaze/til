package designpattern.mediator;

public abstract class Colleague {
    protected Mediator mediator;
    protected String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void receive(String name);

    public void notifyOthers() {
        System.out.printf("%s: notify the other colleagues.\n", this.name);
        this.mediator.notifyOthers(this);
    }
}
