package designpattern.mediator;

public class ConcreteColleagueB extends Colleague {
    public ConcreteColleagueB(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void receive(String name) {
        System.out.printf("%s: receive message form %s.\n", this.name, name);
    }
}
